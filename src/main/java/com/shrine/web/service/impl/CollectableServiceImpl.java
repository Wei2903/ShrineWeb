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

    @Override
    public List<Collectable> getCollectablesByUserId(Long userId) {
        return collectableMapper.queryCollectablesByUserId(userId);
    }
}
