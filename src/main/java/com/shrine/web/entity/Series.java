package com.shrine.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Series implements Serializable {
    private static final Long serialVersionUID = 1L;
    private static String imgPathPrefix = "/comics";
    private Long id;
    private String portraitImage;
    private String landImage;
    private String des;
    private String title;
    private String logo;
    @TableField(exist = false)
    private List<Author> authors;
    @TableField(exist = false)
    private List<Chapter> chapters;
    @TableField(exist = false)
    private List<Cast> casts;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    private int status;
    private String colorCode;
    private String continueReadImage;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime chaptorUpdate;

    public String getPortraitImagePath(){
        if ((title != null && !"".equals(title))
                && (portraitImage != null && !"".equals(portraitImage))){
            String titleMidPath = title.replaceAll("\\p{Punct}", "");
                    titleMidPath = titleMidPath.replace(" ", "");
            return imgPathPrefix + "/" + titleMidPath + "/" + portraitImage;
        }
        return null;
    }
}
