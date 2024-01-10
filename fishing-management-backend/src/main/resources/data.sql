INSERT INTO app_user (username, password, enabled) VALUES ('admin', '$2a$12$/8O0nK5JEXsXk6LxgzIsEu8vyxZDyRRIjjxmnCSdPAnIx0mXxs12C', true);
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('fisherman', '$2a$10$/M2rdVYDZAI7DHPTZVfNUeo8SmEgjdqM.YXpkQOO7Tf/F6ip6/k16', true, 'Nikola', 'Magdić', '1996-11-15');
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('keeper', '$2a$10$/M2rdVYDZAI7DHPTZVfNUeo8SmEgjdqM.YXpkQOO7Tf/F6ip6/k16', true, 'Čuvar', 'Čuvarkić', '1988-05-01');
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('drugi', '$2a$10$/M2rdVYDZAI7DHPTZVfNUeo8SmEgjdqM.YXpkQOO7Tf/F6ip6/k16', true, 'Milan', 'Milanović', '1997-10-05');
INSERT INTO app_user (username, password, enabled, first_name, last_name, date_of_birth) VALUES ('privredni', '$2a$10$/M2rdVYDZAI7DHPTZVfNUeo8SmEgjdqM.YXpkQOO7Tf/F6ip6/k16', true, 'Jovan', 'Jovanović', '1991-11-11');
--
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_FISHERMAN');
INSERT INTO authority (name) VALUES ('ROLE_KEEPER');
--
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (5, 2);
--
INSERT INTO fisherman (id, address, city, category) VALUES (2, 'JNA 117', 'Ruma', 0);
INSERT INTO fisherman (id, address, city, category) VALUES (4, 'Vojvode Mišića 2A', 'Novi Sad', 0);
INSERT INTO fisherman (id, address, city, category) VALUES (5, 'Bulevar Kralja Petra 100', 'Novi Sad', 1);
--
INSERT INTO recreational_fisherman (id) VALUES (2);
INSERT INTO recreational_fisherman (id) VALUES (4);
--
INSERT INTO professional_fisherman(id) VALUES (5);
--
INSERT INTO keeper (id) VALUES (3);
--
INSERT INTO fishing_area (name, area_type, description, allowed_fishing, image) VALUES ('Dunav', 0, 'Dunav je druga najduža reka u Evropi. Prolazi kroz 7 zemalja. Uliva se u Crno More.', true, 'http://localhost:8080/sava-dunav-usce.jpg');
INSERT INTO fishing_area (name, area_type, allowed_fishing, image) VALUES ('Sava', 0, true, 'http://localhost:8080/sava.jpg');
INSERT INTO fishing_area (name, area_type, description, allowed_fishing, image) VALUES ('Borkovac', 1, 'Ovo jezero je divno jedini problem je što nema ribe!', true, 'http://localhost:8080/borkovac(1).jpg');
INSERT INTO fishing_area (name, area_type, description, allowed_fishing, image) VALUES ('Kanal DTD', 3, 'Kanal DTD odnosno kanal Dunav-Tisa-Dunav je nastao za vrema Austro-Ugarske.', true, 'http://localhost:8080/kanal-dtd.jpg');
--
INSERT INTO fishing_spot (fishing_area_id, id, type, latitude, longitude, image) VALUES (1, 1, 1, 45.223554325463994, 19.804557545929946, 'http://localhost:8080/dunav-novi-sad-mesto.jpg');
INSERT INTO fishing_spot (fishing_area_id, id, type, latitude, longitude, image) VALUES (3, 1, 2, 45.04011918564572, 19.81751806040365, 'http://localhost:8080/sava-mesto.jpg');
INSERT INTO fishing_spot (fishing_area_id, id, type, latitude, longitude, image) VALUES (1, 2, 1, 45.223554325463994, 19.804557545929946, 'http://localhost:8080/novi_sad_dunav-mesto.jpg'); 
--
INSERT INTO fish_species (name, latin_name, category, min_size, max_quantity, fishing_ban_start_day, fishing_ban_start_month, fishing_ban_end_day, fishing_ban_end_month, permanent_fishing_ban, image) VALUES ('Štuka', 'Esox lucius', 0, 40, 3, 1, 3, 31, 3, false, 'http://localhost:8080/stuka.jpg');
INSERT INTO fish_species (name, latin_name, category, min_size, max_quantity, fishing_ban_start_day, fishing_ban_start_month, fishing_ban_end_day, fishing_ban_end_month, permanent_fishing_ban, image) VALUES ('Smuđ', 'Stizostedion lucioperca', 0, 40, 3, 1, 3, 30, 4, false, 'http://localhost:8080/smudj.jpg');
INSERT INTO fish_species (name, latin_name, category, min_size, max_quantity, fishing_ban_start_day, fishing_ban_start_month, fishing_ban_end_day, fishing_ban_end_month, permanent_fishing_ban, image) VALUES ('Som', 'Silurus glanis', 0, 60, 3, 1, 5, 15, 6, false, 'http://localhost:8080/evropski-som-silurus-glanis.jpg');
--
INSERT INTO containing (fishing_area_id, fish_species_id) VALUES (1, 1);
INSERT INTO containing (fishing_area_id, fish_species_id) VALUES (1, 2);
INSERT INTO containing (fishing_area_id, fish_species_id) VALUES (2, 1);

INSERT INTO keeps(fishing_area_id, keeper_id) VALUES(1, 3);

INSERT INTO penalty (name, fine) VALUES ('Lov, posedovanje i uništavanje riblje mlađi i primeraka ribe u vreme mresta i lovostaja', 10000);
INSERT INTO penalty (name, fine) VALUES ('Lov ribe neposredno rukom', 5000);
INSERT INTO penalty (name, description, fine) VALUES ('Lov ribe nedozvoljenim sredstvima i alatima', 'Lov ribe eksplozivom i drugim rasprskavajućim sredstvima, harpunom, ostima, podvodnom puškom, vatrenim oružjem, strujom, veštačkim izvorom svetlosti, drugim hemijskim sredstvima...', 20000);
INSERT INTO penalty (name, description, fine) VALUES ('Prekoračenje težine ulova', 'Ulov više od 5kg ribe', 10000);
INSERT INTO penalized(fisherman_id, penalty_id, keeper_id) VALUES (4, 1, 3);