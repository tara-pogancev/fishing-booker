-- ADDRESSES
INSERT INTO address(id, city, country, street)	VALUES (1, 'Eastbourne', 'UK', 'Marine Parade 22');
INSERT INTO address(id, city, country, street)  VALUES (2, 'Novi Sad', 'Serbia', 'Bulevar Oslobodjenja 85');
INSERT INTO address(id, city, country, street)	VALUES (3, 'Brighton', 'UK', 'Kingsway 30');
INSERT INTO address(id, city, country, street)	VALUES (4, 'San Francisco', 'USA', 'Jauss St 7');
INSERT INTO address(id, city, country, street)	VALUES (5, 'Winnipeg', 'Canada', 'Main St 62');

INSERT INTO public.address(id, city, country, street)   VALUES (6, 'Camden', 'Mississippi', '2141 17th Hwy');
INSERT INTO public.address(id, city, country, street)	VALUES (7, 'Bowling Green', 'Kentucky', '2105 Robin Rd #T4');
INSERT INTO public.address(id, city, country, street)	VALUES (8, 'Chenoa', 'Illinois', '601 Emma St');
INSERT INTO public.address(id, city, country, street)	VALUES (9, 'Eustis', 'Florida', '845 Edgewater Cir');
INSERT INTO public.address(id, city, country, street)	VALUES (10, 'Somers Point', 'New Jersey', '1418 Massachusetts Ave');
INSERT INTO public.address(id, city, country, street)	VALUES (11, 'Olive Branch', 'Mississippi', '3379 College Rd');
INSERT INTO public.address(id, city, country, street)	VALUES (12, 'Fayette', 'Alabama', '5824 171st Hwy N');
INSERT INTO public.address(id, city, country, street)	VALUES (13, 'Warsaw', 'North Carolina', '716 Curtis Rd');
INSERT INTO public.address(id, city, country, street)	VALUES (14, 'Rockville', 'Maryland', '4712 Norbeck Rd');
INSERT INTO public.address(id, city, country, street)	VALUES (15, 'Napoleonville', 'Louisiana', '704 Jefferson St');
INSERT INTO public.address(id, city, country, street)	VALUES (16, 'Johnstown', 'Pennsylvania', '180 Kenesaw Ln');
INSERT INTO public.address(id, city, country, street)	VALUES (17, 'Hanover', 'Indiana', '5748 W Hensler Rd');


-- USERS
INSERT INTO public.application_user(id, email, enabled, last_name, first_name, password, phone, role, address)	VALUES (1, 'client.email@fishing.com', true, 'Tiegan', 'Glass', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '210-822-0348', '0', '1');
INSERT INTO public.application_user(id, email, enabled, last_name, first_name, password, phone, role, address)	VALUES (2, 'cottage@fishing.com', true, 'Korben', 'Williams', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '812-758-6034', '1', '2');
INSERT INTO public.application_user(id, email, enabled, last_name, first_name, password, phone, role, address)	VALUES (3, 'boat@fishing.com', true, 'Ellouise', 'Price', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '218-851-2525', '2', '3');
INSERT INTO public.application_user(id, email, enabled, last_name, first_name, password, phone, role, address)	VALUES (4, 'adventures@fishing.com', true, 'Filip', 'Magana', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '310-793-7864', '3', '4');
INSERT INTO public.application_user(id, email, enabled, last_name, first_name, password, phone, role, address)	VALUES (5, 'admin@fishing.com', true, 'Admin', 'Admin', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '978-809-5104', '4', '5');

INSERT INTO public.registered_client(is_blocked, penalties, user_id, rank)	VALUES (false, 0, 1, null);
INSERT INTO public.cottage_owner(user_id, rank)	VALUES (2, null);
INSERT INTO public.boat_owner(user_id, rank)	VALUES (3, null);
INSERT INTO public.fishing_instructor(biography, rating, user_id, rank)	VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. In blandit leo sed nibh ultrices tincidunt. Donec vulputate sapien ut mauris sagittis laoreet. Phasellus viverra nibh et eros consectetur, id facilisis est lacinia.', 0.0, 4, null);
INSERT INTO public.administrator(user_id)	VALUES (5);

-- IMAGES

INSERT INTO public.image (id, content, name) VALUES (1, 93231, 'boat');
INSERT INTO public.image (id, content, name) VALUES (2, 93232, 'boat');
INSERT INTO public.image (id, content, name) VALUES (3, 93233, 'cottage');
INSERT INTO public.image (id, content, name) VALUES (4, 93234, 'cottage');
INSERT INTO public.image (id, content, name) VALUES (5, 93235, 'cottage');
INSERT INTO public.image (id, content, name) VALUES (6, 93236, 'boat');
INSERT INTO public.image (id, content, name) VALUES (7, 93237, 'boat');
INSERT INTO public.image (id, content, name) VALUES (8, 93238, 'boat');
INSERT INTO public.image (id, content, name) VALUES (9, 93239, 'boat');
INSERT INTO public.image (id, content, name) VALUES (10, 93240, 'boat');
INSERT INTO public.image (id, content, name) VALUES (11, 93241, 'cottage');
INSERT INTO public.image (id, content, name) VALUES (12, 93243, 'fishing');
INSERT INTO public.image (id, content, name) VALUES (13, 93244, 'fishing');
INSERT INTO public.image (id, content, name) VALUES (14, 93245, 'fishing');
INSERT INTO public.image (id, content, name) VALUES (15, 93246, 'fishing');
INSERT INTO public.image (id, content, name) VALUES (16, 93247, 'fishing');
INSERT INTO public.image (id, content, name) VALUES (17, 93248, 'fishing');
INSERT INTO public.image (id, content, name) VALUES (18, 93249, 'fishing');
INSERT INTO public.image (id, content, name) VALUES (19, 93250, 'fishing');

