-- Create the database
CREATE DATABASE IF NOT EXISTS attendy_db;
USE attendy_db;

-- Drop old users table if it exists (optional)
DROP TABLE IF EXISTS users;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('STUDENT', 'TEACHER') NOT NULL
);
   -- SELECT * FROM attendy_db; 
  -- TRUNCATE TABLE attendy_db ;