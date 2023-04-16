-- Active: 1681681433495@@127.0.0.1@5432@postgres@public
CREATE DATABASE bd2_jdbc;

CREATE TABLE pessoa (
    ID SMALLINT PRIMARY KEY,
    nome VARCHAR(30),
    idade SMALLINT
);


INSERT INTO pessoa VALUES (1, 'João', 20);

DROP TABLE pessoa;