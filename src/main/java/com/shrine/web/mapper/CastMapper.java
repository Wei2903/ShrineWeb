package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Cast;
import com.shrine.web.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CastMapper  extends BaseMapper<Cast> {
    List<Cast> queryCastsBySeriesId(Integer id);
}
