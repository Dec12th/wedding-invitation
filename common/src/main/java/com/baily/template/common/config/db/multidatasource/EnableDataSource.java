package com.baily.template.common.config.db.multidatasource;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import({DynamicDataSource.class,MultiDataSourceAspect.class})
public @interface EnableDataSource {
}
