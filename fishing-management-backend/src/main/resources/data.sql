INSERT INTO app_user (username, password, enabled) VALUES ('admin', '$2a$12$/8O0nK5JEXsXk6LxgzIsEu8vyxZDyRRIjjxmnCSdPAnIx0mXxs12C', true);
INSERT INTO app_user (username, password, enabled) VALUES ('fisherman', '$2a$10$/M2rdVYDZAI7DHPTZVfNUeo8SmEgjdqM.YXpkQOO7Tf/F6ip6/k16', true);
INSERT INTO app_user (username, password, enabled) VALUES ('keeper', '$2a$10$/M2rdVYDZAI7DHPTZVfNUeo8SmEgjdqM.YXpkQOO7Tf/F6ip6/k16', true);
INSERT INTO app_user (username, password, enabled) VALUES ('drugi', '$2a$10$/M2rdVYDZAI7DHPTZVfNUeo8SmEgjdqM.YXpkQOO7Tf/F6ip6/k16', true);
INSERT INTO app_user (username, password, enabled) VALUES ('treci', '$2a$10$/M2rdVYDZAI7DHPTZVfNUeo8SmEgjdqM.YXpkQOO7Tf/F6ip6/k16', true);
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
INSERT INTO fisherman (first_name, last_name, date_of_birth, address, city, category, user_id) VALUES ('Nikola', 'Magdić', '1996-11-15', 'JNA 117', 'Ruma', 0, 2);
INSERT INTO fisherman (first_name, last_name, date_of_birth, address, city, category, user_id) VALUES ('Milan', 'Milanović', '1997-10-05', 'Vojvode Mišića 2A', 'Novi Sad', 0, 4);
INSERT INTO fisherman (first_name, last_name, date_of_birth, address, city, category, user_id) VALUES ('Jovan', 'Jovanović', '1991-11-11', 'Bulevar Kralja Petra 100', 'Novi Sad', 0, 5);
--
INSERT INTO keeper (first_name, last_name, date_of_birth, user_id) VALUES ('Čuvar', 'Čuvarkić', '1988-05-01', 3);
--
INSERT INTO fishing_area (name, area_type, image) VALUES ('Sava', 0, 'http://localhost:8080/belgrade-sava-river.jpg');
INSERT INTO fishing_area (name, area_type, description, image) VALUES ('Dunav', 0, 'Dunav je druga najduža reka u Evropi. Prolazi kroz 7 zemalja. Uliva se u Crno More.', 'http://localhost:8080/Dunav.jpg');
INSERT INTO fishing_area (name, area_type, description, image) VALUES ('Borkovac', 1, 'Ovo jezero je divno jedini problem je što nema ribe!', 'http://localhost:8080/borkovac-ruma-naslovna.jpg');


INSERT INTO fishing_spot (fishing_area_id, type, latitude, longitude, image) VALUES (2, 1, 45.223554325463994, 19.804557545929946, 'http://fishing-promo_qlkcwh.avif');
INSERT INTO fishing_spot (fishing_area_id, type, latitude, longitude) VALUES (3, 1, 45.04011918564572, 19.81751806040365);

INSERT INTO fish_species (name, latin_name, category, min_size, max_quantity, fishing_ban_start_day, fishing_ban_start_month, fishing_ban_end_day, fishing_ban_end_month, permanent_fishing_ban, image) VALUES ('Štuka', 'Esox lucius', 0, 40, 3, 1, 3, 31, 3, false, 'http://localhost:8080/stuka.gif');
INSERT INTO fish_species (name, latin_name, category, min_size, max_quantity, fishing_ban_start_day, fishing_ban_start_month, fishing_ban_end_day, fishing_ban_end_month, permanent_fishing_ban, image) VALUES ('Smuđ', 'Stizostedion lucioperca', 0, 40, 3, 1, 3, 30, 4, false, 'http://localhost:8080/smudj.jpg');

INSERT INTO containing (fishing_area_id, fish_species_id) VALUES (1, 1);
INSERT INTO containing (fishing_area_id, fish_species_id) VALUES (1, 2);
INSERT INTO containing (fishing_area_id, fish_species_id) VALUES (2, 1);

INSERT INTO penalty (name, description, fine) VALUES ('Prekoračenje težine ulova', 'Ulov više od 5kg ribe', 10000);
INSERT INTO penalized(fisherman_id, penalty_id) VALUES (2, 1);