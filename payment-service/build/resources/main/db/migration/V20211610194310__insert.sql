INSERT INTO cards (balance, is_default, card_number, card_status, expiration_date, user_id)
VALUES (200, 1, '2345678934562345', 'ENABLED', '2024-09-01', 1),
       (0, 0, '2345357944562345', 'ENABLED', '2025-11-01', 1),
       (0, 0, '0234567893436745', 'IS_BLOCKED', '2022-05-01', 1),
       (1000, 1, '8053589456234548', 'ENABLED', '2023-12-01', 2),
       (500, 1, '8903568934562345', 'ENABLED', '2026-04-01', 3),
       (250, 1, '8903358180347045', 'ENABLED', '2024-01-01', 4);

INSERT INTO transactions (amount, email, transaction_type, card_id)
VALUES (400, 'klasevich.t@gmail.com', 'DEPOSIT', 1),
       (800, 'klasevich.t@gmail.com', 'DEPOSIT', 3),
       (1000, 'klasevich.t@gmail.com', 'DEPOSIT', 2),
       (700, 'klasevich.t@gmail.com', 'DEPOSIT', 4),
       (600, 'klasevich.t@gmail.com', 'DEPOSIT', 1);

INSERT INTO transactions (amount, email, transaction_type, card_id, unp, purpose_of_payment, bank_code)
VALUES (100, 'klasevich.t@gmail.com', 'PAYMENT', 1, 129403103, 'for fitness', 'JKLH0ELD0S0GCSJEN0NGLSNGISNL'),
       (50, 'klasevich.t@gmail.com', 'PAYMENT', 3, 123503103, 'for work', 'JKLH0ELD0S0G35678NGLSNG8ISNL'),
       (300, 'klasevich.t@gmail.com', 'PAYMENT', 2, 129446323, 'for maintenance', 'JKLH3456S0GCSJEN0DNGLS5NTGIS');

INSERT INTO transactions (amount, email, transaction_type, card_id, recipient_card_id)
VALUES (100, 'klasevich.t@gmail.com', 'TRANSFER', 1, 2),
       (50, 'klasevich.t@gmail.com', 'TRANSFER', 3, 1),
       (300, 'klasevich.t@gmail.com', 'TRANSFER', 2, 3);


