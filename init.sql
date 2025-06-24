DROP DATABASE IF EXISTS currency_db;
CREATE DATABASE currency_db;

USE currency_db;

CREATE TABLE currencies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL,
    name VARCHAR(30) NOT NULL,
    status VARCHAR(20) NOT NULL
);
