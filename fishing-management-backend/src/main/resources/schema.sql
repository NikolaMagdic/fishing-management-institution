
SET foreign_key_checks = 0;

DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS user_authority;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS fisherman;
DROP TABLE IF EXISTS recreational_fisherman;
DROP TABLE IF EXISTS professional_fisherman;
DROP TABLE IF EXISTS keeper;
DROP TABLE IF EXISTS fishing_area;
DROP TABLE IF EXISTS fishing_spot;
DROP TABLE IF EXISTS fish_species;
DROP TABLE IF EXISTS inhabiting;
DROP TABLE IF EXISTS keeping;
DROP TABLE IF EXISTS license;
DROP TABLE IF EXISTS catch;
DROP TABLE IF EXISTS catch_item;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS fish_population_modification;
DROP TABLE IF EXISTS penalty;
DROP TABLE IF EXISTS penalized;
DROP TABLE IF EXISTS verification_token;

CREATE TABLE app_user (
	id 		 				 INTEGER 	   NOT NULL AUTO_INCREMENT,
	username 				 VARCHAR(320)  NOT NULL,
	password 				 VARCHAR(60)   NOT NULL,
	first_name 		  		 VARCHAR(20),
	last_name 		  		 VARCHAR(30),
	date_of_birth	  		 DATE,
	enabled					 BOOLEAN,		
	last_password_reset_date DATE,
	PRIMARY KEY (id)
);

CREATE TABLE authority (
	id    		INTEGER    NOT NULL AUTO_INCREMENT,
	role_name  VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE user_authority (
	user_id 		INTEGER NOT NULL,
	authority_id  	INTEGER NOT NULL,
	PRIMARY KEY (user_id, authority_id)
);

CREATE TABLE admin (
	id       INTEGER     NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id)
);

