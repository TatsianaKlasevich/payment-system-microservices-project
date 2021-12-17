package com.klasevich.itrex.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerTestConfiguration implements ResourceServerConfigurer {

    @Override
    public void configure(ResourceServerSecurityConfigurer security) throws Exception {
        security.stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) {
    }

}

