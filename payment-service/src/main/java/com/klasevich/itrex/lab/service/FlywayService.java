package com.klasevich.itrex.lab.service;

import org.flywaydb.core.Flyway;

import static com.klasevich.itrex.lab.properties.Properties.*;


public class FlywayService {
    private Flyway flyway;

    public FlywayService() {
        init();
    }

    public void migrate() {
        flyway.migrate();
    }

    public void clean() {
        flyway.clean();
    }

    private void init() {
        flyway = Flyway.configure()
                .dataSource(H2_URL, H2_USER, H2_PASSWORD)
                .locations(MIGRATION_LOCATION)
                .load();
    }
}
