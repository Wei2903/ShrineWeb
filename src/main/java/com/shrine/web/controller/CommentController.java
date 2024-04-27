package com.shrine.web.controller;

import com.shrine.web.entity.Comment;
import com.shrine.web.entity.Message;
import com.shrine.web.entity.User;
import com.shrine.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    // Add comment under a chapter
    @GetMapping("/comment")
    @ResponseBody
    public Message comment(@RequestBody Comment comment, HttpServletRequest request){
        Object user = request.getSession().getAttribute("user");
        if (user instanceof User){
            String commentContent = comment.getComment();
            if (commentContent == null || "".equals(commentContent)){
                return Message.getMessage("Comment cannot be empty");
            }
            User usr = (User) user;
            System.out.println("Starting adding Comment:" + comment);
            commentService.addComment(comment);
            return Message.getMessage(usr.getName() + "make comment: {" + comment + "} successfully");
        }
        return Message.getMessage("Haven't Login yet");
    }

}
