package com.klasevich.itrex.lab.config;

import com.klasevich.itrex.lab.service.FlywayService;
import com.klasevich.itrex.lab.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.klasevich.itrex.lab")
public class ApplicationContextConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FlywayService flywayService() {
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();
        return flywayService;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Session session() {
        return HibernateUtil.getSessionFactory().openSession();
    }
}
