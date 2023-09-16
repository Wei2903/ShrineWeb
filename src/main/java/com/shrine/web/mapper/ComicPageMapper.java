package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.ComicPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComicPageMapper extends BaseMapper<ComicPage> {
    List<ComicPage> getComicPagesByChapterId(Integer chapterId);
}
