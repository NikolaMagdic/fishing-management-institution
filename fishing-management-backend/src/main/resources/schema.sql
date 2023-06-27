DROP TABLE app_user;
DROP TABLE admin;
DROP TABLE fishing_area;

CREATE TABLE app_user (
	id 		 				 INTEGER 	   NOT NULL AUTO_INCREMENT,
	username 				 VARCHAR(20)   NOT NULL,
	password 				 VARCHAR(200)  NOT NULL,
	last_password_reset_date DATE,
	PRIMARY KEY (id)
);

CREATE TABLE admin (
	id       INTEGER     NOT NULL AUTO_INCREMENT,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(200) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE fishing_area (
	id 			INTEGER NOT NULL AUTO_INCREMENT,
	name 		VARCHAR(20) NOT NULL,
	area_type 	INTEGER,
	PRIMARY KEY (id)
);
