package com.shrine.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class VUEFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //Define the list of domain names that are allowed to be accessed
        List<String> allowedOrigins = Arrays.asList("http://localhost:8080", "http://localhost:8081");

        // Get the request source domain name
        String originHeader = request.getHeader("Origin");

        // Check whether the requested source domain name is in the allowed list
        if (allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
        }
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, userId,page");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}


