package com.shrine.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.entity.Series;
import com.shrine.web.mapper.SeriesMapper;
import com.shrine.web.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl
        extends ServiceImpl<SeriesMapper, Series>
        implements SeriesService {

    @Autowired
    SeriesMapper seriesMapper;

    // Get all series to display on the mainpage
    @Override
    public List<Series> queryAllSeries() {
        return seriesMapper.getAllSeries();
    }

    // Get details of a series using its id
    @Override
    public Series querySeriesDetail(Integer id) {
        return seriesMapper.getSeriesDetailBySeriesId(id);
    }


}
