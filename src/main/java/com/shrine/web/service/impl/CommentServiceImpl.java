package com.shrine.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.entity.Comment;
import com.shrine.web.entity.User;
import com.shrine.web.mapper.CommentMapper;
import com.shrine.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl
        extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    @Autowired
    CommentMapper mapper;

    // Add comment under a chapter
    @Transactional
    @Override
    public void addComment(Comment comment) {
        mapper.addComment(comment);
    }
}
