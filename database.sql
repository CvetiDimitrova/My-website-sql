DROP DATABASE IF EXISTS traktori;
CREATE DATABASE traktori;
USE traktori;
CREATE TABLE traktori (id INT, marka VARCHAR(255), snimka VARCHAR(255), cena INT);
CREATE TABLE potrebiteli (name VARCHAR(255), pass VARCHAR(255), mail VARCHAR(255) );
INSERT INTO potrebiteli VALUES ("admin", "admin", "admin@MMC.bg);
INSERT INTO marki VALUES (1, "Bulgar"), (2, "UMZ");