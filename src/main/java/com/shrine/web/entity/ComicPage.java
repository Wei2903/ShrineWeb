package com.shrine.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ComicPage {
    private Integer id;
    private String imgPath;
    private Long chapterId;
    private Integer pageNum;
    @TableField(exist = false)
    private List<Comment> comments;
    @TableField(exist = false)
    private List<AuthorComment> authorComments;
    private Integer doubleSpread;
    private Integer isVisible;
    private Integer status; // if this page exists (or is deleted)

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


}
