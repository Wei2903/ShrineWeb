package com.shrine.web.admin.controller;

import com.shrine.web.admin.service.AdminChapterService;
import com.shrine.web.admin.service.AdminComicPagesService;
import com.shrine.web.entity.Chapter;
import com.shrine.web.entity.ComicPage;
import com.shrine.web.entity.Series;
import com.shrine.web.service.ChapterService;
import com.shrine.web.service.ComicPageService;
import com.shrine.web.service.SeriesService;
import com.shrine.web.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ComicPagesAdminController {

    @Autowired
    AdminComicPagesService adminComicPagesService;

    @Autowired
    ComicPageService comicPageService;
    @Autowired
    ChapterService chapterService;

    @Autowired
    SeriesService seriesService;
    @PostMapping("/admin/pages/addPages")
    public String addPages(
            @RequestParam("Id") Integer chapterId,
            @RequestParam("PageImage") MultipartFile pageImage
    ){
        //Long maxSeriesChapterNumber = adminChapterService.getMaxSeriesChapterNumber(seriesId);
        //Series series = seriesService.querySeriesDetail(seriesId);

        Long maxChapterPageNumber = adminComicPagesService.getMaxChapterPageNumber(chapterId);
        Chapter chapter = chapterService.getComicPagesByChapterId(chapterId);
        Series series = seriesService.querySeriesDetail(Math.toIntExact(chapter.getSeriesId()));

        try {
            IOUtils.SaveComicPages(series,chapter,pageImage);
        } catch (IOException e) {
            e.printStackTrace();
            return "File IO Error, Cannot Insert Page";
        }
        //TODO: insert Thumb
        ComicPage comicPage = new ComicPage();
        comicPage.setPageNum(Math.toIntExact(maxChapterPageNumber+1));
        comicPage.setChapterId(Long.valueOf(chapterId));
        comicPage.setImgPath(pageImage.getOriginalFilename());
        comicPage.setStatus(1);
        comicPage.setDoubleSpread(0);
        comicPage.setIsVisible(1);
        adminComicPagesService.insertPage(comicPage);
        return "Add page Successful";
    }

    @PostMapping("/admin/pages/batchAddPages")
    public String batchAddPages( @RequestParam("Id") Integer chapterId,
                                   @RequestParam("files") List<MultipartFile> files) {

        Chapter chapter = chapterService.getComicPagesByChapterId(chapterId);
        Series series = seriesService.querySeriesDetail(Math.toIntExact(chapter.getSeriesId()));
        Long maxChapterPageNumber = adminComicPagesService.getMaxChapterPageNumber(chapterId);
        for(MultipartFile file: files){
            try {
                IOUtils.SaveComicPages(series,chapter,file);
            } catch (IOException e) {
                e.printStackTrace();
                return "File IO Error, Cannot Insert Page";
            }
            //TODO: insert Thumb
            ComicPage comicPage = new ComicPage();
            comicPage.setPageNum(Math.toIntExact(maxChapterPageNumber+1));
            maxChapterPageNumber = maxChapterPageNumber+1;
            comicPage.setChapterId(Long.valueOf(chapterId));
            comicPage.setImgPath(file.getOriginalFilename());
            comicPage.setStatus(1);
            comicPage.setDoubleSpread(0);
            comicPage.setIsVisible(1);
            adminComicPagesService.insertPage(comicPage);
        }
        return "Add pages Successful";
    }

    @PostMapping("/admin/pages/deletePages")
    public String deletePages( @RequestParam("id") Integer id,@RequestParam("chapterId") Integer chapterId) {
        adminComicPagesService.deleteSinglePage(id,chapterId);
        return "Delete page Successful";
    }

    @PostMapping("/admin/pages/batchDeletePages")
    public String batchDeletePages( @RequestParam("ids")List<Integer> ids,@RequestParam("chapterId") Integer chapterId) {
        adminComicPagesService.deleteBatchPage(ids, chapterId);
        return "Delete pages Successful";
    }



}
