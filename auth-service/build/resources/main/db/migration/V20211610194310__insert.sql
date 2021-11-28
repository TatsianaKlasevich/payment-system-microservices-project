INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity,
                                  refresh_token_validity, resource_ids, authorized_grant_types, additional_information)
VALUES ('mobile', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/code',
        'READ,WRITE', '3600', '10000', 'inventory,transaction', 'authorization_code,password,refresh_token,implicit',
        '{}');

INSERT INTO permissions (NAME)
VALUES ('create_user'),
       ('read_user'),
       ('update_user'),
       ('delete'),
       ('read_card'),
       ('create_card'),
       ('update_card');

INSERT INTO roles (NAME)
VALUES ('BANK_EMPLOYEE'),
       ('CLIENT');

INSERT INTO PERMISSIONS_ROLES (PERMISSION_ID, ROLE_ID)
VALUES (1, 1), /* create bank_employee */
       (2, 1), /* read user - bank_employee */
       (3, 1), /* update user - bank_employee */
       (4, 1), /* delete - bank_employee */
       (5, 1), /* read card - bank_employee */
       (6, 1), /* create - bank_employee */
       (7, 1), /* update user - bank_employee */
       (1, 2), /* create user - client */
       (2, 2), /* read user - client */
       (3, 2), /* update user - client */
       (5, 2), /* read card - client */
       (6, 2), /* create card - client */
       (7, 2); /* update card - client */

insert into users (username, password, email, enabled, account_non_expired, credentials_non_expired,
                   account_non_locked)
VALUES ('tanya', '{bcrypt}$2a$10$ODGwrk2ufy5d7T6afmACwOA/6j6rvXiP5amAMt1YjOQSdEw44QdqG', 'klasevich.t@gmail.com', '1',
        '1', '1', '1'),
       ('gleb', '{bcrypt}$2a$10$ODGwrk2ufy5d7T6afmACwOA/6j6rvXiP5amAMt1YjOQSdEw44QdqG', 'gleb@gmail.com', '1',
        '1', '1', '1');
-- password - kpass

INSERT INTO ROLES_USERS (ROLE_ID, USER_ID)
VALUES (1, 1), /* tanya-bank_employee */
       (2, 2) /* gleb-client */ ;
