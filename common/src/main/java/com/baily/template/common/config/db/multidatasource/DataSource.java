package com.baily.template.common.config.db.multidatasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: DataSource
 * @Description:
 * @author:YB
 * @date:2018年01月03日 13:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
	
	String value() default "";	//注解时指定，不可变更
	
	boolean manual() default false;	//手动指定，通过方法参数改动
	
}
