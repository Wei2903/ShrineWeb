package com.shrine.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface UserLoginMapper {
    LocalDateTime getLastLoginDate(Long userId);

    int updateUserLoginContinuedDays(Long userIdd);

    int clearUserLoginContinuedDays(Long userId);

    int insertUserLogin(Long userId);
}
