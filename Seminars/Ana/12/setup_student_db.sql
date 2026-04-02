-- ============================================
-- Hibernate Student Database Setup Script
-- ============================================
-- This script creates the student_db database
-- and initializes the students_table with sample data

-- Create the student_db database
CREATE DATABASE IF NOT EXISTS student_db;

-- Use the student_db database
USE student_db;

-- Create the students_table
CREATE TABLE IF NOT EXISTS students_table (
  id INT AUTO_INCREMENT PRIMARY KEY,
  student_id VARCHAR(255) NOT NULL UNIQUE,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL
);

-- Insert sample data
INSERT INTO students_table (student_id, first_name, last_name) VALUES
  ('STU001', 'John', 'Doe'),
  ('STU002', 'Jane', 'Smith'),
  ('STU003', 'Bob', 'Johnson');

-- Verify the data was inserted
SELECT * FROM students_table;