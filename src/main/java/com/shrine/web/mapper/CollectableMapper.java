package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Collectable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CollectableMapper extends BaseMapper<Collectable> {
    List<Collectable> queryAvatarsByUserId(Long userId);

    List<Collectable> queryStickersByUserId(Long userId);

    List<Collectable> queryAvatarsByUserIdAndSortedBy(@Param("userId") Long userId, @Param("sortedBy") String sortedBy);

    List<Collectable> queryStickersByUserIdAndSortedBy(@Param("userId") Long userId, @Param("sortedBy") String sortedBy);

}