CREATE TABLE fisherman (
	id                INTEGER NOT NULL,
	address 		  VARCHAR(50),
	city			  VARCHAR(30),
	category          ENUM('RECREATIONAL', 'PROFESSIONAL') NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE recreational_fisherman (
	id		INTEGER NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE professional_fisherman (
	id				INTEGER  NOT NULL,
	registry_number INTEGER  NOT NULL UNIQUE,
	PRIMARY KEY(id)
);

CREATE TABLE keeper (
	id                INTEGER NOT NULL, 
	license_number	  VARCHAR(30),
	PRIMARY KEY(id)
);

CREATE TABLE fishing_area (
	id			BIGINT 									NOT NULL AUTO_INCREMENT,
	name 					VARCHAR(30) 							NOT NULL,
	area_type 				ENUM('RIVER', 'LAKE', 'POND', 'CANAL')	NOT NULL,
	description 			VARCHAR(1024),
	fishing_allowed			BOOLEAN,
	image					VARCHAR(255),
	parent_fishing_area_id 	BIGINT,
	PRIMARY KEY (id)
);

CREATE TABLE fishing_spot (
	id		BIGINT													NOT NULL,
	spot_type			ENUM('ARRANGED_PLACE', 'BOAT', 'PIER', 'FISHING_HOUSE')	NOT NULL,
	latitude			DOUBLE,			
	longitude			DOUBLE,
	fishing_area_id 	BIGINT  												NOT NULL,
	image				VARCHAR(255),
	PRIMARY KEY (id, fishing_area_id)
);

CREATE TABLE fish_species (
	id 			 	  		INTEGER 	NOT NULL AUTO_INCREMENT,
	common_name 		 	VARCHAR(31) NOT NULL,
	latin_name   	  		VARCHAR(30) NOT NULL,
	category	 	  		ENUM('NOBLE', 'INDIGENOUS', 'NON_NATIVE') NOT NULL,
	min_size	 	  		INTEGER,
	max_quantity 	  		INTEGER,
	fishing_ban_start_month INTEGER,
	fishing_ban_start_day 	INTEGER,
	fishing_ban_end_month   INTEGER,
	fishing_ban_end_day 	INTEGER,
	permanent_fishing_ban 	BOOLEAN,
	description				VARCHAR(1024),
	image					VARCHAR(255),
	PRIMARY KEY (id)
);

CREATE TABLE inhabiting (
	fish_species_id	 INTEGER  NOT NULL,
	fishing_area_id	 BIGINT  NOT NULL,
	PRIMARY KEY (fish_species_id, fishing_area_id)
);

CREATE TABLE keeping (
	fishing_area_id  BIGINT NOT NULL,
	keeper_id		 INTEGER NOT NULL,
	PRIMARY KEY (fishing_area_id, keeper_id)
);

CREATE TABLE license (
	license_id					INTEGER NOT NULL AUTO_INCREMENT,
	type 						ENUM('YEARLY', 'DAILY', 'MULTIDAY') NOT NULL,
	status						ENUM('CONFIRMED', 'REJECTED', 'CREATED') NOT NULL, 
	year						INTEGER, 
	date						DATE,
	end_date					DATE,
	fisherman_id				INTEGER,
	professional_fisherman_id 	INTEGER,
	dtype						VARCHAR(31) NOT NULL,
	PRIMARY KEY (license_id)
);

CREATE TABLE catch (
	id              INTEGER NOT NULL AUTO_INCREMENT,
	date			DATE,
	fisherman_id	INTEGER NOT NULL,
	fishing_area_id	BIGINT NOT NULL,
	reservation_id	INTEGER,
	PRIMARY KEY (id)
);

CREATE TABLE catch_item (
	item_id				INTEGER NOT NULL,
	quantity			INTEGER,
	weight				FLOAT,
	confirmation_status	ENUM('CONFIRMED', 'CONFISCATED', 'NOT_VERIFIED') NOT NULL,
	catch_id			INTEGER NOT NULL,
	fish_id				INTEGER NOT NULL,
	keeper_id			INTEGER,
	PRIMARY KEY (item_id, catch_id)
);

CREATE TABLE reservation (
	id				INTEGER NOT NULL AUTO_INCREMENT,
	arrival_date	DATE NOT NULL,
	departure_date	DATE,
	cancelled		BOOLEAN,
	fisherman_id	INTEGER NOT NULL,
	fishing_spot_id	BIGINT NOT NULL,
	fishing_area_id BIGINT NOT NULL,
	license_id		INTEGER,
	PRIMARY KEY (id)
);

CREATE TABLE fish_population_modification (
	id					INTEGER NOT NULL AUTO_INCREMENT,
	date				DATE, 
	amount				INTEGER,
	total_weight		INTEGER,
	modification_type	ENUM('FISH_STOCKING', 'SELECTIVE_FISHING'),
	fishing_area_id 	BIGINT NOT NULL,
	fish_species_id 	INTEGER NOT NULL,
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
	area_id			BIGINT NOT NULL,
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

ALTER TABLE recreational_fisherman ADD CONSTRAINT fk_recreational_fisherman FOREIGN KEY (id)
REFERENCES fisherman (id);

ALTER TABLE professional_fisherman ADD CONSTRAINT fk_professional_fisherman FOREIGN KEY (id)
REFERENCES fisherman (id);

ALTER TABLE keeper ADD CONSTRAINT fk_keeper FOREIGN KEY (id)
REFERENCES app_user (id);

ALTER TABLE fishing_area ADD CONSTRAINT fk_parent_fishing_area FOREIGN KEY (parent_fishing_area_id)
REFERENCES fishing_area (id);

ALTER TABLE fishing_spot ADD CONSTRAINT fk_has FOREIGN KEY (fishing_area_id)
REFERENCES fishing_area (id);

ALTER TABLE inhabiting ADD CONSTRAINT fk_inhabits FOREIGN KEY (fishing_area_id)
REFERENCES fishing_area (id);

ALTER TABLE inhabiting ADD CONSTRAINT fk_inhabits2 FOREIGN KEY (fish_species_id)
REFERENCES fish_species (id);

ALTER TABLE keeping ADD CONSTRAINT fk_keeps FOREIGN KEY (keeper_id)
REFERENCES keeper (id);

ALTER TABLE keeping ADD CONSTRAINT fk_keeps2 FOREIGN KEY (fishing_area_id)
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

ALTER TABLE fish_population_modification ADD CONSTRAINT fk_includes FOREIGN KEY (fish_species_id)
REFERENCES fish_species (id);

ALTER TABLE fish_population_modification ADD CONSTRAINT fk_takes_place FOREIGN KEY (fishing_area_id)
REFERENCES fishing_area (id);

ALTER TABLE penalized ADD CONSTRAINT fk_recorded FOREIGN KEY (penalty_id)
REFERENCES penalty (id);

ALTER TABLE penalized ADD CONSTRAINT fk_receives FOREIGN KEY (fisherman_id)
REFERENCES fisherman (id);

ALTER TABLE penalized ADD CONSTRAINT fk_records FOREIGN KEY (area_id, keeper_id)
REFERENCES keeping (fishing_area_id, keeper_id);

ALTER TABLE verification_token ADD CONSTRAINT fk_token FOREIGN KEY (user_id)
REFERENCES app_user (id);

set foreign_key_checks = 1;
