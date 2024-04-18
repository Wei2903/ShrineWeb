package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Series;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeriesMapper  extends BaseMapper<Series> {
    List<Series> getAllSeries();

    Series getSeriesDetailBySeriesId(Integer id);

    void insertSeries(Series series);
}
