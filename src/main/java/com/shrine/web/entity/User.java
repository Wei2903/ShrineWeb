package com.shrine.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    private static final Long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String email;
    private String password;
//    private String apiKey;
//    private String token;
    private int role;
    private int profileType;
    private int verify;     //1 = verified 0 = unverified
    private String verifyToken;
    private String profile;
    private Long profileId;
    private int defaultValue;
    private int is_deleted;
    private int level;
    private int exp;




    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
