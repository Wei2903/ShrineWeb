package com.shrine.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shrine.web.entity.Chapter;
import com.shrine.web.service.ChapterService;
import com.shrine.web.service.ExpOperationsService;
import com.shrine.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @Autowired
    UserService userService;

    @Autowired
    ExpOperationsService expOperationsService;

    @ResponseBody
    @GetMapping("/chapter/{chapterId}")
    public Chapter getDetailedChapter(@PathVariable("chapterId") String chapterId){
        Integer id = Integer.parseInt(chapterId);
        return chapterService.getComicPagesByChapterId(id);
    }



    @PostMapping("/experience/increase")
    @ResponseBody
    public ResponseEntity<?> addExp(@RequestHeader("userId") Long userId, @RequestHeader("page") int page){
        System.out.println("收到userId: "+userId);
        System.out.println("收到page: "+page);
        System.out.println("收到请求");
        int exp = expOperationsService.getExpOperationsPage().getExp();
        System.out.println("默认经验值： " +exp);
        int addExp = page * exp;
        System.out.println("增加经验值： "+addExp);
        userService.updateExp(userId,addExp);
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("success", true);
        return ResponseEntity.ok(responseJson);
    }


}
