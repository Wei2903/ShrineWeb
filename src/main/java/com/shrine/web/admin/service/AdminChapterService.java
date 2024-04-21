package com.shrine.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Chapter;

public interface AdminChapterService extends IService<Chapter> {
    public Long getMaxSeriesChapterNumber(Integer seriesId);

    public void insertChapter(Chapter chapter);

    String getChapterTitleById(Integer chapterId);

    public void updateChapterTitle(Long chapterId, String title);
    public void updateChapterCoverImage(Integer chapterId, String coverImage);
}
