DROP SCHEMA IF EXISTS payment_database CASCADE;
CREATE SCHEMA IF NOT EXISTS payment_database;

CREATE TABLE IF NOT EXISTS cards
(
    id              INT NOT NULL AUTO_INCREMENT,
    balance         DECIMAL,
    is_default      BOOLEAN,
    currency        VARCHAR(3),
    card_number     BIGINT,
    card_status     VARCHAR(25),
    expiration_date DATE,
    cvv_code        INT,
    user_id         INT,
    PRIMARY KEY (id)
--         FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS payments
(
    id                 INT AUTO_INCREMENT,
    sender             INT,
    recipient          INT,
    amount             DECIMAL,
    transaction_status VARCHAR(25),
    unp                BIGINT,
    purpose_of_payment VARCHAR(125),
    transaction_date   TIMESTAMP,
    bank_code          VARCHAR(28),
    card_id            INT,
    PRIMARY KEY (id),
    FOREIGN KEY (card_id) REFERENCES cards (id)
);