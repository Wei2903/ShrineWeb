package com.shrine.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Cast implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private Long seriesId;
    private Long creatorId;
    private Long castId;
    private String title;
    private String des;
    private String image;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


}
