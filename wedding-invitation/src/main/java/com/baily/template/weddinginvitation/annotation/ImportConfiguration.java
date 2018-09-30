package com.baily.template.weddinginvitation.annotation;

import com.baily.template.weddinginvitation.user.model.User;
import org.springframework.context.annotation.Bean;

public class ImportConfiguration {

    @Bean
    public User createUser() {
        User user = new User();
        user.setNickName("葬爱家族-无心");
        return user;
    }
}
