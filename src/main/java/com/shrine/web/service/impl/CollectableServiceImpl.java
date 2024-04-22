package com.shrine.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.entity.Collectable;
import com.shrine.web.mapper.CollectableMapper;
import com.shrine.web.service.CollectableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectableServiceImpl
        extends ServiceImpl<CollectableMapper, Collectable>
        implements CollectableService {

    @Autowired
    CollectableMapper collectableMapper;

    // Default function to get all avatars that user has
    @Override
    public List<Collectable> getAvatarsByUserId(Long userId) {
        return collectableMapper.queryAvatarsByUserId(userId);
    }

    // Get all avatars that user has and sorted them using given parameter 'sortedBy'
    @Override
    public List<Collectable> getAvatarsByUserIdAndSortedBy(Long userId, String sortedBy) {
        return collectableMapper.queryAvatarsByUserIdAndSortedBy(userId, sortedBy);
    }

    // Default function to get all stickers that user has
    @Override
    public List<Collectable> getStickersByUserId(Long userId) {
        return collectableMapper.queryStickersByUserId(userId);
    }

    @Override
    public Collectable getCurrentAvatar(Long collectableId) {
        return collectableMapper.selectById(collectableId);
    }

    // Get all stickers that user has and sorted them using given parameter 'sortedBy'
    @Override
    public List<Collectable> getStickersByUserIdAndSortedBy(Long userId, String sortedBy) {
        return collectableMapper.queryStickersByUserIdAndSortedBy(userId, sortedBy);
    }
}
