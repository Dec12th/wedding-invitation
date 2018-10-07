package com.baily.template;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
//@EnableConfigurationProperties
//@TestImport
//@EnableDataBase
public class WeddingInvitationApplication {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WeddingInvitationApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context = springApplication.run(args);
//        System.out.println(context.getBean(User.class).getNickName());
//        System.out.println(context.getBean(DataSourceAutoConfiguration.class));
//        ScanClass1 injectClass = context.getBean(ScanClass1.class);
//        WeddingInvitationApplication application = context.getBean(WeddingInvitationApplication.class);
//        Map<String, Object> data = context.getBeansWithAnnotation(EnableDataBase.class);
//        EnableDataBase[] enableDataBases = application.getClass().getAnnotationsByType(EnableDataBase.class);
//        EnableDataBase enableDataBase = enableDataBases[0];
//        Class<? extends Annotation>[] annotationClasss = enableDataBase.value();
//        System.out.println(annotationClasss[0].getAnnotationsByType(Import.class));
//        System.out.println("========================="+application.toString());
//        injectClass.print();
    }
}
