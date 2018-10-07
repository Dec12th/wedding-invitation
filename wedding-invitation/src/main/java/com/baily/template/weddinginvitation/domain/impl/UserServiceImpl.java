package com.baily.template.weddinginvitation.domain.impl;

import com.baily.template.weddinginvitation.common.db.entity.User;
import com.baily.template.weddinginvitation.common.db.repository.UserRepository;
import com.baily.template.weddinginvitation.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @author:大贝
 * @date:2018年10月07日 20:48
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public void addMoreUsers() {
        User user1 = new User();
        user1.setUsername("123");
        user1.setName("123");
        user1.setPassword("123");
        userRepository.save(user1);
        User user2 = new User();
        user2.setUsername("234");
        user2.setName("123");
        user2.setPassword("123");
        userRepository.save(user2);
    }

    @Override
    public void addMoreList() {

        List userList = new ArrayList();
        User user1 = new User();
        user1.setUsername("345");
        user1.setName("123");
        user1.setPassword("123");
        userList.add(user1);
        User user2 = new User();
        user2.setUsername("456");
        user2.setName("123");
        user2.setPassword("123");
        userList.add(user2);
        userRepository.save(userList);
    }
}
