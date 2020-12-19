CREATE TABLE faculties
(
    faculty_id    NUMBER(6) PRIMARY KEY,
    faculty_name     VARCHAR2(20)
);

CREATE SEQUENCE faculties_seq NOCACHE;

ALTER TABLE faculties
    MODIFY faculty_id DEFAULT faculties_seq.nextval;