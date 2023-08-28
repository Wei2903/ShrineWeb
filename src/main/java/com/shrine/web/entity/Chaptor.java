package com.shrine.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Chaptor implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    private Long seriesId;
    private Long creatorId;
    private Long number;
    private String title;
    private int finish;
    private String logo;
    private String thumb;
    private int status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

}
