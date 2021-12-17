package com.klasevich.itrex.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
public class GatewayConfiguration extends WebSecurityConfigurerAdapter {  //todo
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .antMatchers("/**")
                .authenticated();
    }
}
