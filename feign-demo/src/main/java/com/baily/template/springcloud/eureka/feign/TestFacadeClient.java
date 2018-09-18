package com.baily.template.springcloud.eureka.feign;

import com.baily.com.baily.template.weddinginvitation.api.TestFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("wedding-invitation")
public interface TestFacadeClient extends TestFacade {
}
