package com.shrine.web.controller;

import com.shrine.web.entity.Series;
import com.shrine.web.service.SeriesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class SeriesController {
    @Autowired
    SeriesService seriesService;

    @GetMapping("/home")
    @ResponseBody
    public List<Series> getAllSeries(HttpServletResponse response){
        return seriesService.queryAllSeries();
    }

    @GetMapping("/home/{id}")
    @ResponseBody
    public Series getSeriesDetailById(@PathVariable("id") String id){
        Series series = seriesService.querySeriesDetail(Integer.parseInt(id));
        return series;
    }

}
