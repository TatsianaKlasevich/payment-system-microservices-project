DROP SCHEMA IF EXISTS payment_database CASCADE;
CREATE SCHEMA IF NOT EXISTS payment_database;

CREATE TABLE IF NOT EXISTS cards
(
    id              BIGINT NOT NULL AUTO_INCREMENT,
    balance         DECIMAL,
    is_default      BOOLEAN,
    currency        VARCHAR(3),
    card_number     BIGINT,
    card_status     VARCHAR(25),
    expiration_date DATE,
    cvv_code        INT,
    user_id         BIGINT,
    create_at       TIMESTAMP,
    updated_at        TIMESTAMP,
    PRIMARY KEY (id)
--         FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS payments
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    sender             INT,
    recipient          INT,
    amount             DECIMAL,
    email              VARCHAR(40),
    transaction_status VARCHAR(25),
    unp                BIGINT,
    purpose_of_payment VARCHAR(125),
    transaction_date   TIMESTAMP,
    bank_code          VARCHAR(28),
    card_id            BIGINT,
    create_at            TIMESTAMP,
    updated_at           TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (card_id) REFERENCES cards (id)
);