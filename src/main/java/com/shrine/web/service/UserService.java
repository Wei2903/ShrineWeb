package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Author;
import com.shrine.web.entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserService  extends IService<User> {
    void saveNewUser (User newUser, String ApiKey,String token,String verifyToken,String hashedPassword);
    void sendVerificationEmail(User user);

    void updateVerifyStatus(Long userId);

    User getUserByApiKeyAndToken(String apiKey,String token);
}
