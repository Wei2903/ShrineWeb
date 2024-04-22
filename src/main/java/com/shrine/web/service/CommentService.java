package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Comment;
import com.shrine.web.entity.User;

public interface CommentService extends IService<Comment> {
    // Check impl/CommentServiceImpl.java for actual implementation
    public void addComment(Comment comment);
}
