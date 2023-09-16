package com.shrine.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuthorComment {
    private Integer id;
    private Integer pageId;
    private Integer x;
    private Integer y;
    private String comment;
    private Long authorId;
    private Author author;
    private List<Comment> comments;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
