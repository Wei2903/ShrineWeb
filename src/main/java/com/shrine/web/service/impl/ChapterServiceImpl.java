package com.shrine.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.entity.Chapter;
import com.shrine.web.mapper.ChapterMapper;
import com.shrine.web.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ChapterServiceImpl
        extends ServiceImpl<ChapterMapper, Chapter>
        implements ChapterService {

    @Autowired
    ChapterMapper chapterMapper;

    // Get all pages for a selected Chapter
    @Override
    public Chapter getComicPagesByChapterId(Integer chapterId) {
        return chapterMapper.getDetailedChapter(chapterId);
    }

    @Override
    public void deleteChapter(Integer chapterId) {
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chapter::getId,chapterId);
        this.remove(lambdaQueryWrapper);
    }
}
