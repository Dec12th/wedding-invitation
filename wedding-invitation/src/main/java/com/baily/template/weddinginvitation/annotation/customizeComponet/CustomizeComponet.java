package com.baily.template.weddinginvitation.annotation.customizeComponet;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomizeComponet {
    String value() default "";
}
