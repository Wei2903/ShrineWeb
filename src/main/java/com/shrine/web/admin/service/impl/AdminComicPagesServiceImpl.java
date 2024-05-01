package com.shrine.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.admin.service.AdminComicPagesService;
import com.shrine.web.admin.service.AdminSeriesService;
import com.shrine.web.entity.Chapter;
import com.shrine.web.entity.ComicPage;
import com.shrine.web.entity.Series;
import com.shrine.web.mapper.ComicPageMapper;
import com.shrine.web.mapper.SeriesMapper;
import com.shrine.web.service.ChapterService;
import com.shrine.web.service.ComicPageService;
import com.shrine.web.service.SeriesService;
import com.shrine.web.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminComicPagesServiceImpl extends ServiceImpl<ComicPageMapper, ComicPage> implements AdminComicPagesService {

    @Autowired
    ChapterService chapterService;

    SeriesService seriesService;

    ComicPageService comicPageService;
    @Override
    public Long getMaxChapterPageNumber(Integer chapterId) {
        LambdaQueryWrapper<ComicPage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ComicPage::getChapterId,chapterId);
        List<ComicPage> list = list(lambdaQueryWrapper);
        Long max = Long.valueOf(list.size());
        return max;
    }

    @Override
    public void insertPage(ComicPage comicPage) {
        this.save(comicPage);
    }

    @Override
    public void deleteSinglePage(Integer id, Integer chapterId) {
        Chapter chapter = chapterService.getComicPagesByChapterId(chapterId);
        Series series = seriesService.querySeriesDetail(Math.toIntExact(chapter.getSeriesId()));
        String seriesTitle = series.getTitle();
        String chapterTitle = chapter.getTitle();
        String imageName = comicPageService.getComicTitleByComicId(id);
        // Delete from files
        IOUtils.deleteComicPages(seriesTitle,chapterTitle,imageName);
        // Delete from database
        comicPageService.deletePage(id);
    }

    @Override
    public void deleteBatchPage(List<Integer> ids, Integer chapterId) {
        Chapter chapter = chapterService.getComicPagesByChapterId(chapterId);
        Series series = seriesService.querySeriesDetail(Math.toIntExact(chapter.getSeriesId()));
        String seriesTitle = series.getTitle();
        String chapterTitle = chapter.getTitle();
        for(Integer id:ids){
            String imageName = comicPageService.getComicTitleByComicId(id);
            // Delete from files
            IOUtils.deleteComicPages(seriesTitle,chapterTitle,imageName);
            // Delete from database
            comicPageService.deletePage(id);
        }
    }

    @Override
    public List<Integer> getComicPagesById(Integer chapterId) {
        LambdaQueryWrapper<ComicPage> comicPageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        comicPageLambdaQueryWrapper.eq(ComicPage::getChapterId,chapterId);
        List<ComicPage> comicPageList = list(comicPageLambdaQueryWrapper);
        List<Integer> comicPageIdList = new ArrayList<>();
        for(ComicPage page: comicPageList){
            comicPageIdList.add(page.getId());
        }
        return comicPageIdList;
    }
}
