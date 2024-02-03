--DROP TABLE users;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    walletid Int,
    password VARCHAR(255),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    fullname VARCHAR(255),
    email VARCHAR(255),
    phone BIGINT,
    age INT
);

CREATE TABLE IF NOT EXISTS sessions (
    id SERIAL PRIMARY KEY,
    userid int,
    start DATE,
    expirationtime DATE,
    active int
);

CREATE TABLE IF NOT EXISTS wallets (
    id SERIAL PRIMARY KEY,
    userid int,
    amount int,
    lastupdate DATE
);

--CREATE TABLE IF NOT EXISTS history(
--    id SERIAL PRIMARY KEY,
--    userid int,
--    type VARCHAR(255),
--    transactionDate DATE,
--    lastupdate DATE
--);

--create table if not exists check_create_table(id bigserial not null);