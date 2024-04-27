package com.shrine.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shrine.web.entity.Collectable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CollectableMapper extends BaseMapper<Collectable> {
    // Check main/resources/mapper/CollectableMapper.xml for actual implementation

    // Default function to get all avatars that user has
    List<Collectable> queryAvatarsByUserId(Long userId);

    // Default function to get all stickers that user has
    List<Collectable> queryStickersByUserId(Long userId);

    // Get all avatars that user has and sorted them using given parameter 'sortedBy'
    List<Collectable> queryAvatarsByUserIdAndSortedBy(@Param("userId") Long userId, @Param("sortedBy") String sortedBy);

    // Get all stickers that user has and sorted them using given parameter 'sortedBy'
    List<Collectable> queryStickersByUserIdAndSortedBy(@Param("userId") Long userId, @Param("sortedBy") String sortedBy);

}
