/*DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS fishing_area;
DROP TABLE IF EXISTS fish_species;
DROP TABLE IF EXISTS fisherman;

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

CREATE TABLE fish_species (
	id 			 	  INTEGER NOT NULL AUTO_INCREMENT,
	name 		 	  VARCHAR(20) NOT NULL,
	latin_name   	  VARCHAR(30) NOT NULL,
	category	 	  INTEGER NOT NULL,
	min_size	 	  INTEGER,
	max_quantity 	  INTEGER,
	max_weight   	  INTEGER,
	fishing_ban_start DATE,
	fishing_ban_end   DATE,
	permanent_fishing_ban BOOLEAN,
	PRIMARY KEY (id)
);

CREATE TABLE fisherman (
	id                INTEGER NOT NULL AUTO_INCREMENT,
	first_name 		  VARCHAR(20) NOT NULL,
	last_name 		  VARCHAR(30) NOT NULL,
	date_of_birth	  DATE,
	address 		  VARCHAR(50),
	city			  VARCHAR(30),
	category          INTEGER,
	PRIMARY KEY(id)

);
*/