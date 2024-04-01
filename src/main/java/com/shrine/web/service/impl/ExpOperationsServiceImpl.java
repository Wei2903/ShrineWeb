package com.shrine.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shrine.web.entity.ExpOperations;
import com.shrine.web.mapper.ExpOperationsMapper;
import com.shrine.web.service.ExpOperationsService;
import org.springframework.stereotype.Service;

@Service
public class ExpOperationsServiceImpl extends ServiceImpl<ExpOperationsMapper, ExpOperations>
        implements ExpOperationsService {


    @Override
    public ExpOperations getExpOperationsPage() {
        LambdaQueryWrapper<ExpOperations> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ExpOperations::getOperation,"reading");
        return this.getOne(lambdaQueryWrapper);
    }
}
