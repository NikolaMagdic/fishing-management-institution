INSERT INTO app_user (username, password) VALUES ('admin', '$2a$12$/8O0nK5JEXsXk6LxgzIsEu8vyxZDyRRIjjxmnCSdPAnIx0mXxs12C');
--
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_FISHERMAN');
INSERT INTO authority (name) VALUES ('ROLE_KEEPER');
--
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
--
INSERT INTO fishing_area (name, description, area_type, image) VALUES ('Borkovac', 'Ovo jezero je divno jedini problem je što nema ribe!', 1, 'https://www.gradnja.rs/wp-content/uploads/2022/04/borkovac-ruma-naslovna.jpg');
INSERT INTO fishing_area (name, area_type, image) VALUES ('Sava', 0, 'https://turizamusrbiji.rs/wp-content/uploads/2014/10/belgrade-sava-river.jpg');