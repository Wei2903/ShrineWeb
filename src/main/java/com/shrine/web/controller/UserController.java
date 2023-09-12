package com.shrine.web.controller;

import com.shrine.web.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @PostMapping("/login")
    @ResponseBody
    public User login(HttpServletRequest request){
        return null;
    }
}
