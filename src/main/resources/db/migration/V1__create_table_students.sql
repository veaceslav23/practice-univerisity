CREATE TABLE students
(
    student_id    NUMBER(6) PRIMARY KEY,
    first_name     VARCHAR2(20),
    last_name      VARCHAR2(25)        NOT NULL,
    email          VARCHAR2(25) UNIQUE NOT NULL,
    phone_number   VARCHAR2(20),
    faculty_id      NUMBER(6)     NOT NULL,
    CONSTRAINT faculty_id_fk FOREIGN KEY (faculty_id)
    REFERENCES faculties (faculty_id)
);

CREATE SEQUENCE students_seq NOCACHE;

ALTER TABLE students
    MODIFY student_id DEFAULT students_seq.nextval;