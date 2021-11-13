INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity,
                                  refresh_token_validity, resource_ids, authorized_grant_types, additional_information)
VALUES ('mobile', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/code',
        'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

INSERT INTO permissions (NAME)
VALUES ('create_user');
INSERT INTO permissions (NAME)
VALUES ('read_user');
INSERT INTO permissions (NAME)
VALUES ('update_user');
INSERT INTO permissions (NAME)
VALUES ('delete_user');
INSERT INTO permissions (NAME)
VALUES ('read_all_users');


INSERT INTO roles (NAME)
VALUES ('BANK_EMPLOYEE');
INSERT INTO roles (NAME)
VALUES ('CLIENT');

INSERT INTO PERMISSIONS_ROLES (PERMISSION_ID, ROLE_ID)
VALUES (1, 1); /*create bank_employee */
INSERT INTO PERMISSIONS_ROLES (PERMISSION_ID, ROLE_ID)
VALUES (2, 1); /* read bank_employee */
INSERT INTO PERMISSIONS_ROLES (PERMISSION_ID, ROLE_ID)
VALUES (3, 1); /* update bank_employee */
INSERT INTO PERMISSIONS_ROLES (PERMISSION_ID, ROLE_ID)
VALUES (4, 1); /* delete bank_employee */
INSERT INTO PERMISSIONS_ROLES (PERMISSION_ID, ROLE_ID)
VALUES (5, 1); /* read all bank_employee */
INSERT INTO PERMISSIONS_ROLES (PERMISSION_ID, ROLE_ID)
VALUES (1, 2); /* create client */
INSERT INTO PERMISSIONS_ROLES (PERMISSION_ID, ROLE_ID)
VALUES (2, 2); /* read client */
INSERT INTO PERMISSIONS_ROLES (PERMISSION_ID, ROLE_ID)
VALUES (3, 2); /* update client */

insert into users (username, password, email, enabled, account_non_expired, credentials_non_expired,
                   account_non_locked)
VALUES ('tanya', '{bcrypt}$2a$10$ODGwrk2ufy5d7T6afmACwOA/6j6rvXiP5amAMt1YjOQSdEw44QdqG', 'tanyanek@gmail.com', '1',
        '1', '1', '1');
insert into users (username, password, email, enabled, account_non_expired, credentials_non_expired,
                   account_non_locked)
VALUES ('oleg', '{bcrypt}$2a$10$ODGwrk2ufy5d7T6afmACwOA/6j6rvXiP5amAMt1YjOQSdEw44QdqG', 'oleg@gmail.com', '1',
        '1', '1', '1');
-- password - kpass

INSERT INTO ROLES_USERS (ROLE_ID, USER_ID)
VALUES (1, 1); /* tanya-bank_employee */
INSERT INTO ROLES_USERS (ROLE_ID, USER_ID)
VALUES (2, 2) /* oleg-client */ ;
