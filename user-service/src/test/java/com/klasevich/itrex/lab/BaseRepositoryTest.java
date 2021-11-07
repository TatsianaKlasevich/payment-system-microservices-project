package com.klasevich.itrex.lab;

import com.klasevich.itrex.lab.config.ApplicationContextConfiguration;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class BaseRepositoryTest {
    private final ApplicationContext applicationContext;
    private final Flyway flyway;

    public BaseRepositoryTest() {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        flyway = applicationContext.getBean(Flyway.class);
    }

    @BeforeEach
    public void migrate() {
        flyway.migrate();
    }

    @AfterEach
    public void clean() {
        flyway.clean();
    }
}
