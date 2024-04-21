package com.shrine.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Series;

public interface AdminSeriesService extends IService<Series> {

    public void insertSeries(Series series);

    public void updateSeriesTitle(Long seriesId, String title);
    public void updateSeriesDescription(Long seriesId, String description);
    public void updateSeriesCoverImage(Long seriesId, String coverImage);

}
