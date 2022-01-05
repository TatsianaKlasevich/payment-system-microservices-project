package com.klasevich.itrex.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {  //todo
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**", "/v1/**")
                .permitAll()
                .antMatchers("/**")
                .authenticated();
//
//        http
//                    .csrf().disable()
//                    .authorizeRequests()
//                    .anyRequest()
//                    .permitAll();
    }
}
