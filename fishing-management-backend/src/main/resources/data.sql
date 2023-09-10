INSERT INTO app_user (username, password, enabled) VALUES ('admin', '$2a$12$/8O0nK5JEXsXk6LxgzIsEu8vyxZDyRRIjjxmnCSdPAnIx0mXxs12C', true);
INSERT INTO app_user (username, password, enabled) VALUES ('fisherman', '$2a$10$/M2rdVYDZAI7DHPTZVfNUeo8SmEgjdqM.YXpkQOO7Tf/F6ip6/k16', true);
--
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_FISHERMAN');
INSERT INTO authority (name) VALUES ('ROLE_KEEPER');
--
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
--
INSERT INTO fisherman (first_name, last_name, date_of_birth, address, city, category, user_id) VALUES ('Nikola', 'Magdić', '1996-11-15', 'JNA 117', 'Ruma', 0, 2);
--
INSERT INTO fishing_area (name, description, area_type, image) VALUES ('Borkovac', 'Ovo jezero je divno jedini problem je što nema ribe!', 1, 'https://www.gradnja.rs/wp-content/uploads/2022/04/borkovac-ruma-naslovna.jpg');
INSERT INTO fishing_area (name, area_type, image) VALUES ('Sava', 0, 'https://turizamusrbiji.rs/wp-content/uploads/2014/10/belgrade-sava-river.jpg');

INSERT INTO fishing_spot (fishing_area_id, type, latitude, longitude) VALUES (1, 1, 45.04011918564572, 19.81751806040365);
