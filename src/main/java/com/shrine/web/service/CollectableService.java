package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Collectable;

import java.util.List;

public interface CollectableService extends IService<Collectable> {
    // Check impl/CollectableServiceImpl.java for actual implementation
    List<Collectable> getAvatarsByUserId(Long userId);

    List<Collectable> getAvatarsByUserIdAndSortedBy(Long userId, String sortedBy);

    List<Collectable> getStickersByUserId(Long userId);

    Collectable getCurrentAvatar(Long collectableId);

    List<Collectable> getStickersByUserIdAndSortedBy(Long userId, String sortedBy);
}
