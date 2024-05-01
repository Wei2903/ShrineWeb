package com.shrine.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.admin.service.AdminChapterService;
import com.shrine.web.admin.service.AdminComicPagesService;
import com.shrine.web.entity.Chapter;
import com.shrine.web.entity.ComicPage;
import com.shrine.web.entity.Series;
import com.shrine.web.mapper.ChapterMapper;
import com.shrine.web.service.ChapterService;
import com.shrine.web.service.SeriesService;
import com.shrine.web.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminChapterServiceImpl extends ServiceImpl<ChapterMapper,Chapter> implements AdminChapterService {
    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    ChapterService chapterService;

    @Autowired
    SeriesService seriesService;

    @Autowired
    AdminComicPagesService adminComicPagesService;


    @Override
    public Long getMaxSeriesChapterNumber(Integer seriesId) {
        return chapterMapper.getChaptersCountNumberBySeriesId(seriesId);
    }

    @Override
    public void insertChapter(Chapter chapter) {
        chapterMapper.insertChapter(chapter);
    }

    @Override
    public String getChapterTitleById(Integer chapterId){
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chapter::getId,chapterId);
        Chapter chapter = this.getOne(lambdaQueryWrapper);
        return chapter.getTitle();
    };
    @Override
    public void updateChapterTitle(Long chapterId, String title) {
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chapter::getId,chapterId);
        Chapter chapter = this.getOne(lambdaQueryWrapper);
        chapter.setTitle(title);
        UpdateWrapper<Chapter> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",chapterId);
        this.update(chapter,updateWrapper);
    }

    @Override
    public void updateChapterCoverImage(Integer chapterId, String coverImage) {
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chapter::getId,chapterId);
        Chapter chapter = this.getOne(lambdaQueryWrapper);
        chapter.setLogo(coverImage);
        UpdateWrapper<Chapter> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",chapterId);
        this.update(chapter,updateWrapper);
    }

    @Override
    public void deleteChapter(Integer chapterId) {
        List<Integer> comicPages = adminComicPagesService.getComicPagesById(chapterId);
        adminComicPagesService.deleteBatchPage(comicPages,chapterId);
        Chapter chapter = chapterService.getComicPagesByChapterId(chapterId);
        Series series = seriesService.querySeriesDetail(Math.toIntExact(chapter.getSeriesId()));
        String seriesTitle = series.getTitle();
        String chapterTitle = chapter.getTitle();
        // Delete from files
        IOUtils.deleteChapter(seriesTitle,chapterTitle);
        // Delete from database
        chapterService.deleteChapter(chapterId);
    }

    @Override
    public void deleteBatchChapter(List<Integer> chapterIds) {
        for(Integer chapterId:chapterIds){
            List<Integer> comicPages = adminComicPagesService.getComicPagesById(chapterId);
            adminComicPagesService.deleteBatchPage(comicPages,chapterId);
            deleteChapter(chapterId);
        }
    }

    @Override
    public List<Integer> getChaptersBySeriesId(Integer seriesId) {
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chapter::getSeriesId,seriesId);
        List<Chapter> chapterList = list(lambdaQueryWrapper);
        List<Integer> chapterIdList = new ArrayList<>();
        for (Chapter chapter:chapterList){
            chapterIdList.add(Math.toIntExact(chapter.getId()));
        }
        return chapterIdList;
    }


}
