DROP SCHEMA IF EXISTS auth_database CASCADE;
CREATE SCHEMA IF NOT EXISTS auth_database;

CREATE TABLE IF NOT EXISTS oauth_client_details
(
    client_id               VARCHAR(30)  NOT NULL,
    client_secret           VARCHAR(100) NOT NULL,
    web_server_redirect_uri VARCHAR(100) DEFAULT NULL,
    scope                   VARCHAR(100) DEFAULT NULL,
    access_token_validity   int          DEFAULT NULL,
    refresh_token_validity  int          DEFAULT NULL,
    resource_ids            VARCHAR(100) DEFAULT NULL,
    authorized_grant_types  VARCHAR(200) DEFAULT NULL,
    authorities             VARCHAR(100) DEFAULT NULL,
    additional_information  VARCHAR(200) DEFAULT NULL,
    autoapprove             VARCHAR(100) DEFAULT NULL,
    PRIMARY KEY (client_id)
);

CREATE TABLE IF NOT EXISTS permissions
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UNIQUE_PERMISSION_NAME UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT UNIQUE_ROLE_NAME UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS users
(
    id                      BIGINT       NOT NULL AUTO_INCREMENT,
    username                VARCHAR(30)  NOT NULL,
    password                VARCHAR(100) NOT NULL,
    email                   VARCHAR(30)  NOT NULL,
    enabled                 TINYINT(4)   NOT NULL,
    account_non_expired     TINYINT(4)   NOT NULL,
    credentials_non_expired TINYINT(4)   NOT NULL,
    account_non_locked      TINYINT(4)   NOT NULL,
    primary key (id),
    CONSTRAINT UNIQUE_USERNAME UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS permissions_roles
(
    permission_id BIGINT DEFAULT NULL,
    role_id       BIGINT DEFAULT NULL,
    PRIMARY KEY (permission_id, role_id),
    FOREIGN KEY (permission_id) REFERENCES permissions (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS roles_users
(
    role_id BIGINT DEFAULT NULL,
    user_id BIGINT DEFAULT NULL,
    PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- token store
CREATE TABLE IF NOT EXISTS oauth_client_token
(
    token_id          VARCHAR(256),
    token             LONGVARBINARY,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_access_token
(
    token_id          VARCHAR(256),
    token             LONGVARBINARY,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication    LONGVARBINARY,
    refresh_token     VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          LONGVARBINARY,
    authentication LONGVARBINARY
);

CREATE TABLE IF NOT EXISTS oauth_code
(
    code           VARCHAR(256),
    authentication LONGVARBINARY
);

CREATE TABLE IF NOT EXISTS oauth_approvals
(
    userId         VARCHAR(256),
    clientId       VARCHAR(256),
    scope          VARCHAR(256),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP
);


