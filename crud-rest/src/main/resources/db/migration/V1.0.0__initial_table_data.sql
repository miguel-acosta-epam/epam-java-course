INSERT INTO expenses (description, amount, date) VALUES
    ('Groceries', 50.00, '2024-01-20'),
    ('Dinner with friends', 30.00, '2024-01-21'),
    ('Movie night', 15.00, '2024-01-22'),
    ('Shopping spree', 120.00, '2024-01-23'),
    ('Coffee with colleagues', 10.50, '2024-01-24'),
    ('Weekend getaway', 200.00, '2024-01-25'),
    ('Fitness class', 25.00, '2024-01-26'),
    ('Lunch at the cafe', 18.50, '2024-01-27'),
    ('Book purchase', 35.00, '2024-01-28'),
    ('Tech gadgets', 80.00, '2024-01-29');

-- admin -> admin123
-- customer -> customer123
INSERT INTO app_user (username, password, email, locked, disabled) VALUES
    ('admin', '$2y$10$gjtUC90R5AFl/hr8yNwH0uZ2Ge/Qma8PZ8.63EN7DWZRoLgVY/24y', 'admin@correo.com', false, false),
    ('customer', '$2y$10$bN3TZg6tlJa7kZ/GX9/Fd.AGYvlreajQc01dY/3.DFQnYXRtkOe6G', 'customer@correo.com', false, false);

INSERT INTO app_user_role (username, role, granted_date) VALUES
    ('admin', 'ADMIN', CURRENT_TIMESTAMP),
    ('customer', 'CUSTOMER', CURRENT_TIMESTAMP);
