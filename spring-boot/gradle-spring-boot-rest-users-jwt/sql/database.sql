-- DROP TABLE USERS
-- DROP TABLE PHONES
--============================================================
-- scrtip de base datos para revisar estructura, la aplicacion rest utiliza y crea la BD en memoria cuando se ejecuta.
--============================================================Base datos
CREATE TABLE PHONES
(
    NUMBER BIGINT PRIMARY KEY NOT NULL,
    ID_USERS BINARY (255),
    CITY_CODE VARCHAR (30),
    COUNTRY_CODE VARCHAR (30),
    FOREIGN KEY (ID_USERS) REFERENCES USERS (ID)
);

CREATE TABLE USERS
(
    ID BINARY (255) PRIMARY KEY NOT NULL,
    NAME VARCHAR (255),
    EMAIL VARCHAR (255),
    PASSWORD VARCHAR (255),
    CREATED TIMESTAMP,
    MODIFIED TIMESTAMP,
    LAST_LOGIN TIMESTAMP,
    TOKEN VARCHAR (255),
    IS_ACTIVE BOOLEAN
);

--============================================================datos (Utilizar para pruebas)
INSERT INTO PHONES(NUMBER, CITY_CODE, COUNTRY_CODE)
VALUES (928884736,
        'SCL',
        '+56');
INSERT INTO USERS( ID, NAME, EMAIL, PASSWORD, CREATED, MODIFIED, LAST_LOGIN,TOKEN, IS_ACTIVE)
VALUES ('e7fbab63-7730-4ec9-be73-a62e33ea73c3',
        'PEPITO',
        'mail@mail.com',
        '1234',
        PARSEDATETIME('2020-08-07-00.00.00','yyyy-MM-dd-HH.mm.ss'),
        PARSEDATETIME('2020-08-07-00.00.00','yyyy-MM-dd-HH.mm.ss'),
        PARSEDATETIME('2020-08-07-00.00.00','yyyy-MM-dd-HH.mm.ss'),
        'Bearer eyJhbGciOiJub25lIn0.eyJpYXQiOjE2NjEwMzgxMDJ9.',
        true);