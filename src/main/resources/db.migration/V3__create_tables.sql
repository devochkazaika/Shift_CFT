CREATE TABLE IF NOT EXISTS history (
    id SERIAL PRIMARY KEY,
    userid int,
--    amount int,
    transactiondate VARCHAR(15),
    type VARCHAR(12),
--    receiverphone int,
    maintenancenumber int
--    status VARCHAR(12)
);