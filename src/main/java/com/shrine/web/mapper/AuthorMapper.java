package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Author;
import com.shrine.web.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorMapper extends BaseMapper<Author> {
    List<Author> getAuthorBySeriesId(Integer id);
}
