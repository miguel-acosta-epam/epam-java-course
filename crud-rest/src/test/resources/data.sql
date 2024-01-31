-- Inserts for expenses table
INSERT INTO expenses (id, description, amount, date) VALUES
    (1, 'Groceries', 50.00, '2024-01-20'),
    (2, 'Dinner with friends', 30.00, '2024-01-21'),
    (3, 'Movie night', 15.00, '2024-01-22'),
    (4, 'Shopping spree', 120.00, '2024-01-23'),
    (5, 'Coffee with colleagues', 10.50, '2024-01-24');

-- Inserts for app_user table
INSERT INTO app_user (username, password, email, locked, disabled) VALUES
    ('admin', '$2y$10$gjtUC90R5AFl/hr8yNwH0uZ2Ge/Qma8PZ8.63EN7DWZRoLgVY/24y', 'admin@correo.com', false, false),
    ('customer', '$2y$10$bN3TZg6tlJa7kZ/GX9/Fd.AGYvlreajQc01dY/3.DFQnYXRtkOe6G', 'customer@correo.com', false, false);

-- Inserts for app_user_role table
INSERT INTO app_user_role (username, role, granted_date) VALUES
    ('admin', 'ADMIN', CURRENT_TIMESTAMP),
    ('customer', 'CUSTOMER', CURRENT_TIMESTAMP);
