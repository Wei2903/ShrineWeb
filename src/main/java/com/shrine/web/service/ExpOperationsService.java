package com.shrine.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shrine.web.entity.ExpOperations;
import com.shrine.web.entity.LevelExp;

public interface ExpOperationsService extends IService<ExpOperations> {

    ExpOperations getExpOperationsPage();
}
