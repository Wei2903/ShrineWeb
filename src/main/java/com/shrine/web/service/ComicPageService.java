package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.ComicPage;

public interface ComicPageService extends IService<ComicPage> {
    String getComicTitleByComicId(Integer id);

    void deletePage(Integer id);
}
