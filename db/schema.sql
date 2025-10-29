-- MySQL schema (basic)
CREATE DATABASE IF NOT EXISTS examtimetable;
USE examtimetable;

CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL,
  department VARCHAR(100)
);

CREATE TABLE exams (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  course_code VARCHAR(50),
  course_name VARCHAR(200),
  department VARCHAR(100),
  start_time DATETIME,
  end_time DATETIME,
  room VARCHAR(100),
  type VARCHAR(50)
);
