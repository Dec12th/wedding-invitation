package com.baily.template;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: Application
 * @Description:
 * @author:YB
 * @date:2018年01月02日 11:24
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties
public class WeddingInvitationApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WeddingInvitationApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
