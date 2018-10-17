package com.baily.template.demo.springboot;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "druid.config")
@Data
@ConditionalOnProperty("druid.config.enabled")
@Component("druidProperties")
//使用@ConfigurationProperties时，如果需要在Apollo配置变化时自动更新注入的值，需要配合使用EnvironmentChangeEvent或RefreshScope
//@RefreshScope
public class DruidProperties {

    private static final Logger logger = LoggerFactory.getLogger(DruidProperties.class);

    private String urlMapping;

    private boolean resetEnable;

    private String allow;

    private String deny;

    private String loginUsername;

    private String loginPassword;

    private String urlPatterns;

    private String exclusions;

    private boolean enabled;

    @PostConstruct
    private void initialize() {
        logger.info(
                "DruidProperties initialized - {}",
                this.toString());
    }

}
