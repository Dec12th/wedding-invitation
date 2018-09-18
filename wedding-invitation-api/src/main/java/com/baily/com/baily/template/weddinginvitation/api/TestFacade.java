package com.baily.com.baily.template.weddinginvitation.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("test")
public interface TestFacade {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String test();
}
