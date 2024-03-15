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
        Object user = request.getSession().getAttribute("user");
        if (user instanceof User) {
            User usr = (User) user;
            return collectableService.getAvatarsByUserId(usr.getId());
        }
        return null;
    }

    @GetMapping("/stickers")
    @ResponseBody
    public List<Collectable> getStickers(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (user instanceof User) {
            User usr = (User) user;
            return collectableService.getStickersByUserId(usr.getId());
        }
        return null;
    }



}
