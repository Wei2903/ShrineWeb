package com.shrine.web.controller;

import com.shrine.web.entity.Chapter;
import com.shrine.web.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @ResponseBody
    @GetMapping("/chapter/{chapterId}")
    public Chapter getDetailedChapter(@PathVariable("chapterId") String chapterId){
        Integer id = Integer.parseInt(chapterId);
        Chapter chapter = chapterService.getComicPagesByChapterId(id);
        return chapter;
    }
}
