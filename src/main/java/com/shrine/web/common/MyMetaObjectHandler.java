package com.shrine.web.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info(metaObject.toString());
        if (metaObject.hasSetter("createdAt")){
            metaObject.setValue("createdAt", LocalDateTime.now());
        }
        if (metaObject.hasSetter("updatedAt")){
            metaObject.setValue("updatedAt", LocalDateTime.now());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info(metaObject.toString());
        if (metaObject.hasSetter("updatedAt")){
            metaObject.setValue("updatedAt", LocalDateTime.now());
        }
    }
}
