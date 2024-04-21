package com.shrine.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.admin.service.AdminChapterService;
import com.shrine.web.entity.Chapter;
import com.shrine.web.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminChapterServiceImpl extends ServiceImpl<ChapterMapper,Chapter> implements AdminChapterService {
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

    @Override
    public String getChapterTitleById(Integer chapterId){
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chapter::getId,chapterId);
        Chapter chapter = this.getOne(lambdaQueryWrapper);
        return chapter.getTitle();
    };
    @Override
    public void updateChapterTitle(Long chapterId, String title) {
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chapter::getId,chapterId);
        Chapter chapter = this.getOne(lambdaQueryWrapper);
        chapter.setTitle(title);
        UpdateWrapper<Chapter> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",chapterId);
        this.update(chapter,updateWrapper);
    }

    @Override
    public void updateChapterCoverImage(Integer chapterId, String coverImage) {
        LambdaQueryWrapper<Chapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chapter::getId,chapterId);
        Chapter chapter = this.getOne(lambdaQueryWrapper);
        chapter.setLogo(coverImage);
        UpdateWrapper<Chapter> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",chapterId);
        this.update(chapter,updateWrapper);
    }


}
