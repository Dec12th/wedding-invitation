package com.baily.template;

import com.baily.template.common.config.db.EnableDataBase;
import com.baily.template.weddinginvitation.annotation.TestImport;
import com.baily.template.weddinginvitation.annotation.customizeComponet.ScanClass1;
import com.baily.template.weddinginvitation.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @ClassName: Application
 * @Description:
 * @author:YB
 * @date:2018年01月02日 11:24
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties
@TestImport
@EnableDataBase
public class WeddingInvitationApplication {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WeddingInvitationApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context = springApplication.run(args);
        System.out.println(context.getBean(User.class).getNickName());
        System.out.println(context.getBean(DataSourceAutoConfiguration.class));
        ScanClass1 injectClass = context.getBean(ScanClass1.class);
        injectClass.print();
    }
}
