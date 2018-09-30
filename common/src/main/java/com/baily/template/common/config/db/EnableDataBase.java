package com.baily.template.common.config.db;

import com.baily.template.common.config.db.druid.EnableDruid;
import com.baily.template.common.config.db.multidatasource.EnableDataSource;
import com.baily.template.common.config.mybatis.EnableMybatis;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableDruid
@EnableDataSource
//@EnableMybatis
public @interface EnableDataBase {
}
