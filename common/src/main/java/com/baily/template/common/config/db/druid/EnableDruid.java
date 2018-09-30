package com.baily.template.common.config.db.druid;

import com.baily.template.common.config.db.datasource.EnableMasterSlave;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableMasterSlave
@Import(DruidConfig.class)
public @interface EnableDruid {
}
