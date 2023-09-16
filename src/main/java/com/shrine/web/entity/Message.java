package com.shrine.web.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Message {
    private String msg;
    private Map<String, Object> extend = new HashMap<>();

    public static Message getMessage(String msg){
        Message message = new Message();
        message.msg = msg;
        return message;
    }
}
