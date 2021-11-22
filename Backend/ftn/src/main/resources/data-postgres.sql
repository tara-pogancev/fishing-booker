-- ADDRESSES
INSERT INTO public.address(id, city, country, street)	VALUES (1, 'Eastbourne', 'UK', 'Marine Parade 22');
INSERT INTO public.address(id, city, country, street)  VALUES (2, 'Novi Sad', 'Serbia', 'Bulevar Oslobodjenja 85');
INSERT INTO public.address(id, city, country, street)	VALUES (3, 'Brighton', 'UK', 'Kingsway 30');
INSERT INTO public.address(id, city, country, street)	VALUES (4, 'San Francisco', 'USA', 'Jauss St 7');
INSERT INTO public.address(id, city, country, street)	VALUES (5, 'Winnipeg', 'Canada', 'Main St 62');

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
	VALUES (1, 30.5, 1, 15.0, 'Mauris volutpat molestie venentis. Phasellus lobortis bibendum est, et fringilla magna tempus tempor. Integer pellentesque sem ac tortor euismod finibus. In hac habitasse platea dictumst. Aliquam tincidunt consequat iaculis.', 58.5, 'Basic equipment', 8, 120.0, 'Laura', 4, 35.0, 3.9, 6, 3);
INSERT INTO public.boat(
	id, boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (2, 45.8, 2, 10.0, 'Vivamus quam purus, tempus sit amet tristique at, commodo vitae nisl. Sed eleifend ornare massa, at mattis tellus gravida ac.', 65.5, 'Advanced equipment!', 5, 80.0, 'Sophia', 8, 42.0, 5.0, 7, 3);
INSERT INTO public.boat(
	id, boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (3, 20.0, 3, 15.8, 'Fusce fermentum metus a finibus ultricies. Sed ullamcorper placerat felis quis consectetur. Donec sodales sem sit amet nisi tempus.', 20.1, 'Basic equipment!', 6, 75.0, 'Tifa', 7, 85.0, 0.0, 8, 3);
INSERT INTO public.boat(
	id, boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (4, 85.6, 4, 7.2, 'Praesent auctor erat eros, nec egestas ante fringilla at. In ut enim feugiat, ultricies eros a, lacinia lorem. Aliquam pulvinar id lorem commodo bibendum.', 120.5, 'Premium equipment!', 15, 150.0, 'Scarlet', 10, 60.0, 4.8, 9, 3);

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

-- BOAT IMAGES

INSERT INTO public.boat_images(boat_id, image_id)	VALUES (2, 9);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (3, 7);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (4, 8);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (1, 10);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (2, 6);

-- COTTAGES

INSERT INTO public.cottage(id, description, guest_limit, name, price, rating, address, cottage_owner)
	VALUES (1, 'Ut aliquam magna congue pulvinar interdum. Vestibulum at risus pretium lectus rutrum fringilla non id mi.', 20, 'ShinRa RnR', 65.5, 5.0, 10, 2);
INSERT INTO public.cottage(id, description, guest_limit, name, price, rating, address, cottage_owner)
	VALUES (2, 'Quisque magna ante, molestie quis erat sit amet, interdum dignissim urna. Integer eget turpis varius, dapibus ex sed, finibus turpis. Fusce pharetra maximus purus eget blandit.', 15, 'Peace&Calm', 30.0, 4.5, 11, 2);
INSERT INTO public.cottage(id, description, guest_limit, name, price, rating, address, cottage_owner)
	VALUES (3, 'Praesent auctor erat eros, nec egestas ante fringilla at. In ut enim feugiat, ultricies eros a, lacinia lorem. Aliquam pulvinar id lorem commodo bibendum.', 8, 'Ocean Reset', 45.2, 3.7, 12, 2);
INSERT INTO public.cottage(id, description, guest_limit, name, price, rating, address, cottage_owner)
	VALUES (4, 'Vivamus pellentesque ex vel ligula lobortis, vel interdum libero pellentesque.', 9, 'Forest Dreams', 29.9, 4.6, 13, 2);

-- ROOMS

INSERT INTO public.room(id, number_of_beds, cottage_id)	VALUES (1, 3, 1);
INSERT INTO public.room(id, number_of_beds, cottage_id)	VALUES (2, 4, 1);
INSERT INTO public.room(id, number_of_beds, cottage_id)	VALUES (3, 3, 2);
INSERT INTO public.room(id, number_of_beds, cottage_id)	VALUES (4, 6, 2);
INSERT INTO public.room(id, number_of_beds, cottage_id)	VALUES (5, 2, 4);
INSERT INTO public.room(id, number_of_beds, cottage_id)	VALUES (6, 1, 4);
INSERT INTO public.room(id, number_of_beds, cottage_id)	VALUES (7, 8, 4);

-- COTTAGE RULES OF CONDUCT

INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (1, 1);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (1, 2);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (2, 3);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (2, 4);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (3, 2);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (4, 1);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (4, 5);

-- COTTAGE UTILITIES

INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (1, 0.0, 1, 1);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (2, 0.0, 4, 1);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (3, 5.0, 3, 2);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (4, 5.0, 4, 2);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (5, 0.0, 2, 3);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (6, 8.0, 1, 4);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (7, 8.0, 3, 4);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (8, 35.0, 3, 5);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (9, 25.0, 4, 5);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (10, 5.5, 2, 6);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (11, 0.0, 3, 7);
INSERT INTO public.cottage_utility(id, price, cottage_id, utility_id)	VALUES (12, 20.0, 4, 8);

-- COTTAGE IMAGES

INSERT INTO public.cottage_images(cottage_id, image_id)	VALUES (1, 3);
INSERT INTO public.cottage_images(cottage_id, image_id)	VALUES (2, 5);
INSERT INTO public.cottage_images(cottage_id, image_id)	VALUES (3, 4);
INSERT INTO public.cottage_images(cottage_id, image_id)	VALUES (4, 11);

-- ADVENTURES

INSERT INTO public.adventure(id, cancellation_percentage_keep, description, guest_limit, name, price, rating, address, instructor)
	VALUES (1, 5.5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu nibh consequat, accumsan tortor id, pretium dolor. Aenean eget interdum nisl, nec tempor dolor.', 10, 'Wild Fishing', 25.6, 4.4, 14, 4);
INSERT INTO public.adventure(id, cancellation_percentage_keep, description, guest_limit, name, price, rating, address, instructor)
	VALUES (2, 2.5, 'Nunc nec commodo lacus, pretium elementum dui. Quisque pellentesque elementum laoreet. Etiam viverra libero libero, vel fermentum odio.', 8, 'Outdoor Retreat', 30.9, 4.6, 15, 4);
INSERT INTO public.adventure(id, cancellation_percentage_keep, description, guest_limit, name, price, rating, address, instructor)
	VALUES (3, 10.0, 'Proin sodales lobortis pharetra. Curabitur lacus nisl, sagittis ut pretium a, scelerisque sed turpis. Fusce varius laoreet orci a posuere.', 12, 'Calm Rivers', 39.9, 5.0, 16, 4);
INSERT INTO public.adventure(id, cancellation_percentage_keep, description, guest_limit, name, price, rating, address, instructor)
	VALUES (4, 5.8, 'Nam congue, eros at porttitor porta, justo eros congue mauris, vitae suscipit augue nulla vel mi. Sed vel ante egestas, scelerisque neque a.', 6, 'Fishing With Friends', 45.0, 3.8, 17, 4);

-- ADVENTURE RULES

INSERT INTO public.adventure_rules(adventure_id, rule_id)	VALUES (1, 1);
INSERT INTO public.adventure_rules(adventure_id, rule_id)	VALUES (1, 2);
INSERT INTO public.adventure_rules(adventure_id, rule_id)	VALUES (2, 3);
INSERT INTO public.adventure_rules(adventure_id, rule_id)	VALUES (2, 4);
INSERT INTO public.adventure_rules(adventure_id, rule_id)	VALUES (1, 5);
INSERT INTO public.adventure_rules(adventure_id, rule_id)	VALUES (4, 6);
INSERT INTO public.adventure_rules(adventure_id, rule_id)	VALUES (4, 7);
INSERT INTO public.adventure_rules(adventure_id, rule_id)	VALUES (2, 5);
INSERT INTO public.adventure_rules(adventure_id, rule_id)	VALUES (2, 8);

-- ADVENTURE UTILITIES

INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (1, 0.0, 1, 1);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (2, 0.0, 4, 1);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (3, 5.0, 3, 2);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (4, 5.0, 4, 2);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (5, 0.0, 2, 3);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (6, 8.0, 1, 4);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (7, 8.0, 3, 4);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (8, 35.0, 3, 5);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (9, 25.0, 4, 5);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (10, 5.5, 2, 6);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (11, 0.0, 3, 7);
INSERT INTO public.adventure_utility(id, price, adventure_id, utility_id)	VALUES (12, 20.0, 4, 8);

-- ADVENTURE EQUIPMENT

INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (1, 1);
INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (1, 2);
INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (2, 3);
INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (3, 4);
INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (3, 5);

-- ADVENTURE IMAGES

INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (1, 12);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (1, 13);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (2, 14);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (2, 15);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (3, 16);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (3, 17);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (4, 18);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (4, 19);