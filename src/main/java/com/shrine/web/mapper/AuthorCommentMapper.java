package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.AuthorComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorCommentMapper extends BaseMapper<AuthorComment> {
    List<AuthorComment> getAuthorCommentByPageId(Integer pageId);
}
