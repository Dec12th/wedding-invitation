package com.baily.template.common.config.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MyBatisConfig.class,MyBatisMapperScannerConfig.class})
public @interface EnableMybatis {
}
