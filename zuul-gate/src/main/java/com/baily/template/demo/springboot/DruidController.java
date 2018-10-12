package com.baily.template.demo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/druid/")
public class DruidController {

    @Autowired
    DruidProperties druidProperties;

    @RequestMapping(value = "getDruidConfig",method = RequestMethod.GET)
    public String getDruidConfig() {
        return druidProperties.toString();
    }
}
