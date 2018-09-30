package com.baily.template.weddinginvitation.annotation.customizeComponet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BeanScannerConfigurer.class)
public class CustomizeScanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(CustomizeScanTest.class);
        annotationConfigApplicationContext.refresh();
        ScanClass1 injectClass = annotationConfigApplicationContext.getBean(ScanClass1.class);
        injectClass.print();
    }
}