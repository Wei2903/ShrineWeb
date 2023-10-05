package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Author;
import com.shrine.web.entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserService  extends IService<User> {
    void saveNewUser (User newUser,String verifyToken,String hashedPassword);
    void sendVerificationEmail(User user);

    void sendResetPasswordEmail(User user);

    void updateVerifyStatus(Long userId, String password);

    void updateVerifyToken(String email, String verifyToken);

    void updateUsername(Long userId, String name);

    void updatePassword(Long userId, String password);

    User getUserByEmail(String email);
}
