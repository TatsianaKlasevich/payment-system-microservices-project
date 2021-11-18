DROP SCHEMA IF EXISTS users_database CASCADE;
CREATE SCHEMA IF NOT EXISTS users_database;

DROP TABLE IF EXISTS users cascade;

CREATE TABLE IF NOT EXISTS users
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    email                    VARCHAR(25)           NOT NULL,
    name                     VARCHAR(25),
    second_name              VARCHAR(25),
    surname                  VARCHAR(25),
    date_of_birth            DATE,
    identity_passport_number VARCHAR(25),
    phone_number             VARCHAR(13),
    create_at                TIMESTAMP,
    updated_at               TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT UNIQUE_EMAIL UNIQUE (email),
    CONSTRAINT UNIQUE_IDENTITY_PASSPORT_NUMBER UNIQUE (identity_passport_number)
);



