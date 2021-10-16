CREATE TABLE IF NOT EXISTS users
(
    id                       INT         NOT NULL AUTO_INCREMENT,
    email                    VARCHAR(25) NOT NULL,
    password                 VARCHAR(35) NOT NULL,
    name                     VARCHAR(25),
    second_name              VARCHAR(25),
    surname                  VARCHAR(25),
    date_of_birth            DATE,
    identity_passport_number VARCHAR(25),
    phone_number             VARCHAR(13),
    PRIMARY KEY (id),
    CONSTRAINT UNIQUE_EMAIL UNIQUE (email),
    CONSTRAINT UNIQUE_IDENTITY_PASSPORT_NUMBER UNIQUE (identity_passport_number)
);

CREATE TABLE IF NOT EXISTS roles
(
    id   INT         NOT NULL AUTO_INCREMENT,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

