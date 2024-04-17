package com.shrine.web.config;

import com.shrine.web.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //for interceptor
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .addPathPatterns("/user/**").excludePathPatterns("/user/login");
///*                .addPathPatterns("/user/collections/**")
//                .addPathPatterns("/user/store/**").addPathPatterns("user/user-info/**");*/
//
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

    }
}
