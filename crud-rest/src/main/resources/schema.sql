CREATE TABLE IF NOT EXISTS app_user (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(50),
    locked TINYINT NOT NULL,
    disabled TINYINT NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS app_user_role (
    username VARCHAR(20) NOT NULL,
    role VARCHAR(20) NOT NULL,
    granted_date TIMESTAMP,
    PRIMARY KEY (username, role)
);

CREATE TABLE IF NOT EXISTS expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL
);

