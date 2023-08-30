package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Series;
import com.shrine.web.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeriesMapper  extends BaseMapper<Series> {
}
