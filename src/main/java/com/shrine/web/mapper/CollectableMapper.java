package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Collectable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CollectableMapper extends BaseMapper<Collectable> {
    List<Collectable> queryCollectablesByUserId(Long userId);
}
