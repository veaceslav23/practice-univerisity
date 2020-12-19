CREATE TABLE administrators
(
    administrator_id    NUMBER(6) PRIMARY KEY,
    login     VARCHAR2(20) NOT NULL,
    password      VARCHAR2(100)        NOT NULL
);

CREATE SEQUENCE admins_seq NOCACHE;

ALTER TABLE administrators
    MODIFY administrator_id DEFAULT admins_seq.nextval;