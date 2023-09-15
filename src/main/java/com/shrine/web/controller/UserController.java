package com.shrine.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shrine.web.entity.User;
import com.shrine.web.service.UserService;
import com.shrine.web.utils.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody User user){
        log.info("user: {}",user);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,user.getEmail());
        User one = userService.getOne(lqw);
        System.out.println(one);
        if(one == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account doesn't exist");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(user.getPassword(),one.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Password :"+user.getPassword()+" should be : "+one.getPassword());
        }

        // 生成 apiKey
        String apiKey = TokenGenerator.generateHmac(TokenGenerator.generateRandomKey(64),user.getEmail());
        // 生成 token
        String token = TokenGenerator.generateHmac(TokenGenerator.generateRandomKey(32),user.getEmail());

        // 设置 apiKey 和 token 到 User 对象
        one.setApiKey(apiKey);
        one.setToken(token);

        // 更新用户信息（包括 apiKey 和 token）到数据库
        userService.updateById(one);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("apiKey", apiKey);
        resultMap.put("token", token);
        // 返回用户信息，包括 apiKey 和 token
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody User user){
        log.info("user:{}",user);
        // 判断前端传来的数据是否正常
        if(user.getName().isEmpty()||user.getPassword().isEmpty()||user.getEmail().isEmpty()){
            return ResponseEntity.badRequest().body("All fields are required");
        }
        String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if(!user.getEmail().matches(emailRegex)){
            return ResponseEntity.badRequest().body("Invalid email");
        }
        if(user.getPassword().length()< 8){
            return ResponseEntity.badRequest().body("Password should be at least 8 characters long");
        }
        // 判断账号是否已存在
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,user.getEmail());
        User one = userService.getOne(lqw);
        if(one!=null){
            return ResponseEntity.badRequest().body("User already exist");
        }

        // 二者都满足则生成ApiKey, token和VerifyToken，密码加密并生成User对象
        String ApiKey = TokenGenerator.generateHmac(TokenGenerator.generateRandomKey(64),user.getEmail());
        String token = TokenGenerator.generateHmac(TokenGenerator.generateRandomKey(32),user.getEmail());
        String verifyToken = TokenGenerator.generateHmac(TokenGenerator.generateRandomKey(64),user.getEmail());


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        // 保存信息到数据库
        userService.saveNewUser(user,ApiKey,token,verifyToken,hashedPassword);
        // 发送验证邮件
        userService.sendVerificationEmail(user);
        System.out.println("sent$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        //验证成功
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/verifyRegistration")
    @ResponseBody
    public ResponseEntity<?> verify(@RequestParam("code")String verifyToken,@RequestParam("email")String email ){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,email);
        User one = userService.getOne(lqw);
        if(one.getVerifyToken().equals(verifyToken)){
            userService.updateVerifyStatus(one.getId());
            return ResponseEntity.ok("User has been verified");
        }else{
            return ResponseEntity.badRequest().body("invalid verification code");
        }
    }

    @RequestMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("apiKey") String apiKey, @RequestHeader("token") String token) {
        // 验证 apiKey 和 token 的有效性
        User user = userService.getUserByApiKeyAndToken(apiKey, token);

        if (user != null) {
            // 返回用户信息
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid apiKey or token");
        }
    }
}
