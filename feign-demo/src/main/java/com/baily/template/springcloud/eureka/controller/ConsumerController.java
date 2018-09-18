package com.baily.template.springcloud.eureka.controller;

import com.baily.template.springcloud.eureka.feign.ConsumerService;
import com.baily.template.springcloud.eureka.feign.TestFacadeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ConsumerController
 * @Description:
 * @author:YB
 * @date:2018年01月15日 22:41
 */
@RestController
@RequestMapping("feign")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Value("${test.name.world:demo}")
    private String world;

    @Autowired
    private TestFacadeClient testFacade;



    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public String helloController() {
        return consumerService.helloController()+"，99999999999";
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String helloController2() {
        return "Hello "+testFacade.test()+"!";
    }


}
