package com.shrine.web.admin.controller;

import com.shrine.web.admin.service.AdminSeriesService;
import com.shrine.web.entity.Series;
import com.shrine.web.service.SeriesService;
import com.shrine.web.utils.IOUtils;
import com.shrine.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class SeriesAdminController {

    @Autowired
    SeriesService seriesService;
    @Autowired
    AdminSeriesService adminSeriesService;

    @GetMapping("/admin/series")
    @ResponseBody
    public List<Series> getAllSeries(HttpServletResponse response){
        return seriesService.queryAllSeries();
    }

    @GetMapping("/admin/series/{id}")
    @ResponseBody
    public Series getSeriesDetailById(@PathVariable("id") String id){
        Series series = seriesService.querySeriesDetail(Integer.parseInt(id));
        return series;
    }


    @PostMapping("/admin/series/addSeries")
    public String addSeries(
            @RequestParam("Title") String title,
            @RequestParam("Description") String description,
            @RequestParam("CoverImage") MultipartFile coverImage) {
        boolean isAddSuccessful = false;
        try {
            isAddSuccessful = IOUtils.SaveCoverImage(title, coverImage);
            if (!isAddSuccessful){
                return "Series Folder Already Exist, Cannot Add New Series";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "File IO Error, Cannot Add New Series";
        }
        Series series = new Series();
        series.setTitle(title);
        series.setDes(description);
        series.setPortraitImage(coverImage.getOriginalFilename());
        //TODO: Series LOGO and Land Image Insert
        series.setLogo("logo not implemented yet");
        series.setLandImage("LandImage not implemented yet");
        series.setColorCode("");
        adminSeriesService.insertSeries(series);
        return "Add Series Successfully";
    }
}
