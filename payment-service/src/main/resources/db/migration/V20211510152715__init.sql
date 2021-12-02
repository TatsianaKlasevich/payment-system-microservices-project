DROP SCHEMA IF EXISTS payments_database CASCADE;
CREATE SCHEMA IF NOT EXISTS payments_database;

CREATE TABLE IF NOT EXISTS cards
(
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    balance         DECIMAL,
    is_default      BOOLEAN     NOT NULL,
    card_number     VARCHAR(16) NOT NULL,
    card_status     VARCHAR(15) NOT NULL,
    expiration_date DATE        NOT NULL,
    user_id         BIGINT      NOT NULL,
    create_at       TIMESTAMP,
    updated_at      TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT UNIQUE_CARD_NUMBER UNIQUE (card_number)
);

CREATE TABLE IF NOT EXISTS transactions
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    user_id            BIGINT,
    amount             DECIMAL               NOT NULL,
    email              VARCHAR(40),
    transaction_type   VARCHAR(25)           NOT NULL,
    unp                BIGINT,
    purpose_of_payment VARCHAR(125),
    bank_code          VARCHAR(28),
    card_id            BIGINT,
    recipient_card_id  BIGINT,
    create_at          TIMESTAMP,
    updated_at         TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (card_id) REFERENCES cards (id)
);