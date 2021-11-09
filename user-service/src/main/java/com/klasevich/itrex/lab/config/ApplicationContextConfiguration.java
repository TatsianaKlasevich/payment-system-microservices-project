package com.klasevich.itrex.lab.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan("com.klasevich.itrex.lab")
public class ApplicationContextConfiguration {
    private static final String DATABASE_DRIVER = "database.driver";
    private static final String DATABASE_URL = "database.url";
    private static final String DATABASE_USERNAME = "database.username";
    private static final String DATABASE_PASSWORD = "database.password";
    private static final String MIGRATION_LOCATION = "migration.location";
    private static final String SCAN_PACKAGE = "scan.package";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String SHOW_SQL = "hibernate.show.sql";
    private static final String FORMAT_SQL = "hibernate.format.sql";

    @Autowired
    private Environment env;

    @Bean
    @DependsOn("flyway")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty(DATABASE_DRIVER));
        dataSource.setUrl(env.getProperty(DATABASE_URL));
        dataSource.setUsername(env.getProperty(DATABASE_USERNAME));
        dataSource.setPassword(env.getProperty(DATABASE_PASSWORD));
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource(env.getProperty(DATABASE_URL),
                        env.getProperty(DATABASE_USERNAME),
                        env.getProperty(DATABASE_PASSWORD))
                .locations(env.getProperty(MIGRATION_LOCATION))
                .load();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(env.getProperty(SCAN_PACKAGE));
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
        hibernateProperties.put(SHOW_SQL, env.getProperty(SHOW_SQL));
        hibernateProperties.put(FORMAT_SQL, env.getProperty(FORMAT_SQL));
        return hibernateProperties;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
