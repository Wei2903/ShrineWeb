package com.shrine.web.controller;

import com.shrine.web.entity.Collectable;
import com.shrine.web.entity.User;
import com.shrine.web.service.CollectableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CollectableController {
    @Autowired
    CollectableService collectableService;

    @GetMapping("/avatars")
    @ResponseBody
    public List<Collectable> getAvatars(HttpServletRequest request) {
        try {
            Long userId = Long.parseLong(request.getHeader("userId"));
            return collectableService.getAvatarsByUserId(userId);
        }catch (Exception e){
            System.out.println("Cannot get User ID");
            return null;
        }
    }

    @GetMapping("/stickers")
    @ResponseBody
    public List<Collectable> getStickers(HttpServletRequest request) {

        try {
            Long userId = Long.parseLong(request.getHeader("userId"));
            System.out.println(userId);
            return collectableService.getStickersByUserId(userId);
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Cannot get User ID");
            return null;
        }
    }



}
