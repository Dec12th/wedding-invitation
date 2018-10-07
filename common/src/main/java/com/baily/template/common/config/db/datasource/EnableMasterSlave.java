package com.baily.template.common.config.db.datasource;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import({MasterConfig.class,Slave1Config.class})
public @interface EnableMasterSlave {
}
