/*
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS user_authority;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS fisherman;
DROP TABLE IF EXISTS keeper;
DROP TABLE IF EXISTS fishing_area;
DROP TABLE IF EXISTS fishing_spot;
DROP TABLE IF EXISTS fish_species;
DROP TABLE IF EXISTS containing;
DROP TABLE IF EXISTS keeps;
DROP TABLE IF EXISTS license;
DROP TABLE IF EXISTS catch;
DROP TABLE IF EXISTS catch_item;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS fish_stocking;
DROP TABLE IF EXISTS penalty;
DROP TABLE IF EXISTS penalized;
DROP TABLE IF EXISTS verification_token;


CREATE TABLE app_user (
	id 		 				 INTEGER 	   NOT NULL AUTO_INCREMENT,
	username 				 VARCHAR(20)   NOT NULL,
	password 				 VARCHAR(20)  NOT NULL,
	last_password_reset_date DATE,
	PRIMARY KEY (id)
);

CREATE TABLE authority (
	id    INTEGER 	  NOT NULL AUTO_INCREMENT,
	name  VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE user_authority (
	user_id      INTEGER NOT NULL,
	authority_id  INTEGER NOT NULL,
	PRIMARY KEY (user_id, authority_id)
);

CREATE TABLE admin (
	id       INTEGER     NOT NULL AUTO_INCREMENT,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(200) NOT NULL,
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

CREATE TABLE keeper (
	id                 INTEGER NOT NULL AUTO_INCREMENT, 
	first_name		   VARCHAR(20) NOT NULL,	
	last_name          VARCHAR(30) NOT NULL,
	date_of_birth	   DATE,
	PRIMARY KEY(id)
);

CREATE TABLE fishing_area (
	id 			INTEGER NOT NULL AUTO_INCREMENT,
	name 		VARCHAR(20) NOT NULL,
	area_type 	INTEGER,
	description VARCHAR(255), 
	PRIMARY KEY (id)
);

CREATE TABLE fishing_spot (
	id				INTEGER			NOT NULL AUTO_INCREMENT,
	type			VARCHAR(20)		NOT NULL,
	latitude		DOUBLE,			
	longitude		DOUBLE,
	fishing_area_id INTEGER     	NOT NULL,
	PRIMARY KEY (id, fishing_area_id)
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

CREATE TABLE containing (
	fish_species_id	 INTEGER  NOT NULL,
	fishing_area_id	 INTEGER  NOT NULL,
	PRIMARY KEY (fish_species_id, fishing_area_id)
);

CREATE TABLE keeps (
	fishing_area_id  INTEGER NOT NULL,
	keeper_id		 INTEGER NOT NULL,
	PRIMARY KEY (fishing_area_id, keeper_id)
);

CREATE TABLE license (
	license_id		INTEGER NOT NULL AUTO_INCREMENT,
	type 			INTEGER NOT NULL,
	status			INTEGER, 
	year			INTEGER, 
	day				DATE,
	fisherman_id	INTEGER NOT NULL,
	spot_id			INTEGER,
	PRIMARY KEY (license_id)
);

CREATE TABLE catch (
	id              INTEGER NOT NULL AUTO_INCREMENT,
	time			DATE,
	fisherman_id	INTEGER NOT NULL,
	fishing_area_id	INTEGER NOT NULL,
	reservation_id	INTEGER,
	PRIMARY KEY (id)
);

CREATE TABLE catch_item (
	item_id			INTEGER NOT NULL AUTO_INCREMENT,
	quantity		INTEGER,
	weight			FLOAT,
	confirmed		BOOLEAN,
	catch_id		INTEGER NOT NULL,
	fish_id			INTEGER NOT NULL.
	PRIMARY KEY (item_id)
);

CREATE TABLE reservation (
	id				INTEGER NOT NULL AUTO_INCREMENT,
	arrival_date	DATE NOT NULL,
	departure_date	DATE,
	fisherman_id	INTEGER NOT NULL,
	fishing_spot_id	INTEGER NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE fish_stocking (
	id				INTEGER NOT NULL AUTO_INCREMENT,
	date			DATE, 
	number			INTEGER,
	fishing_area_id INTEGER NOT NULL,
	fish_species_id INTEGER NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE penalty (
	id 				INTEGER NOT NULL AUTO_INCREMENT,
	name			INTEGER NOT NULL,
	description		VARCHAR(300),
	fine			FLOAT,
	PRIMARY KEY (id)
);

CREATE TABLE penalized (
	id				INTEGER NOT NULL AUTO_INCREMENT,
	date			DATE,
	report			VARCHAR(300),
	fisherman_id	INTEGER NOT NULL,
	penalty_id		INTEGER NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE verification_token (
	id				INTEGER NOT NULL AUTO_INCREMENT,
	token			VARCHAR(300) NOT NULL,
	user_id			INTEGER NOT NULL,
	PRIMARY KEY (id)
);
*/