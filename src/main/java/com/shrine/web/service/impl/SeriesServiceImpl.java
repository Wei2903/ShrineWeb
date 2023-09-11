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

    @Override
    public List<Series> queryAllSeries() {
        return seriesMapper.selectList(null);
    }
}
