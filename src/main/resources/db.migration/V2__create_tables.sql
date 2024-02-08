CREATE TABLE IF NOT EXISTS bills (
    id SERIAL PRIMARY KEY,
    userid int,
    maintenancenumber int,
    amount int,
    type VARCHAR(8)
);