-- Step 1: Create the Database
CREATE DATABASE BankDB;

-- Step 2: Use the Database
USE BankDB;

-- Step 3: Create the BankAccounts Table
CREATE TABLE BankAccounts (
    accountNumber VARCHAR(20) PRIMARY KEY,
    accountHolderName VARCHAR(100) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
);

-- Step 4: Insert Sample Data into the BankAccounts Table
INSERT INTO BankAccounts (accountNumber, accountHolderName, balance) VALUES
('123456789', 'John Doe', 1000.00),
('987654321', 'Jane Smith', 500.50),
('555555555', 'Alice Johnson', 750.75),
('444444444', 'Bob Brown', 250.00);

-- Step 5: Query to Retrieve All Data from the BankAccounts Table
SELECT * FROM BankAccounts;