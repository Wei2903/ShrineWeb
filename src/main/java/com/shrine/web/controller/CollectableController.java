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

    @GetMapping("/collectable")
    @ResponseBody
    public List<Collectable> getCollectables(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (user instanceof User) {
            User usr = (User) user;
            return collectableService.getCollectablesByUserId(usr.getId());
        }
        return null;
    }

}
