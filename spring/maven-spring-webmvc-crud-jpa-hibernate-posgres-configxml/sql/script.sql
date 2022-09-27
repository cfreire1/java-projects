--============================================================TABLAS
--tabla de productos
CREATE TABLE PRODUCT (
ID INTEGER PRIMARY KEY NOT NULL,
NAME VARCHAR (255),
DESCRIPTION VARCHAR(255),
PRICE DECIMAL(15,2),
STOCK INTEGER,
CREATE_AT timestamp
);


--============================================================SEQUENCE
CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;



