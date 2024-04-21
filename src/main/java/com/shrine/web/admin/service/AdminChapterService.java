package com.shrine.web.admin.service;

import com.shrine.web.entity.Chapter;

public interface AdminChapterService {
    public Long getMaxSeriesChapterNumber(Integer seriesId);

    public void insertChapter(Chapter chapter);
}
