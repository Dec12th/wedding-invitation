package com.baily.template.demo.spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("testBean")
@Data
public class TestBean {

    @Value("${http.connectTimeout}")
    long connectTimeout;

}
