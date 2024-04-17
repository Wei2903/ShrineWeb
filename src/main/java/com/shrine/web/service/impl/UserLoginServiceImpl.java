package com.shrine.web.service.impl;

import com.shrine.web.entity.ExpOperations;
import com.shrine.web.mapper.UserLoginMapper;
import com.shrine.web.service.ExpOperationsService;
import com.shrine.web.service.UserLoginService;
import com.shrine.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class UserLoginServiceImpl
        implements UserLoginService {
    @Autowired
    UserLoginMapper userLoginMapper;
    @Autowired
    ExpOperationsService expOperationsService;
    @Autowired
    UserService userService;


    @Override
    public Boolean loginUpdate(Long userId) {
        // Update User Login Info Table
        LocalDateTime lastLoginDate = userLoginMapper.getLastLoginDate(userId);
        if (lastLoginDate == null){
            userLoginMapper.insertUserLogin(userId);
            // Update Login Exp
            ExpOperations expOperationsLogin = expOperationsService.getExpOperationsLogin();
            int exp = expOperationsLogin.getExp();
            userService.updateExp(userId, exp);
            return true;
        }
        if (isDayToday(lastLoginDate)){
            return false;
        }
        if (isDayBeforeToday(lastLoginDate)){
            userLoginMapper.updateUserLoginContinuedDays(userId);
        } else {
            userLoginMapper.clearUserLoginContinuedDays(userId);
        }
        // Update Login Exp
        ExpOperations expOperationsLogin = expOperationsService.getExpOperationsLogin();
        int exp = expOperationsLogin.getExp();
        userService.updateExp(userId, exp);
        return true;
    }

    public static boolean isDayBeforeToday(LocalDateTime dateToCheck) {
        LocalDateTime today = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(dateToCheck.toLocalDate(), today.toLocalDate());
        return daysBetween == 1;
    }

    public static boolean isDayToday(LocalDateTime dateToCheck) {
        LocalDateTime today = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(dateToCheck.toLocalDate(), today.toLocalDate());
        return daysBetween == 0;
    }
}
