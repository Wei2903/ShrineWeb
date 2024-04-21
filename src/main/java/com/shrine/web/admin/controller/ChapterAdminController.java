package com.shrine.web.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shrine.web.admin.service.AdminChapterService;
import com.shrine.web.entity.Chapter;
import com.shrine.web.entity.Series;
import com.shrine.web.service.ChapterService;
import com.shrine.web.service.SeriesService;
import com.shrine.web.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ChapterAdminController {
    @Autowired
    AdminChapterService adminChapterService;
    @Autowired
    SeriesService seriesService;

    @Autowired
    ChapterService chapterService;

    @PostMapping("/admin/chapters/addChapters")
    public String addChapter(
            @RequestParam("Id") Integer seriesId,
            @RequestParam("Title") String title,
            @RequestParam("ChapterImage") MultipartFile chapterImage
            ){
        Long maxSeriesChapterNumber = adminChapterService.getMaxSeriesChapterNumber(seriesId);
        Series series = seriesService.querySeriesDetail(seriesId);
        try {
            boolean ifInsertSuccessful = IOUtils.SaveChapterLogo(series, title, chapterImage);
            if (!ifInsertSuccessful){
                return "Chapter Title Already Exists, Cannot Insert Chapter";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "File IO Error, Cannot Insert Chapter";
        }
        //TODO: insert Thumb
        Chapter chapter = new Chapter();
        chapter.setSeriesId(series.getId());
        chapter.setNumber(maxSeriesChapterNumber+1);
        chapter.setTitle(title);
        chapter.setLogo(chapterImage.getOriginalFilename());
        chapter.setThumb("Thumb Haven't been Implemented yet");
        chapter.setFinish(1);
        chapter.setStatus(1);
        adminChapterService.insertChapter(chapter);
        return "Add Chapter Successful";
    }

    @PostMapping("/admin/chapters/modifyChapterTitle")
    public String modifyChapterTitle (@RequestParam("Id") Integer seriesId,
                                      @RequestParam("ChapterId") String id,
                                      @RequestParam("newTitle") String newTitle){

        boolean isRenameSuccessful = false;

        Series series = seriesService.querySeriesDetail(seriesId);
        String oldTitle = adminChapterService.getChapterTitleById(Integer.parseInt(id));
        System.out.println("-----------------------------------------------------------------------------"+oldTitle);
        try {
            isRenameSuccessful = IOUtils.renameChapterDirectory(series,oldTitle,newTitle);
            if(!isRenameSuccessful){
                return "Rename failed.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "File IO Error, Cannot rename Series";
        }

        adminChapterService.updateChapterTitle(Long.parseLong(id),newTitle);
        return "Modify Series Title Successfully";
    }

    @PostMapping ("/admin/chapters/modifyChapterCoverImage")
    public String modifyChapterCoverImage (@RequestParam("Id") Integer seriesId,
                                           @RequestParam("ChapterId") String id,
                                           @RequestParam("newCoverImage") MultipartFile newCoverImage){
        Series series = seriesService.querySeriesDetail(seriesId);
        String chapterTitle = adminChapterService.getChapterTitleById(Integer.parseInt(id));
        System.out.println("-----------------------" + series.getTitle());
        boolean isModifyCoverImageSuccessful = false;
        try {
            isModifyCoverImageSuccessful = IOUtils.modifyChapterImage(series.getTitle(),chapterTitle,series.getPortraitImage(),newCoverImage);
            if(!isModifyCoverImageSuccessful){
                return "modify image failed.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "File IO Error, Cannot modify image.";
        }
        adminChapterService.updateChapterCoverImage(Integer.parseInt(id),newCoverImage.getOriginalFilename());
        return "Modify Cover Image Successfully";
    }


}
