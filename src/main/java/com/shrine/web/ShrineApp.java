package com.shrine.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shrine.web.entity.*;
import com.shrine.web.mapper.*;
import com.shrine.web.service.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Slf4j
@SpringBootApplication
@ServletComponentScan /* for scanning @WebFilter */
@ComponentScan
@EnableTransactionManagement
public class ShrineApp {
    public static void main(String[] args) {
        ApplicationContext ioc = SpringApplication.run(ShrineApp.class, args);
        log.info("project run success $$$$$$$$$$$$$$$$$$$$$$$$$");
    }
}
