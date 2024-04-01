package com.shrine.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.entity.LevelExp;
import com.shrine.web.entity.User;
import com.shrine.web.mapper.UserMapper;
import com.shrine.web.service.LevelExpService;
import com.shrine.web.service.UserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    LevelExpService levelExpService;

    @Override
    @Transactional
    public void saveNewUser(User newUser,String verifyToken,String hashedPassword) {
        newUser.setPassword(hashedPassword);
        newUser.setVerify(0);
        newUser.setVerifyToken(verifyToken);
        newUser.setRole(3);
        newUser.setProfileType(0);      //TBD
        newUser.setProfile("default");  //TBD
        newUser.setProfileId(1L);   //TBD
        newUser.setDefaultValue(0);     //TBD
        newUser.setIs_deleted(0);
        newUser.setLevel(0);
        newUser.setExp(0);
        this.save(newUser);
    }

    @Override
    public void sendVerificationEmail(User user) {

        try{
            String toAddress = user.getEmail();
            String fromAddress = "noreply@shrinecomics.com";
            String senderName = "Shrine comics";
            String subject = "Shrine comics verification Code";
            String content = "<h3>Welcome to the worlds of Shrine Comics!</h3>"
                    + "<br><span>Before you can start enjoying your manga, please verify your email by entering the following code!</span>"
                    + "<br><br>[[CODE]]"
                    + "<br><br><br><br><h5>Thanks,</h5><h4>Shrine Comics</h4>";
            log.info("content created$$$$$$$$$$$$$$$$$$$$");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            log.info("MessageHelper created$$$$$$$$$$$$$$$$$$$$$$$");

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            String verifyToken = user.getVerifyToken();
            content = content.replace("[[CODE]]", verifyToken);
            helper.setText(content,true);
            mailSender.send(message);
            log.info("sent success$$$$$$$$$$$$$$$$$");
        } catch (UnsupportedEncodingException | MessagingException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendResetPasswordEmail(User user) {
        try{
            String toAddress = user.getEmail();
            String fromAddress = "noreply@shrinecomics.com";
            String senderName = "Shrine comics";
            String subject = "Shrine comics Reset Password";
            String content = "<h3>Dear [[USERNAME]], </h3>"
                    + "<br><span>We received a request to reset your password for Shrine Comics. Please find the verification code below:</span>"
                    + "<br><br><h3>[[CODE]]</h3>"
                    +"<br><br>If you did not initiate this request, please ignore this email. Your password will remain unchanged."
                    + "<br><br><br><h5>Thanks,</h5><h4>Shrine Comics</h4>";
            log.info("content created$$$$$$$$$$$$$$$$$$$$");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            log.info("MessageHelper created$$$$$$$$$$$$$$$$$$$$$$$");

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            String username = user.getName();
            String verifyToken = user.getVerifyToken();
            content = content.replace("[[USERNAME]]",username);
            content = content.replace("[[CODE]]", verifyToken);
            helper.setText(content,true);
            mailSender.send(message);
            log.info("send success$$$$$$$$$$$$$$$$$$$$$$$$");
        } catch (UnsupportedEncodingException | MessagingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void updateVerifyStatus(Long userId, String password) {
        LambdaQueryWrapper<User> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(User::getId,userId);
        User user = this.getOne(lqw1);
        user.setVerify(1);
        user.setVerifyToken("");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",userId);

        log.info("before update:" + this.getOne(lqw1));
        this.update(user,updateWrapper);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId,userId);
        log.info("after update:" + this.getOne(lqw));
        log.info("update success");

    }

    @Override
    @Transactional
    public void updateVerifyToken(String email, String verifyToken){
        LambdaQueryWrapper<User> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(User::getEmail,email);
        User user = this.getOne(lqw1);
        user.setVerifyToken(verifyToken);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("email",email);
        this.update(user,updateWrapper);


    }

        @Override
        @Transactional
        public void updateUsername(Long userId, String name) {

            LambdaQueryWrapper<User> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(User::getId,userId);
            User user = this.getOne(lqw1);
            log.info("name:" + name);
            user.setName(name);
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id",userId);
            this.update(user,updateWrapper);
        }

    @Override
    public void updatePassword(Long userId, String password) {
        LambdaQueryWrapper<User> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(User::getId,userId);
        User user = this.getOne(lqw1);
        log.info("name:"+user);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        user.setVerifyToken("");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",userId);
        this.update(user,updateWrapper);
    }

    @Override
    public void updateProfile(Long userId, Long profileId) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,userId);
        User user = this.getOne(lambdaQueryWrapper);
        user.setProfileId(profileId);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",userId);
        this.update(user,updateWrapper);
    }

    @Override
    public User getUserByEmail(String email) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getEmail,email);
        return this.getOne(lqw);
    }



    @Override
    public void updateExp(Long userId, Integer exp) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId,userId);
        User user = this.getOne(lqw);
        LambdaQueryWrapper<LevelExp> lqw1 = new LambdaQueryWrapper<>();
        int max = 22;
        lqw1.eq(LevelExp::getLevel,(user.getLevel()+1)>max?22:user.getLevel()+1);
        LevelExp levelExp = levelExpService.getOne(lqw1);
        user.setExp(user.getExp()+exp);
        if(user.getExp()>=levelExp.getExp()){
            user.setExp(user.getExp() - levelExp.getExp());
            user.setLevel(user.getLevel()+1);
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",userId);
        this.update(user,updateWrapper);
    }


}