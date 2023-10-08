package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.Collectable;

import java.util.List;

public interface CollectableService extends IService<Collectable> {
    List<Collectable> getCollectablesByUserId(Long userId);
}
