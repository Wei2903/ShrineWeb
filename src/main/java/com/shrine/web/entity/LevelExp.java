package com.shrine.web.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LevelExp implements Serializable {

    private static final Long serialVersionUID = 1L;
    private int id;
    private int level;
    private int exp;
}
