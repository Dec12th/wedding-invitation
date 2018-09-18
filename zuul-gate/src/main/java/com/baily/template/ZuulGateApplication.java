package com.baily.template;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName: Application
 * @Description:
 * @author:YB
 * @date:2018年01月02日 11:24
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulGateApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ZuulGateApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
