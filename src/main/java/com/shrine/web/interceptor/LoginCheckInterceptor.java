package com.shrine.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.shrine.web.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override   //true = let in , false = intercept
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletRequest req = request;
        HttpServletResponse resp = response;

        String jwt = req.getHeader("token");

        if(StringUtils.isEmpty(jwt)){
            log.info("token is null");
            ResponseEntity responseEntity = ResponseEntity.badRequest().body("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(responseEntity);
            resp.getWriter().write(notLogin);
            return false;
        }

        try {
            JwtUtils.parseJwt(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("parse token failed");
            ResponseEntity responseEntity = ResponseEntity.badRequest().body("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(responseEntity);
            resp.getWriter().write(notLogin);
            return false;
        }

        log.info("legal token, let in");
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
