package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Author;
import com.shrine.web.entity.Series;

import java.util.List;

public interface SeriesService  extends IService<Series> {
    public List<Series> queryAllSeries();

}
