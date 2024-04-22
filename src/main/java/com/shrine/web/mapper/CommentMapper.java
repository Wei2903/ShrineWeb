package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Comment;
import com.shrine.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    // Check main/resources/mapper/CommentMapper.xml for actual implementation

    List<Comment> getCommentsByPageId(Integer PageId);

    List<Comment> getToAuthorCommentByPageIdAndAuthorCommentId(@Param("pageId") Integer PageId,
                                             @Param("AuthorCommentId") Integer authorCommentId);

    // Add comment under a chapter
    void addComment(Comment comment);
}
