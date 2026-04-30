# ახალი ბაზის შექმნა
CREATE DATABASE students_db_19;

# მაიესქიუელისთვის იმის თქმა თუ რომელ ბაზას ვიყენებთ
use students_db_19;

CREATE TABLE students
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL
);