-- RULES OF CONDUCT

INSERT INTO public.rules_of_conduct(id, rule_description) VALUES (1, 'No drinking');
INSERT INTO public.rules_of_conduct(id, rule_description) VALUES (2, 'No kids');
INSERT INTO public.rules_of_conduct(id, rule_description) VALUES (3, '18+');
INSERT INTO public.rules_of_conduct(id, rule_description) VALUES (4, 'No smoking');
INSERT INTO public.rules_of_conduct(id, rule_description) VALUES (5, 'Treat others as you would like to be treated');
INSERT INTO public.rules_of_conduct(id, rule_description) VALUES (6, 'Respect other peoples property and person');
INSERT INTO public.rules_of_conduct(id, rule_description) VALUES (7, 'Laugh with anyone, but laugh at no one');
INSERT INTO public.rules_of_conduct(id, rule_description) VALUES (8, 'Be responsible for your own learning');

-- UTILITIES

INSERT INTO public.utility(id, name)	VALUES (1, 'Pet-Friendly');
INSERT INTO public.utility(id, name)	VALUES (2, 'WiFi Included');
INSERT INTO public.utility(id, name)	VALUES (3, 'Free Drinks');
INSERT INTO public.utility(id, name)	VALUES (4, 'Health Support');
INSERT INTO public.utility(id, name)	VALUES (5, 'Exclusive');
INSERT INTO public.utility(id, name)	VALUES (6, 'TV');
INSERT INTO public.utility(id, name)	VALUES (7, 'Food Included');
INSERT INTO public.utility(id, name)	VALUES (8, 'Live Music');

-- NAVIGATIONAL EQUIPMENT

INSERT INTO public.navigational_equipment(id, name)	VALUES (1, 1);
INSERT INTO public.navigational_equipment(id, name)	VALUES (2, 2);
INSERT INTO public.navigational_equipment(id, name)	VALUES (3, 3);
INSERT INTO public.navigational_equipment(id, name)	VALUES (4, 4);
INSERT INTO public.navigational_equipment(id, name)	VALUES (5, 5);

-- BOATS

INSERT INTO public.boat(
	id, boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (1, 30.5, 1, 15.0, 'Great boat!', 58.5, 'Basic equipment', 8, 120.0, 'Laura', 4, 35.0, 3.9, 6, 3);
INSERT INTO public.boat(
	id, boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (2, 45.8, 2, 10.0, 'Amazing boat!', 65.5, 'Advanced equipment!', 5, 80.0, 'Sophia', 8, 42.0, 5.0, 10, 3);
INSERT INTO public.boat(
	id, boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (3, 20.0, 3, 15.8, 'Awesome boat!', 20.1, 'Basic equipment!', 6, 75.0, 'Tifa', 7, 85.0, 0.0, 14, 3);
INSERT INTO public.boat(
	id, boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (4, 85.6, 4, 7.2, 'Superb boat!', 120.5, 'Premium equipment!', 15, 150.0, 'Scarlet', 10, 60.0, 4.8, 3, 3);

-- BOAT UTILITIES

INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (1, 0.0, 1, 1);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (2, 0.0, 4, 1);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (3, 5.0, 3, 2);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (4, 5.0, 4, 2);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (5, 0.0, 2, 3);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (6, 8.0, 1, 4);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (7, 8.0, 3, 4);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (8, 35.0, 3, 5);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (9, 25.0, 4, 5);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (10, 5.5, 2, 6);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (11, 0.0, 3, 7);
INSERT INTO public.boat_utility(id, price, boat_id, utility_id)	VALUES (12, 20.0, 4, 8);

-- BOAT NAVIGATIONAL EQUIPMENT

INSERT INTO public.boat_navigational_equipment(boat_id, equipment_id)	VALUES (1, 1);
INSERT INTO public.boat_navigational_equipment(boat_id, equipment_id)	VALUES (1, 2);
INSERT INTO public.boat_navigational_equipment(boat_id, equipment_id)	VALUES (2, 3);
INSERT INTO public.boat_navigational_equipment(boat_id, equipment_id)	VALUES (3, 4);
INSERT INTO public.boat_navigational_equipment(boat_id, equipment_id)	VALUES (3, 5);

-- BOAT RULES OF CONDUCT

INSERT INTO public.boat_rules(boat_id, rule_id)	VALUES (1, 1);
INSERT INTO public.boat_rules(boat_id, rule_id)	VALUES (1, 2);
INSERT INTO public.boat_rules(boat_id, rule_id)	VALUES (2, 3);
INSERT INTO public.boat_rules(boat_id, rule_id)	VALUES (2, 4);
INSERT INTO public.boat_rules(boat_id, rule_id)	VALUES (1, 5);
INSERT INTO public.boat_rules(boat_id, rule_id)	VALUES (4, 6);
INSERT INTO public.boat_rules(boat_id, rule_id)	VALUES (4, 7);
INSERT INTO public.boat_rules(boat_id, rule_id)	VALUES (2, 5);
INSERT INTO public.boat_rules(boat_id, rule_id)	VALUES (2, 8);
