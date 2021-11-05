DROP SCHEMA IF EXISTS auth_database CASCADE;
CREATE SCHEMA IF NOT EXISTS auth_database;

create table if not exists oauth_client_details
(
    client_id               varchar(255) not null,
    client_secret           varchar(255) not null,
    web_server_redirect_uri varchar(2048) default null,
    scope                   varchar(255)  default null,
    access_token_validity   int(11)       default null,
    refresh_token_validity  int(11)       default null,
    resource_ids            varchar(1024) default null,
    authorized_grant_types  varchar(1024) default null,
    authorities             varchar(1024) default null,
    additional_information  varchar(4096) default null,
    autoapprove             varchar(255)  default null,
    primary key (client_id)
);

create table if not exists permissions
(
    id   bigint not null auto_increment,
    name varchar(512) default null,
    primary key (id),
    CONSTRAINT UNIQUE_PERMISSION_NAME UNIQUE (name)
);

create table if not exists roles
(
    id   bigint not null auto_increment,
    name varchar(255) default null,
    primary key (id),
    CONSTRAINT UNIQUE_ROLE_NAME UNIQUE (name)
);

create table if not exists users
(
    id                    BIGINT        not null auto_increment,
    username              varchar(100)  not null,
    password              varchar(1024) not null,
    email                 varchar(1024) not null,
    enabled               tinyint(4)    not null,
    accountNonExpired     tinyint(4)    not null,
    credentialsNonExpired tinyint(4)    not null,
    accountNonLocked      tinyint(4)    not null,
    primary key (id),
    CONSTRAINT UNIQUE_USERNAME UNIQUE (username)
);

create table if not exists permissions_roles
(
    permission_id bigint default null,
    role_id       bigint default null,
    PRIMARY KEY (permission_id, role_id),
    FOREIGN KEY (permission_id) REFERENCES permissions (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

create table if not exists roles_users
(
    role_id BIGINT default null,
    user_id BIGINT default null,
    PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- token store
create table if not exists oauth_client_token
(
    token_id          VARCHAR(256),
    token             LONGVARBINARY,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256)
);

create table if not exists oauth_access_token
(
    token_id          VARCHAR(256),
    token             LONGVARBINARY,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication   LONGVARBINARY,
    refresh_token     VARCHAR(256)
);

create table if not exists oauth_refresh_token(
    token_id VARCHAR(256),
    token LONGVARBINARY,
    authentication LONGVARBINARY
);

create table if not exists oauth_code
(
    code           VARCHAR(256),
    authentication LONGVARBINARY
);

create table if not exists oauth_approvals
(
    userId         VARCHAR(256),
    clientId       VARCHAR(256),
    scope          VARCHAR(256),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP
);


