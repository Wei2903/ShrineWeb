package com.shrine.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.admin.service.AdminSeriesService;
import com.shrine.web.entity.Series;
import com.shrine.web.mapper.SeriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;




@Service
public class AdminSeriesServiceImpl extends ServiceImpl<SeriesMapper,Series> implements AdminSeriesService {
    @Autowired
    SeriesMapper seriesMapper;

    @Override
    public void insertSeries(Series series) {
        seriesMapper.insertSeries(series);
    }

    @Override
    @Transactional
    public void updateSeriesTitle(Long seriesId, String title) {
        LambdaQueryWrapper<Series> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Series::getId,seriesId);
        Series series = this.getOne(lambdaQueryWrapper);
        series.setTitle(title);
        UpdateWrapper<Series> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",seriesId);
        this.update(series,updateWrapper);
    }

    @Override
    public void updateSeriesDescription(Long seriesId, String description) {
        LambdaQueryWrapper<Series> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Series::getId,seriesId);
        Series series = this.getOne(lambdaQueryWrapper);
        series.setDes(description);
        UpdateWrapper<Series> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",seriesId);
        this.update(series,updateWrapper);
    }

    @Override
    public void updateSeriesCoverImage(Long seriesId, String coverImage) {
        LambdaQueryWrapper<Series> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Series::getId,seriesId);
        Series series = this.getOne(lambdaQueryWrapper);
        series.setPortraitImage(coverImage);
        UpdateWrapper<Series> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",seriesId);
        this.update(series,updateWrapper);
    }

    @Override
    public void deleteSeries(Integer seriesId) {
        LambdaQueryWrapper<Series> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Series::getId,seriesId);
        this.remove(lambdaQueryWrapper);
    }
}
