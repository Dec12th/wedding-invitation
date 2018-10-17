package com.baily.template.demo.spring;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("testBean")
@Data
public class TestBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Value("${http.connectTimeout}")
    long connectTimeout;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String getEnvValue(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }
}
