CREATE TABLE IF NOT EXISTS app_user (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(50),
    locked BOOLEAN NOT NULL,
    disabled BOOLEAN NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS app_user_role (
    username VARCHAR(20) NOT NULL,
    role VARCHAR(20) NOT NULL,
    granted_date TIMESTAMP,
    PRIMARY KEY (username, role)
);

CREATE TABLE IF NOT EXISTS expenses (
    id BIGINT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    date DATE NOT NULL
);
