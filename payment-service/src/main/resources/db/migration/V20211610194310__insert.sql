INSERT INTO CARDS (balance, is_default, card_number, card_status, expiration_date, user_id)
VALUES (200, 1, '2345678934562345', 'ENABLED', '2024-09-01', 1),
       (0, 0, '2345357944562345', 'ENABLED', '2025-11-01', 1),
       (0, 0, '0234567893436745', 'IS_BLOCKED', '2022-05-01', 1),
       (1000, 1, '8053589456234548', 'ENABLED', '2023-12-01', 2),
       (500, 1, '8903568934562345', 'ENABLED', '2026-04-01', 3),
       (250, 1, '8903358180347045', 'ENABLED', '2024-01-01', 4);

INSERT INTO PAYMENTS(id, amount, email, payment_type, card_id)
VALUES (1, 400, 'klasevich.t@gmail.com', 'DEPOSIT', 1),
       (2, 600, 'klasevich.t@gmail.com', 'DEPOSIT', 1);

