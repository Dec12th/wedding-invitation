package com.baily.template.weddinginvitation;

import com.baily.template.weddinginvitation.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    User user;

    @RequestMapping(value = "helloUser", method = RequestMethod.GET)
    public String helloUser() {
        return "hello," + (user == null ? "user is null" : user.getNickName());
    }
}
