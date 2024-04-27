package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.User;

import java.util.Map;

public interface UserService  extends IService<User> {
    // Check impl/UserServiceImpl.java for actual implementation

    void saveNewUser (User newUser,String verifyToken,String hashedPassword);
    void sendVerificationEmail(User user);

    void sendResetPasswordEmail(User user);

    void updateVerifyStatus(Long userId, String password);

    void updateVerifyToken(String email, String verifyToken);

    void updateUsername(Long userId, String name);

    void updatePassword(Long userId, String password);

    void updateProfile(Long userId, Long profileId);

    User getUserByEmail(String email);
    void updateExp(Long userId, Integer exp);

    Double getExpPercentage(Long userId);

    int getUserLevel(Long userId);

    Long getUserCoins(Long userId);

    Map getUserStatus(Long userId);

}
