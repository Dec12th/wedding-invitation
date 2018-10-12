package com.baily.template.demo.spring;

import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/spring/")
public class SpringController {

    @Value("${http.connectTimeout:2000}")
    long connectTimeout;

    @Autowired
    TestBean testBean;

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

    @RequestMapping(value = "getProperty", method = RequestMethod.GET)
    public String getJsonBeans(HttpServletRequest request) {
        String result = testBean.getEnvValue(request.getQueryString());
        System.out.println(result);
        return "result:"+result;
    }


    @Data
    private static class JsonBean {

        private String someString;
        private int someInt;
    }
}
