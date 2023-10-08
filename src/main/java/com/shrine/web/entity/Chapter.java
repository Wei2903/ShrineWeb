package com.shrine.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Chapter implements Serializable {
    private static final Long serialVersionUID = 1L;
    private static final String pathPrefix = "/comics/";
    private Long id;
    private Long seriesId;
    private Long number;
    private String title;
    private int finish;
    private String logo;
    private String thumb;
    private int status;
    private List<ComicPage> pages;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

}
