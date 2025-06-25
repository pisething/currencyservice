DROP DATABASE IF EXISTS currencydb;
CREATE DATABASE currencydb;

-- Grant all privileges to `user` on currencydb
GRANT ALL PRIVILEGES ON currencydb.* TO 'user'@'%';

USE currencydb;

CREATE TABLE currencies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL,
    name VARCHAR(30) NOT NULL,
    status VARCHAR(20) NOT NULL
);
