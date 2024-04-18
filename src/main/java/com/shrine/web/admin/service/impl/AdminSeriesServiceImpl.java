package com.shrine.web.admin.service.impl;

import com.shrine.web.admin.service.AdminSeriesService;
import com.shrine.web.entity.Series;
import com.shrine.web.mapper.SeriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminSeriesServiceImpl implements AdminSeriesService {
    @Autowired
    SeriesMapper seriesMapper;

    @Override
    public void insertSeries(Series series) {
        seriesMapper.insertSeries(series);
    }
}
