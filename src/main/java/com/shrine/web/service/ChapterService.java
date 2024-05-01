package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Chapter;
import com.shrine.web.entity.ComicPage;

import java.util.List;

public interface ChapterService extends IService<Chapter> {
    // Check impl/ChapterServiceImpl.java for actual implementation
    Chapter getComicPagesByChapterId(Integer chapterId);

    void deleteChapter(Integer chapterId);
}
