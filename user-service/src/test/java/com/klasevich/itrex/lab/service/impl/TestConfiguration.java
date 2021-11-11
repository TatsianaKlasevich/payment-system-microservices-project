package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.klasevich.itrex.lab")
public class TestConfiguration {
    @Bean
    UserService userService() {
        return new UserServiceImpl();
    }
}