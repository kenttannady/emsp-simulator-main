-- Initialize database for eMSP Simulator
-- This script runs when MySQL container starts for the first time

USE emsp_simulator;

-- Set character set
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- Create tables if they don't exist (Spring Boot JPA will handle this with ddl-auto=update)
-- This file can be used for initial data setup if needed

-- Example initial data (optional)
-- INSERT INTO some_table (column1, column2) VALUES ('value1', 'value2');

SET FOREIGN_KEY_CHECKS = 1;
