package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Series;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeriesMapper  extends BaseMapper<Series> {

    // Get all series to display on the mainpage
    List<Series> getAllSeries();

    // Get details of a series using its id
    Series getSeriesDetailBySeriesId(Integer id);

    // Used in Admin Panel, insert series into the database
    void insertSeries(Series series);
}
