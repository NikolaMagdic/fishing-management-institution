INSERT INTO app_user (username, password, enabled) VALUES ('admin@mail.com', '$2a$10$uur9ymenzS6H1BxfaDPoUuAzVuLv78ufD6Vl2AotoNMqgej0K3PLO', true);
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('fisherman@mail.com', '$2a$10$V9qewJLaVTxiGb2g3W8XwugNA137VQEkXy/A0XsxQ6cJV6D14ZW.6', true, 'Nikola', 'Magdić', '1996-11-15');
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('keeper@mail.com', '$2a$10$V9qewJLaVTxiGb2g3W8XwugNA137VQEkXy/A0XsxQ6cJV6D14ZW.6', true, 'Sergej', 'Čuvarkić', '1988-05-01');
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('drugi@mail.com', '$2a$10$V9qewJLaVTxiGb2g3W8XwugNA137VQEkXy/A0XsxQ6cJV6D14ZW.6', true, 'Milan', 'Milanović', '1997-10-05');
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('privredni@mail.com', '$2a$10$V9qewJLaVTxiGb2g3W8XwugNA137VQEkXy/A0XsxQ6cJV6D14ZW.6', true, 'Jovan', 'Jovanović', '1991-11-11');
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('zenaribolovac@mail.com', '$2a$10$V9qewJLaVTxiGb2g3W8XwugNA137VQEkXy/A0XsxQ6cJV6D14ZW.6', true, 'Milica', 'Miličić', '1999-05-18');
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('pecaros@mail.com', '$2a$10$V9qewJLaVTxiGb2g3W8XwugNA137VQEkXy/A0XsxQ6cJV6D14ZW.6', true, 'Zdravko', 'Magdić', '1948-02-14');
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('cuvar@mail.com', '$2a$10$V9qewJLaVTxiGb2g3W8XwugNA137VQEkXy/A0XsxQ6cJV6D14ZW.6', true, 'Jovan', 'Fišer', '1980-02-14');
-- lozinka za sve korisnike je 123123123, sem za admina za koga je admin123
INSERT INTO authority (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (role_name) VALUES ('ROLE_FISHERMAN');
INSERT INTO authority (role_name) VALUES ('ROLE_KEEPER');
--
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (5, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (6, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (7, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (8, 3);
--
INSERT INTO fisherman (id, address, city, category) VALUES (2, 'JNA 117', 'Ruma', 1);
INSERT INTO fisherman (id, address, city, category) VALUES (4, 'Vojvode Mišića 2A', 'Novi Sad', 1);
INSERT INTO fisherman (id, address, city, category) VALUES (5, 'Bulevar Kralja Petra 100', 'Novi Sad', 2);
INSERT INTO fisherman (id, address, city, category) VALUES (6, 'Stari šor 32', 'Sremska Mitrovica', 1);
INSERT INTO fisherman (id, address, city, category) VALUES (7, 'JNA 117', 'Ruma', 1);
--
INSERT INTO recreational_fisherman (id) VALUES (2);
INSERT INTO recreational_fisherman (id) VALUES (4);
INSERT INTO recreational_fisherman (id) VALUES (6);
INSERT INTO recreational_fisherman (id) VALUES (7);
--
INSERT INTO professional_fisherman(id, registry_number) VALUES (5, 121);
--
INSERT INTO keeper (id, license_number) VALUES (3, '190924');
INSERT INTO keeper (id, license_number) VALUES (8, '190920ER');
--
INSERT INTO fishing_area (name, area_type, description, fishing_allowed, image) VALUES ('Dunav', 1, 'Dunav je druga najduža reka u Evropi. Prolazi kroz 7 zemalja i uliva se u Crno More. Kroz Srbiju protiče u dužini od 588 km. U severnom delu sliva kroz Srbiju reka je tipično nizijska, obala je peskovita sa ilovačom, a uz obalu ima močvarnih područja, ritova i mrtvaja. U južnom delu toka, prolazi kroz Đerdap koji se sastoji iz četiri klisure i kotlina između njih. Reka je u većem delu toka spora, sem u klisurama i na samom izlazu iz Srbije u reonu Negotina.', true, 'http://localhost:8080/sava-dunav-usce.jpg');
INSERT INTO fishing_area (name, area_type, fishing_allowed, image) VALUES ('Sava', 1, true, 'http://localhost:8080/sava.jpg');
INSERT INTO fishing_area (name, area_type, description, fishing_allowed, image) VALUES ('Borkovac', 2, 'Ovo jezero je divno jedini problem je što nema ribe!', true, 'http://localhost:8080/borkovac(1).jpg');
INSERT INTO fishing_area (name, area_type, description, fishing_allowed, image) VALUES ('Kanal DTD', 4, 'Kanal DTD odnosno kanal Dunav-Tisa-Dunav je nastao za vrema Austro-Ugarske.', true, 'http://localhost:8080/kanal-dtd.jpg');
INSERT INTO fishing_area (name, area_type, description, fishing_allowed, image) VALUES ('Tisa', 1, 'Tisa je reka u srednjoj Evropi i najduža pritoka Dunava. Protiče kroz Panonsku niziju. Izvire u Ukrajini, na Karpatima u oblasti Bukovina. Kroz Srbiju teče u dužini od 164 km, a širina joj je od 140 do 220 m. Tok Tise je regulisan pa su na mnogim mestima ostali rukavci mrtve Tise.', true, 'http://localhost:8080/tisa.jpeg');
--
INSERT INTO fishing_spot (fishing_area_id, id, spot_type, latitude, longitude, image) VALUES (1, 1, 1, 45.231616, 19.850564, 'http://localhost:8080/dunav-novi-sad-mesto.jpg');
INSERT INTO fishing_spot (fishing_area_id, id, spot_type, latitude, longitude, image) VALUES (2, 1, 3, 44.758760, 19.719062, 'http://localhost:8080/sava-mesto.jpg');
INSERT INTO fishing_spot (fishing_area_id, id, spot_type, latitude, longitude, image) VALUES (1, 2, 1, 45.231616, 19.850564, 'http://localhost:8080/novi_sad_dunav-mesto.jpg'); 
INSERT INTO fishing_spot (fishing_area_id, id, spot_type, latitude, longitude, image) VALUES (1, 3, 2, 45.223554325463994, 19.804557545929946, 'http://localhost:8080/Aluminum_Fishing_Boat.jpg');
INSERT INTO fishing_spot (fishing_area_id, id, spot_type, latitude, longitude, image) VALUES (3, 1, 1, 45.048526, 19.822185, 'http://localhost:8080/borkovac stapovi.jpg');
INSERT INTO fishing_spot (fishing_area_id, id, spot_type, latitude, longitude, image) VALUES (3, 2, 4, 45.044915, 19.820679, 'http://localhost:8080/borkovac vikendica.jpg');
INSERT INTO fishing_spot (fishing_area_id, id, spot_type, latitude, longitude, image) VALUES (3, 3, 1, 45.044090, 19.820559, 'http://localhost:8080/borkovac mesto.jpg');
INSERT INTO fishing_spot (fishing_area_id, id, spot_type, latitude, longitude, image) VALUES (3, 4, 2, 45.039117, 19.817662, 'http://localhost:8080/Aluminum_Fishing_Boat.jpg');
INSERT INTO fishing_spot (fishing_area_id, id, spot_type, latitude, longitude, image) VALUES (1, 4, 4, 45.231812, 19.840137, 'http://localhost:8080/borkovac vikendica.jpg');
--
INSERT INTO fish_species (common_name, latin_name, category, permanent_fishing_ban, image) VALUES ('Američki patuljasti som, terpan', 'Ictalurus nebulosus', 3, false, 'http://localhost:8080/patuljasti somic.jpg');
INSERT INTO fish_species (common_name, latin_name, category, permanent_fishing_ban, image) VALUES ('Babuška, srebrni karaš', 'Carassius auratus', 3, false, 'http://localhost:8080/srebrni karas.jpg');
INSERT INTO fish_species (common_name, latin_name, category, permanent_fishing_ban, image) VALUES ('Crvenperka', 'Scardinius erythrophthalmus', 2, false, 'http://localhost:8080/crvenperka.jpg');
INSERT INTO fish_species (common_name, latin_name, category, permanent_fishing_ban, image) VALUES ('Linjak', 'Tinca tinca', 1, true, 'http://localhost:8080/linjak.jpg');
INSERT INTO fish_species (common_name, latin_name, category, min_size, max_quantity, fishing_ban_start_day, fishing_ban_start_month, fishing_ban_end_day, fishing_ban_end_month, permanent_fishing_ban, image) VALUES ('Smuđ', 'Stizostedion lucioperca', 1, 40, 3, 1, 3, 30, 4, false, 'http://localhost:8080/smudj.jpg');
INSERT INTO fish_species (common_name, latin_name, category, min_size, max_quantity, fishing_ban_start_day, fishing_ban_start_month, fishing_ban_end_day, fishing_ban_end_month, permanent_fishing_ban, image) VALUES ('Som', 'Silurus glanis', 1, 60, 3, 1, 5, 15, 6, false, 'http://localhost:8080/som.jpg');
INSERT INTO fish_species (common_name, latin_name, category, min_size, max_quantity, fishing_ban_start_day, fishing_ban_start_month, fishing_ban_end_day, fishing_ban_end_month, permanent_fishing_ban, image) VALUES ('Štuka', 'Esox lucius', 1, 40, 3, 1, 3, 31, 3, false, 'http://localhost:8080/stuka.jpg');
--
INSERT INTO inhabiting (fishing_area_id, fish_species_id) VALUES (1, 5);
INSERT INTO inhabiting (fishing_area_id, fish_species_id) VALUES (1, 6);
INSERT INTO inhabiting (fishing_area_id, fish_species_id) VALUES (1, 7);
INSERT INTO inhabiting (fishing_area_id, fish_species_id) VALUES (2, 1);
--
INSERT INTO keeping(fishing_area_id, keeper_id) VALUES(1, 3);

INSERT INTO penalty (name, fine) VALUES ('Lov, posedovanje i uništavanje riblje mlađi i primeraka ribe u vreme mresta i lovostaja', 10000);
INSERT INTO penalty (name, fine) VALUES ('Lov ribe neposredno rukom', 5000);
INSERT INTO penalty (name, description, fine) VALUES ('Lov ribe nedozvoljenim sredstvima i alatima', 'Lov ribe eksplozivom i drugim rasprskavajućim sredstvima, harpunom, ostima, podvodnom puškom, vatrenim oružjem, strujom, veštačkim izvorom svetlosti, drugim hemijskim sredstvima...', 20000);
INSERT INTO penalty (name, description, fine) VALUES ('Prekoračenje težine ulova', 'Ulov više od 5kg ribe', 8000);

INSERT INTO penalized (fisherman_id, penalty_id, keeper_id, area_id) VALUES (4, 1, 3, 1);

INSERT INTO catch (date, fisherman_id, fishing_area_id) VALUES ('2023-03-14', 2, 1);
INSERT INTO catch_item (catch_id, item_id, quantity, weight, confirmation_status, fish_id, keeper_id) VALUES (1, 1, 1, 2.3, 1, 7, 3);