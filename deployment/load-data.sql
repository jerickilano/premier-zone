-- This script will help you load your CSV data into the Railway PostgreSQL database
-- You can run this through Railway's database interface or using a database client

-- First, create the table if it doesn't exist (Railway should handle this automatically)
-- But just in case, here's the structure:

CREATE TABLE IF NOT EXISTS player_statistic (
    name VARCHAR(255) PRIMARY KEY,
    team VARCHAR(255),
    pos VARCHAR(10),
    nation VARCHAR(100),
    age INTEGER,
    mp INTEGER,
    starts INTEGER,
    min INTEGER,
    gls INTEGER,
    ast INTEGER,
    pk INTEGER,
    crdy INTEGER,
    crdr INTEGER,
    xg DECIMAL(5,2),
    xag DECIMAL(5,2)
);

-- To load your CSV data, you can:
-- 1. Use Railway's database interface to import the CSV
-- 2. Use a tool like pgAdmin to connect and import
-- 3. Use the COPY command (if you have direct database access)

-- Example COPY command (adjust path as needed):
-- COPY player_statistic FROM '/path/to/prem_stats.csv' DELIMITER ',' CSV HEADER;

-- Or insert some sample data for testing:
INSERT INTO player_statistic (name, team, pos, nation, age, mp, starts, min, gls, ast, pk, crdy, crdr, xg, xag) 
VALUES 
('Test Player 1', 'Arsenal', 'FW', 'England', 25, 20, 18, 1600, 10, 5, 2, 1, 0, 8.5, 12.3),
('Test Player 2', 'Chelsea', 'MF', 'Brazil', 28, 22, 20, 1800, 3, 8, 0, 2, 0, 4.2, 9.1)
ON CONFLICT (name) DO NOTHING;
