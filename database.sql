DROP DATABASE IF EXISTS mmc;
CREATE DATABASE mmc;
USE mmc;

CREATE TABLE artikuli (id INT, vid VARCHAR(255), marka VARCHAR(255), snimka VARCHAR(255), opisanie VARCHAR(255), cena INT, garancia VARCHAR(255));
CREATE TABLE potrebiteli (name VARCHAR(255), pass VARCHAR(255), mail VARCHAR(255) );

INSERT INTO artikuli VALUES 
	(1, "klimatik", "daikin" , "ac.jpg", "Климатика е удобство,което си заслужава", 960, "3 godini"), 
	(2, "vrati", "darveni" , "Door.jpg", "Разнообразие от врати", 589, "1 godina"), 
	(3, "shtori", "wertikalni" , "R.jpg", "Какво по - хубаво от стая с добре сложени щори", 330, "3 godini"), 
	(4, "dograma", "pvc" , "pvc.jpg", "PVC и алуминиева", 710, "4 godini");
INSERT INTO potrebiteli VALUES
	("admin", "admin", "admin@MMC.bg"),
	("guest", "1234", "guest@MMC.bg"),
	("people", "5678", "people@MMC.bg"),
	("cveti", "2004", "cveti@abv.bg");
