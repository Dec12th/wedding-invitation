package com.baily.template.common.config.jpa;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(MasterJpaRepositoryConfig.class)
public @interface EnableJpa {
}
