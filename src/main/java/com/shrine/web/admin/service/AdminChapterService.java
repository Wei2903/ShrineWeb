package com.shrine.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Chapter;

import java.util.List;

public interface AdminChapterService extends IService<Chapter> {
    public Long getMaxSeriesChapterNumber(Integer seriesId);

    public void insertChapter(Chapter chapter);

    String getChapterTitleById(Integer chapterId);

    public void updateChapterTitle(Long chapterId, String title);
    public void updateChapterCoverImage(Integer chapterId, String coverImage);

    void deleteChapter(Integer chapterId);
    
    void deleteBatchChapter(List<Integer> chapterIds);

    List<Integer> getChaptersBySeriesId(Integer seriesId);
}
