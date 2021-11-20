DROP SCHEMA IF EXISTS users_database CASCADE;
CREATE SCHEMA IF NOT EXISTS users_database;

DROP TABLE IF EXISTS users cascade;

CREATE TABLE IF NOT EXISTS users
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    email                    VARCHAR(25)           NOT NULL,
    name                     VARCHAR(30)           NOT NULL,
    second_name              VARCHAR(30)           NOT NULL,
    surname                  VARCHAR(30)           NOT NULL,
    date_of_birth            DATE                  NOT NULL,
    identity_passport_number VARCHAR(14)           NOT NULL,
    phone_number             VARCHAR(13)           NOT NULL,
    create_at                TIMESTAMP,
    updated_at               TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT UNIQUE_EMAIL UNIQUE (email),
    CONSTRAINT UNIQUE_PASSPORT_NUMBER UNIQUE (identity_passport_number)
);



