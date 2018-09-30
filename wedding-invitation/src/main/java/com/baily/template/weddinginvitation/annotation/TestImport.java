package com.baily.template.weddinginvitation.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 测试注解：测试自定义注解中@Import的作用
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ImportConfiguration.class})
public @interface TestImport {
}
