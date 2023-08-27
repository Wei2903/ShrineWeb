package com.shrine.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan /* 用于扫描 @WebFilter */
@EnableTransactionManagement /* 用于控制事务 */
public class ShrineApp {
    public static void main(String[] args) {
        SpringApplication.run(ShrineApp.class,args);
        log.info("project run success...");
    }
}
