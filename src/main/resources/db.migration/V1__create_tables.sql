--DROP TABLE users;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    walletid VARCHAR(255),
    password VARCHAR(255),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    fullname VARCHAR(255),
    email VARCHAR(255),
    phone INT,
    registrationdate DATE,
    lastupdatedate DATE,
    age INT
);

--create table if not exists check_create_table(id bigserial not null);