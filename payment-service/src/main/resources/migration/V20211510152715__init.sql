CREATE TABLE IF NOT EXISTS cards
(
    id              INT         NOT NULL AUTO_INCREMENT,
    card_type       VARCHAR(25) NOT NULL,
    balance         DECIMAL,
    currency        VARCHAR(3)  NOT NULL,
    card_number     BIGINT      NOT NULL,
    card_status     VARCHAR(25) NOT NULL,
    expiration_date DATE        NOT NULL,
    cvv_code        INT         NOT NULL,
    PRIMARY KEY (id),
);

CREATE TABLE IF NOT EXISTS transactions
(
    id                 INT         NOT NULL AUTO_INCREMENT,
    sender             INT,
    recipient          INT,
    amount             DECIMAL     NOT NULL,
    transaction_status VARCHAR(25) NOT NULL,
    unp                BIGINT      NOT NULL,
    purpose_of_payment VARCHAR(125),
    transaction_date   TIMESTAMP   NOT NULL,
    bank_code          VARCHAR(28),
    card_id            INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (card_id) REFERENCES cards (id)
);