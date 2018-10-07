package com.baily.template.weddinginvitation.web.controller;

import com.baily.template.weddinginvitation.common.db.entity.User;
import com.baily.template.weddinginvitation.common.db.repository.UserRepository;
import com.baily.template.weddinginvitation.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: UserController
 * @Description:
 * @author:大贝
 * @date:2018年10月07日 20:46
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List findUserList() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "users/add", method = RequestMethod.POST)
    public User addUser(@RequestParam("username") String username, @RequestParam("name") String name, @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @PutMapping("/users/{id}")
    public User updUserById(@PathVariable Long id, @RequestParam("name") String name) {
        User user = userRepository.findOne(id);//先查出来，否则修改的时候会将其他request中没有的参数也给覆盖掉
        user.setName(name);
        return userRepository.save(user);//与保存是同一个方法

    }

}
