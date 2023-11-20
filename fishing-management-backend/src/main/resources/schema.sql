/*
SET foreign_key_checks = 0;

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
	password 				 VARCHAR(60)   NOT NULL,
	first_name 		  		 VARCHAR(20),
	last_name 		  		 VARCHAR(30),
	date_of_birth	  		 DATE,
	enabled					 BOOLEAN,		
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
	id                INTEGER NOT NULL,
	address 		  VARCHAR(50),
	city			  VARCHAR(30),
	category          INTEGER,
	PRIMARY KEY(id)
);

CREATE TABLE keeper (
	id                INTEGER NOT NULL AUTO_INCREMENT, 
	PRIMARY KEY(id)
);

CREATE TABLE fishing_area (
	id 			INTEGER 	NOT NULL AUTO_INCREMENT,
	name 		VARCHAR(20) NOT NULL,
	area_type 	INTEGER,
	description VARCHAR(255),
	image		VARCHAR(255),
	PRIMARY KEY (id)
);

CREATE TABLE fishing_spot (
	id				INTEGER		NOT NULL AUTO_INCREMENT,
	type			INTEGER		NOT NULL,
	latitude		DOUBLE,			
	longitude		DOUBLE,
	fishing_area_id INTEGER     NOT NULL,
	image			VARCHAR(255),
	PRIMARY KEY (id, fishing_area_id)
);

CREATE TABLE fish_species (
	id 			 	  		INTEGER NOT NULL AUTO_INCREMENT,
	name 		 	  		VARCHAR(20) NOT NULL,
	latin_name   	  		VARCHAR(30) NOT NULL,
	category	 	  		INTEGER NOT NULL,
	min_size	 	  		INTEGER,
	max_quantity 	  		INTEGER,
	fishing_ban_start_month INTEGER,
	fishing_ban_start_day 	INTEGER,
	fishing_ban_end_month   INTEGER,
	fishing_ban_end_day 	INTEGER,
	permanent_fishing_ban 	BOOLEAN,
	description				VARCHAR(1024),
	image					VARCHAR(1024),
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
	date			DATE,
	end_date		DATE,
	fisherman_id	INTEGER NOT NULL,
	spot_id			INTEGER,
	PRIMARY KEY (license_id)
);

CREATE TABLE catch (
	id              INTEGER NOT NULL AUTO_INCREMENT,
	date			DATE,
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
	fish_id			INTEGER NOT NULL,
	keeper_id		INTEGER,
	PRIMARY KEY (item_id, catch_id)
);

CREATE TABLE reservation (
	id				INTEGER NOT NULL AUTO_INCREMENT,
	arrival_date	DATE NOT NULL,
	departure_date	DATE,
	fisherman_id	INTEGER NOT NULL,
	fishing_spot_id	INTEGER NOT NULL,
	fishing_area_id INTEGER NOT NULL,
	license_id		INTEGER,
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
	id 				INTEGER 	 NOT NULL AUTO_INCREMENT,
	name			VARCHAR(255) NOT NULL,
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
	keeper_id		INTEGER NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE verification_token (
	id				INTEGER NOT NULL AUTO_INCREMENT,
	token			VARCHAR(300) NOT NULL,
	user_id			INTEGER NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE user_authority ADD CONSTRAINT fk_authority FOREIGN KEY (authority_id)
REFERENCES authority (id);

ALTER TABLE user_authority ADD CONSTRAINT fk_user FOREIGN KEY (user_id) 
REFERENCES app_user (id);

ALTER TABLE admin ADD CONSTRAINT fk_admin FOREIGN KEY (id)
REFERENCES app_user (id);

ALTER TABLE fisherman ADD CONSTRAINT fk_fisherman FOREIGN KEY (id)
REFERENCES app_user (id);

ALTER TABLE keeper ADD CONSTRAINT fk_keeper FOREIGN KEY (id)
REFERENCES app_user (id);

ALTER TABLE fishing_spot ADD CONSTRAINT fk_has FOREIGN KEY (fishing_area_id)
REFERENCES fishing_area (id);

ALTER TABLE containing ADD CONSTRAINT fk_contains FOREIGN KEY (fishing_area_id)
REFERENCES fishing_area (id);

ALTER TABLE containing ADD CONSTRAINT fk_contains2 FOREIGN KEY (fish_species_id)
REFERENCES fish_species (id);

ALTER TABLE keeps ADD CONSTRAINT fk_keeps FOREIGN KEY (keeper_id)
REFERENCES keeper (id);

ALTER TABLE keeps ADD CONSTRAINT fk_keeps2 FOREIGN KEY (fishing_area_id)
REFERENCES fishing_area (id);

ALTER TABLE license ADD CONSTRAINT fk_license FOREIGN KEY (fisherman_id)
REFERENCES fisherman (id);

ALTER TABLE catch ADD CONSTRAINT fk_reports FOREIGN KEY (fisherman_id)
REFERENCES fisherman (id);

ALTER TABLE catch ADD CONSTRAINT fk_is_made FOREIGN KEY (fishing_area_id)
REFERENCES fishing_area (id);

ALTER TABLE catch_item ADD CONSTRAINT fk_makes_up FOREIGN KEY (catch_id)
REFERENCES catch (id);

ALTER TABLE catch_item ADD CONSTRAINT fk_presents FOREIGN KEY (fish_id)
REFERENCES fish_species (id);

ALTER TABLE catch_item ADD CONSTRAINT fk_confirms FOREIGN KEY (keeper_id)
REFERENCES keeper (id);

ALTER TABLE reservation ADD CONSTRAINT fk_occupies FOREIGN KEY (fishing_area_id, fishing_spot_id)
REFERENCES fishing_spot (fishing_area_id, id);

ALTER TABLE reservation ADD CONSTRAINT fk_reserves FOREIGN KEY (fisherman_id)
REFERENCES fisherman (id);

ALTER TABLE reservation ADD CONSTRAINT fk_reservation_license FOREIGN KEY (license_id)
REFERENCES license (license_id);

ALTER TABLE fish_stocking ADD CONSTRAINT fk_includes FOREIGN KEY (fish_species_id)
REFERENCES fish_species (id);

ALTER TABLE fish_stocking ADD CONSTRAINT fk_takes_place FOREIGN KEY (fishing_area_id)
REFERENCES fishing_area (id);

ALTER TABLE penalized ADD CONSTRAINT fk_recorded FOREIGN KEY (penalty_id)
REFERENCES penalty (id);

ALTER TABLE penalized ADD CONSTRAINT fk_receives FOREIGN KEY (fisherman_id)
REFERENCES fisherman (id);

ALTER TABLE penalized ADD CONSTRAINT fk_records FOREIGN KEY (keeper_id)
REFERENCES keeper (id);

ALTER TABLE verification_token ADD CONSTRAINT fk_token FOREIGN KEY (user_id)
REFERENCES app_user (id);

set foreign_key_checks = 1;
*/