package com.shrine.web.admin.service.impl;

import com.shrine.web.admin.service.AdminChapterService;
import com.shrine.web.entity.Chapter;
import com.shrine.web.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminChapterServiceImpl implements AdminChapterService {
    @Autowired
    ChapterMapper chapterMapper;


    @Override
    public Long getMaxSeriesChapterNumber(Integer seriesId) {
        return chapterMapper.getChaptersCountNumberBySeriesId(seriesId);
    }

    @Override
    public void insertChapter(Chapter chapter) {
        chapterMapper.insertChapter(chapter);
    }


}
