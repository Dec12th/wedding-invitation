package com.baily.template.demo.spring;

import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spring/")
public class SpringController {

    @Value("${http.connectTimeout:2000}")
    long connectTimeout;

    /**
     * jsonBeanProperty=[{"someString":"hello","someInt":100},{"someString":"world!","someInt":200}]
     */
    @ApolloJsonValue("${jsonBeanProperty:[]}")
    List<JsonBean> jsonBeans;

    @RequestMapping(value = "getTimeout", method = RequestMethod.GET)
    public String getTimeout() {
        return String.valueOf(connectTimeout);
    }

    @RequestMapping(value = "getJsonBeans", method = RequestMethod.GET)
    public String getJsonBeans() {
        return jsonBeans.toString();
    }


    @Data
    private static class JsonBean {

        private String someString;
        private int someInt;
    }
}
