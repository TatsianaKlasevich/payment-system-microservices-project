INSERT INTO users (email, password, name, second_name, surname, date_of_birth,
                   identity_passport_number, phone_number)
VALUES ('klasevich.t@gmail.com', '12345', 'Tanya', 'Vladimirovna',
        'Klasevich', '1986-09-20', '1214NK324545L', '+375447080684');
INSERT INTO users (email, password, name, second_name, surname, date_of_birth,
                   identity_passport_number, phone_number)
VALUES ('segrei@gmail.com', 'e12345', 'Tanya', 'Konstantinovich',
        'Petrov', '1989-09-11', '1214NK784545L', '+375443650684');

INSERT INTO roles (role)
VALUES ('CLIENT');
INSERT INTO roles (role)
VALUES ('BANK_EMPLOYEE');

-- INSERT INTO users_roles (user_id, role_id)
-- VALUES (1, 1);
-- INSERT INTO users_roles (user_id, role_id)
-- VALUES (2, 2); //todo

COMMIT;