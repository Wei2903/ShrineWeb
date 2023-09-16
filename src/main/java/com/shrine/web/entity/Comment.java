package com.shrine.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private Integer pageId;
    private Integer x;
    private Integer y;
    private Long userId;
    private String comment;
    private User user;
    private Integer authorCommentId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
