package com.shrine.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shrine.web.entity.Author;
import com.shrine.web.entity.Chapter;
import com.shrine.web.entity.Series;
import com.shrine.web.mapper.AuthorMapper;
import com.shrine.web.mapper.ChapterMapper;
import com.shrine.web.mapper.SeriesMapper;
import com.shrine.web.service.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Slf4j
@SpringBootApplication
@ServletComponentScan /* 用于扫描 @WebFilter */
@EnableTransactionManagement /* 用于控制事务 */
public class ShrineApp {
    public static void main(String[] args) {
        ApplicationContext ioc = SpringApplication.run(ShrineApp.class, args);
        log.info("project run success $$$$$$$$$$$$$$$$$$$$$$$$$");
    }
}
