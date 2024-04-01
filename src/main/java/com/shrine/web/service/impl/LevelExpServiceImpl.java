package com.shrine.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.entity.Collectable;
import com.shrine.web.entity.LevelExp;
import com.shrine.web.mapper.CollectableMapper;
import com.shrine.web.mapper.LevelExpMapper;
import com.shrine.web.service.CollectableService;
import com.shrine.web.service.LevelExpService;
import org.springframework.stereotype.Service;

@Service
public class LevelExpServiceImpl extends ServiceImpl<LevelExpMapper, LevelExp>
        implements LevelExpService {

}
