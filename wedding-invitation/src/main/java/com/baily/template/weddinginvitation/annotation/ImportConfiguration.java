package com.baily.template.weddinginvitation.annotation;

import com.baily.template.weddinginvitation.common.db.entity.User;
import org.springframework.context.annotation.Bean;

public class ImportConfiguration {

    @Bean
    public User createUser() {
        User user = new User();
        user.setUserName("葬爱家族-无心");
        return user;
    }
}
