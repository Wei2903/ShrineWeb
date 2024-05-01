package com.shrine.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.ComicPage;
import com.shrine.web.entity.Series;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AdminComicPagesService extends IService<ComicPage> {

    Long getMaxChapterPageNumber(Integer chapterId);

    void insertPage(ComicPage comicPage);

    void deleteSinglePage(Integer id, Integer chapterId);

    void deleteBatchPage(List<Integer> ids, Integer chapterId);

    List<Integer> getComicPagesById(Integer chapterId);
}
