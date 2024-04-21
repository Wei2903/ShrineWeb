package com.shrine.web.admin.controller;

import com.shrine.web.admin.service.AdminChapterService;
import com.shrine.web.entity.Chapter;
import com.shrine.web.entity.Series;
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


}
