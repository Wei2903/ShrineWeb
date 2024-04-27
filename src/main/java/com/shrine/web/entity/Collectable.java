package com.shrine.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Collectable implements Serializable {
    private Long id;

    private int status;

    private String seriesName; // which series the image on the collectable is from
    private String artist; // which artist made the image on the collectable

    private int rarity;
    private String image;

    private int type; // 1 means this collectable is an avatar, 2 means this collectable is a sticker

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    public String getCollectablePath(){
        if(type == 1){
            return "/avatar/" + image;
        } else if (type == 2){
            return "/sticker/" + image;
        }
        return null;
    }
}
