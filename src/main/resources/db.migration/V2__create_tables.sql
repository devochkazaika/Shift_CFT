CREATE TABLE IF NOT EXISTS bills (
    id SERIAL PRIMARY KEY,
    userid int,
    othertwo int,
    maintenancenumber int,
    amountremains int,
    amount int,
    type VARCHAR(8),
    status VARCHAR(12)
);