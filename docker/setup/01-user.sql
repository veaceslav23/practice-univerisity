ALTER SESSION SET CONTAINER=PDB;
CREATE USER veaceslav IDENTIFIED BY "veaceslav";
GRANT CREATE SESSION TO veaceslav;
GRANT CREATE TABLE, CREATE VIEW, CREATE SEQUENCE, CREATE SYNONYM TO veaceslav;
GRANT CREATE PROCEDURE TO veaceslav;
GRANT CREATE TRIGGER TO veaceslav;
GRANT UNLIMITED TABLESPACE TO veaceslav;