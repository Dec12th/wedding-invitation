package com.baily.template.weddinginvitation.api.impl;

import com.baily.com.baily.template.weddinginvitation.api.TestFacade;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFacadeImpl implements TestFacade {

//    @Autowired
//    private TestProperties testProperties;

    @Override
    public String test() {
        return "hello world";
    }
}
