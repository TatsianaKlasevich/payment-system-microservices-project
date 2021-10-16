package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.service.FlywayService;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.klasevich.itrex.lab.properties.Properties.*;

public abstract class BaseRepositoryTest {

    private final FlywayService flywayService;
    private final JdbcConnectionPool connectionPool;

    public BaseRepositoryTest() {
        flywayService = new FlywayService();
        connectionPool = JdbcConnectionPool.create(H2_URL, H2_USER, H2_PASSWORD);
    }

    @BeforeEach
    public void initDB() {
        flywayService.migrate();
    }

    @AfterEach
    public void cleanDB() {
        flywayService.clean();
    }

    public JdbcConnectionPool getConnectionPool() {
        return connectionPool;
    }
}
