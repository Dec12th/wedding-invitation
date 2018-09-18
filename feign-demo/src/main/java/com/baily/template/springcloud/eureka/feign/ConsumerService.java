package com.baily.template.springcloud.eureka.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: ConsumerService
 * @Description:
 * @author:YB
 * @date:2018年05月17日 15:03
 */
@FeignClient("oauth-resource")
public interface ConsumerService {

    @RequestMapping(value = "/oauth-resource/feign/test1",method = RequestMethod.GET)
    public String helloController();
}
