package com.shrine.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.entity.Collectable;
import com.shrine.web.entity.ComicPage;
import com.shrine.web.mapper.CollectableMapper;
import com.shrine.web.mapper.ComicPageMapper;
import com.shrine.web.service.CollectableService;
import com.shrine.web.service.ComicPageService;
import org.springframework.stereotype.Service;

@Service
public class ComicPageServiceImpl extends ServiceImpl<ComicPageMapper, ComicPage>
        implements ComicPageService {

    @Override
    public String getComicTitleByComicId(Integer id) {
        LambdaQueryWrapper<ComicPage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ComicPage::getId,id);
        ComicPage comicPage = this.getOne(lambdaQueryWrapper);
        return comicPage.getImgPath();
    }

    @Override
    public void deletePage(Integer id) {
        LambdaQueryWrapper<ComicPage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ComicPage::getId,id);
        this.remove(lambdaQueryWrapper);
    }
}
