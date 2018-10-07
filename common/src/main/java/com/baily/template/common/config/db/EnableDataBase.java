package com.baily.template.common.config.db;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@EnableDruid
//@EnableDataSource
//@EnableJpa
//@EnableMybatis
public @interface EnableDataBase {
    Class<? extends Annotation>[] value() default {};
}
