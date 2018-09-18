package com.baily.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: WebConfig
 * @Description:
 * @author:YB
 * @date:2018年04月09日 17:58
 */
@Configuration
public class WebConfig {
    @Bean
    public XssFilter xssFilter() {
        return new XssFilter();
    }
}
