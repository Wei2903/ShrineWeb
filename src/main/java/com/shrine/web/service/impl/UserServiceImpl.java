package com.shrine.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.entity.User;
import com.shrine.web.mapper.UserMapper;
import com.shrine.web.service.UserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void saveNewUser(User newUser,String ApiKey, String token,String verifyToken,String hashedPassword) {
        newUser.setPassword(hashedPassword);
        newUser.setVerify(0);
        newUser.setVerifyToken(verifyToken);
        newUser.setApiKey(ApiKey);
        newUser.setToken(token);
        newUser.setRole(3);
        newUser.setProfileType(0);      //TBD
        newUser.setProfile("default");  //TBD
        newUser.setProfileId(1L);   //TBD
        newUser.setDefaultValue(0);     //TBD
        newUser.setIs_deleted(0);
        this.save(newUser);
    }

    @Override
    public void sendVerificationEmail(User user) {

        try{
            String toAddress = user.getEmail();
            String fromAddress = "noreply@shrinecomics.com";
            String senderName = "Shrine comics";
            String subject = "Shrine comics email verification";
            String content = "<h3>Welcome to the worlds of Shrine Comics!</h3>"
                    + "<br><span>Before you can start enjoying your manga, please verify your email by clicking the button below!</span>"
                    + "<br><br><a href=\"[[URL]]\">Click here to verify</a>"
                    + "<br><br>Link not working? Copy and paste this link into your browser: " + "[[URL]]"
                    + "<br><br><br><br><h5>Thanks,</h5><h4>Shrine Comics</h4>";
            log.info("内容写好了$$$$$$$$$$$$$$$$$$$$");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            log.info("MessageHelper创建好了$$$$$$$$$$$$$$$$$$$$$$$");

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            String verifyURL = "www.shrinecomics.com/verify?code=" + user.getVerifyToken()+"&email="+user.getEmail(); //url TBD
            content = content.replace("[[URL]]", verifyURL);

            helper.setText(content, true);

            mailSender.send(message);
            log.info("发送成功￥￥￥￥￥￥￥￥￥￥￥￥￥￥");
        } catch (UnsupportedEncodingException | MessagingException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateVerifyStatus(Long userId) {
        User user = new User();
        user.setVerify(1);
        user.setVerifyToken(null);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",userId);
        this.update(user,updateWrapper);

    }

    @Override
    public User getUserByApiKeyAndToken(String apiKey, String token) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getApiKey,apiKey).eq(User::getToken,token);
        return this.getOne(lqw);
    }


}
