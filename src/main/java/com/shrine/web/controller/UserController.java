package com.shrine.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shrine.web.entity.User;
import com.shrine.web.service.UserService;
import com.shrine.web.utils.JwtUtils;
import com.shrine.web.utils.TokenGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ResponseEntity<?> login(@RequestBody User user, HttpServletRequest request){ //
        log.info("user: {}",user);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,user.getEmail());
        User one = userService.getOne(lqw);
        // Add User to Session
        HttpSession session = request.getSession();
        session.setAttribute("user", one);
        System.out.println(one);
        if(one == null || one.getVerify()==0){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(user.getPassword(),one.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password");
        }

        //Generate jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("email",one.getEmail());
        claims.put("profile",one.getProfile());
        String jwt = JwtUtils.generateJwt(claims);

        //Generate Json Object
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("success", true);
        responseJson.put("username",one.getName());
        responseJson.put("profile",one.getProfile());
        responseJson.put("jwt", jwt);

        return ResponseEntity.ok(responseJson);
    }

    @PostMapping("send-verification")
    @ResponseBody
    public ResponseEntity<?> sendVerification (@RequestBody User user){
        log.info("user:{}",user);
        // Check if user already exist
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,user.getEmail());
        User one = userService.getOne(lqw);
        if(one!=null && one.getVerify()==1){
            return ResponseEntity.badRequest().body("Email");
        }
        String verifyToken = TokenGenerator.generateVerificationCode();                     /* */
        String defaultPassword = TokenGenerator.generateRandomKey(32);

        if(one==null){
            // Store user info to database
            userService.saveNewUser(user,verifyToken,defaultPassword);
        }else{
            userService.updateVerifyToken(user.getEmail(),verifyToken);
        }

        // Send verification code to email
        user.setVerifyToken(verifyToken);
        userService.sendVerificationEmail(user);
        System.out.println("sent$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("success", true);
        return ResponseEntity.ok(responseJson);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody User user){
        log.info("user:{}",user);
//        // 判断前端传来的数据是否正常
//        if(user.getName().isEmpty()||user.getPassword().isEmpty()||user.getEmail().isEmpty()){
//            return ResponseEntity.badRequest().body("All fields are required");
//        }
//        String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//        if(!user.getEmail().matches(emailRegex)){
//            return ResponseEntity.badRequest().body("Invalid email");
//        }
//        if(user.getPassword().length()< 8){
//            return ResponseEntity.badRequest().body("Password should be at least 8 characters long");
//        }
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,user.getEmail());
        User one = userService.getOne(lqw);

        if(one==null||one.getVerify()==1){
            return ResponseEntity.badRequest().body("noCode");
        }
        else if(one.getVerifyToken().equals(user.getVerifyToken())){

            userService.updateVerifyStatus(one.getId(), user.getPassword());
            userService.updateUsername(one.getId(), user.getName());
            Map<String, Object> responseJson = new HashMap<>();
            responseJson.put("success", true);
            return ResponseEntity.ok(responseJson);
        }else{
            return ResponseEntity.badRequest().body("invalid");
        }
    }

    @PostMapping("/send-reset-password")
    @ResponseBody
    public ResponseEntity<?> sendResetPassword(@RequestBody User user){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,user.getEmail());
        User one = userService.getOne(lqw);
        if(one==null || one.getVerify()==0){
            return ResponseEntity.badRequest().body("noCode");
        }
        String verifyToken = TokenGenerator.generateVerificationCode();                     /* */
        userService.updateVerifyToken(user.getEmail(),verifyToken);
        // Send verification code to email
        one.setVerifyToken(verifyToken);
        userService.sendResetPasswordEmail(one);
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("success", true);
        return ResponseEntity.ok(responseJson);
    }

    @PostMapping("/reset-password")
    @ResponseBody
    public ResponseEntity<?> ResetPassword(@RequestBody User user){
        log.info("user:{}",user);
//        // 判断前端传来的数据是否正常
//        if(user.getName().isEmpty()||user.getPassword().isEmpty()||user.getEmail().isEmpty()){
//            return ResponseEntity.badRequest().body("All fields are required");
//        }
//        String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//        if(!user.getEmail().matches(emailRegex)){
//            return ResponseEntity.badRequest().body("Invalid email");
//        }
//        if(user.getPassword().length()< 8){
//            return ResponseEntity.badRequest().body("Password should be at least 8 characters long");
//        }
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,user.getEmail());
        User one = userService.getOne(lqw);
        if(one==null||one.getVerify()==0){
            return ResponseEntity.badRequest().body("noCode");
        }
        if(one.getVerifyToken().equals(user.getVerifyToken())){
            userService.updatePassword(one.getId(), user.getPassword());
            Map<String, Object> responseJson = new HashMap<>();
            responseJson.put("success", true);
            return ResponseEntity.ok(responseJson);
        }else{
            return ResponseEntity.badRequest().body("invalid");
        }
    }




//    @RequestMapping("/user-info")
//    public ResponseEntity<?> getUserInfo(@RequestHeader("token") String token) {
//        try {
//            // 解析JWT令牌获取用户信息
//            Claims claims = JwtUtils.parseJwt(token);
//            String email = claims.get("email", String.class);
//
//            // 使用userId去数据库中查询用户信息
//            User user = userService.getUserByEmail(email);
//
//            if (user != null) {
//                // 返回用户信息
//                return ResponseEntity.ok(user);
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
//            }
//        } catch (Exception e) {
//            // 处理解析JWT令牌异常
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
//        }
//    }

    public Boolean checkJWT(@RequestHeader("token") String token){
        return JwtUtils.checkJWT(token);
    }
}
