-- ADDRESSES
INSERT INTO public.address(city, country, street)	VALUES ('Eastbourne', 'UK', 'Marine Parade 22');
INSERT INTO public.address(city, country, street)  VALUES ('Novi Sad', 'Serbia', 'Bulevar Oslobodjenja 85');
INSERT INTO public.address(city, country, street)	VALUES ('Brighton', 'UK', 'Kingsway 30');
INSERT INTO public.address(city, country, street)	VALUES ('San Francisco', 'USA', 'Jauss St 7');
INSERT INTO public.address(city, country, street)	VALUES ('Winnipeg', 'Canada', 'Main St 62');

INSERT INTO public.address(city, country, street)   VALUES ('Camden', 'Mississippi', '2141 17th Hwy');
INSERT INTO public.address(city, country, street)	VALUES ('Bowling Green', 'Kentucky', '2105 Robin Rd T4');
INSERT INTO public.address(city, country, street)	VALUES ('Chenoa', 'Illinois', '601 Emma St');
INSERT INTO public.address(city, country, street)	VALUES ('Eustis', 'Florida', '845 Edgewater Cir');
INSERT INTO public.address(city, country, street)	VALUES ('Somers Point', 'New Jersey', '1418 Massachusetts Ave');
INSERT INTO public.address(city, country, street)	VALUES ('Olive Branch', 'Mississippi', '3379 College Rd');
INSERT INTO public.address(city, country, street)	VALUES ('Fayette', 'Alabama', '5824 171st Hwy N');
INSERT INTO public.address(city, country, street)	VALUES ('Warsaw', 'North Carolina', '716 Curtis Rd');
INSERT INTO public.address(city, country, street)	VALUES ('Rockville', 'Maryland', '4712 Norbeck Rd');
INSERT INTO public.address(city, country, street)	VALUES ('Napoleonville', 'Louisiana', '704 Jefferson St');
INSERT INTO public.address(city, country, street)	VALUES ('Johnstown', 'Pennsylvania', '180 Kenesaw Ln');
INSERT INTO public.address(city, country, street)	VALUES ('Hanover', 'Indiana', '5748 W Hensler Rd');


-- USERS

INSERT INTO public.application_user(email, enabled, last_name, first_name, password, phone, role, address)	VALUES ('client.email@fishing.com', true, 'Tiegan', 'Glass', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '210-822-0348', '0', '1');
INSERT INTO public.application_user(email, enabled, last_name, first_name, password, phone, role, address)	VALUES ('cottage@fishing.com', true, 'Korben', 'Williams', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '812-758-6034', '1', '2');
INSERT INTO public.application_user(email, enabled, last_name, first_name, password, phone, role, address)	VALUES ('boat@fishing.com', true, 'Ellouise', 'Price', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '218-851-2525', '2', '3');
INSERT INTO public.application_user(email, enabled, last_name, first_name, password, phone, role, address)	VALUES ('adventures@fishing.com', true, 'Filip', 'Magana', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '310-793-7864', '3', '4');
INSERT INTO public.application_user(email, enabled, last_name, first_name, password, phone, role, address)	VALUES ('admin@fishing.com', true, 'Admin', 'Admin', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '978-809-5104', '4', '5');

INSERT INTO public.registered_client(is_blocked, penalties, user_id, rank)	VALUES (false, 0, 1, null);
INSERT INTO public.cottage_owner(user_id, rank)	VALUES (2, null);
INSERT INTO public.boat_owner(user_id, rank)	VALUES (3, null);
INSERT INTO public.fishing_instructor(biography, rating, id, rank)	VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. In blandit leo sed nibh ultrices tincidunt. Donec vulputate sapien ut mauris sagittis laoreet. Phasellus viverra nibh et eros consectetur, id facilisis est lacinia.', 0.0, 4, null);
INSERT INTO public.administrator(user_id)	VALUES (5);

-- IMAGES
--boat images  id 1-8
INSERT INTO public.image (url) VALUES ('assets/images/boat.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/boat2.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/boat3.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/boat4.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/boat5.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/boat6.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/boat-interior/interior1.jpeg');
INSERT INTO public.image (url) VALUES ('assets/images/boat-interior/interior2.jpeg');
--cottage images id 9-12
INSERT INTO public.image (url) VALUES ('assets/images/cottage.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/cottage-interior/interior1.jpeg');
INSERT INTO public.image (url) VALUES ('assets/images/cottage-interior/interior2.jpeg');
INSERT INTO public.image (url) VALUES ('assets/images/cottage-interior/interior3.jpeg');
--fishing adventures images id 13-20
INSERT INTO public.image (url) VALUES ('assets/images/fish.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/fish2.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/fish3.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/fish4.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/fishing.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/fishing2.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/fishing3.jpg');
INSERT INTO public.image (url) VALUES ('assets/images/fishing4.jpg');




-- RULES OF CONDUCT

INSERT INTO public.rules_of_conduct(rule_description) VALUES ('No drinking');
INSERT INTO public.rules_of_conduct(rule_description) VALUES ('No kids');
INSERT INTO public.rules_of_conduct(rule_description) VALUES ('18+');
INSERT INTO public.rules_of_conduct(rule_description) VALUES ('No smoking');
INSERT INTO public.rules_of_conduct(rule_description) VALUES ('Treat others as you would like to be treated');
INSERT INTO public.rules_of_conduct(rule_description) VALUES ('Respect other peoples property and person');
INSERT INTO public.rules_of_conduct(rule_description) VALUES ('Laugh with anyone, but laugh at no one');
INSERT INTO public.rules_of_conduct(rule_description) VALUES ('Be responsible for your own learning');

-- UTILITIES

INSERT INTO public.utility(name)	VALUES ('Pet-Friendly');
INSERT INTO public.utility(name)	VALUES ('WiFi Included');
INSERT INTO public.utility(name)	VALUES ('Free Drinks');
INSERT INTO public.utility(name)	VALUES ('Health Support');
INSERT INTO public.utility(name)	VALUES ('Exclusive');
INSERT INTO public.utility(name)	VALUES ('TV');
INSERT INTO public.utility(name)	VALUES ('Food Included');
INSERT INTO public.utility(name)	VALUES ('Live Music');

-- NAVIGATIONAL EQUIPMENT

INSERT INTO public.navigational_equipment(name)	VALUES (1);
INSERT INTO public.navigational_equipment(name)	VALUES (2);
INSERT INTO public.navigational_equipment(name)	VALUES (3);
INSERT INTO public.navigational_equipment(name)	VALUES (4);
INSERT INTO public.navigational_equipment(name)	VALUES (5);

-- BOATS

INSERT INTO public.boat(
	boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (30.5, 1, 15.0, 'Mauris volutpat molestie venentis. Phasellus lobortis bibendum est, et fringilla magna tempus tempor. Integer pellentesque sem ac tortor euismod finibus. In hac habitasse platea dictumst. Aliquam tincidunt consequat iaculis.', 58.5, 'Basic equipment', 8, 120.0, 'Laura', 4, 35.0, 3.9, 6, 3);
INSERT INTO public.boat(
	boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (45.8, 2, 10.0, 'Vivamus quam purus, tempus sit amet tristique at, commodo vitae nisl. Sed eleifend ornare massa, at mattis tellus gravida ac.', 65.5, 'Advanced equipment!', 5, 80.0, 'Sophia', 8, 42.0, 5.0, 7, 3);
INSERT INTO public.boat(
	boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (20.0, 3, 15.8, 'Fusce fermentum metus a finibus ultricies. Sed ullamcorper placerat felis quis consectetur. Donec sodales sem sit amet nisi tempus.', 20.1, 'Basic equipment!', 6, 75.0, 'Tifa', 7, 85.0, 0.0, 8, 3);
INSERT INTO public.boat(
	boat_length, boat_type, cancellation_percentage_keep, description, engine_power, fishing_equipment, guest_limit, max_speed, name, number_of_engines, price, rating, address, boat_owner)
	VALUES (85.6, 4, 7.2, 'Praesent auctor erat eros, nec egestas ante fringilla at. In ut enim feugiat, ultricies eros a, lacinia lorem. Aliquam pulvinar id lorem commodo bibendum.', 120.5, 'Premium equipment!', 15, 150.0, 'Scarlet', 10, 60.0, 4.8, 9, 3);

-- BOAT UTILITIES

INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (0.0, 1, 1);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (0.0, 4, 1);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (5.0, 3, 2);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (5.0, 4, 2);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (0.0, 2, 3);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (8.0, 1, 4);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (8.0, 3, 4);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (35.0, 3, 5);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (25.0, 4, 5);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (5.5, 2, 6);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (0.0, 3, 7);
INSERT INTO public.boat_utility(price, boat_id, utility_id)	VALUES (20.0, 4, 8);

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

INSERT INTO public.boat_images(boat_id, image_id)	VALUES (2, 1);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (3, 2);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (4, 3);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (1, 4);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (2, 5);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (2, 3);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (3, 5);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (4, 7);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (1, 8);
INSERT INTO public.boat_images(boat_id, image_id)	VALUES (2, 8);

-- COTTAGES

INSERT INTO public.cottage(description, guest_limit, name, price, rating, address, cottage_owner)
	VALUES ('Ut aliquam magna congue pulvinar interdum. Vestibulum at risus pretium lectus rutrum fringilla non id mi.', 20, 'ShinRa RnR', 65.5, 5.0, 10, 2);
INSERT INTO public.cottage(description, guest_limit, name, price, rating, address, cottage_owner)
	VALUES ('Quisque magna ante, molestie quis erat sit amet, interdum dignissim urna. Integer eget turpis varius, dapibus ex sed, finibus turpis. Fusce pharetra maximus purus eget blandit.', 15, 'Peace&Calm', 30.0, 4.5, 11, 2);
INSERT INTO public.cottage(description, guest_limit, name, price, rating, address, cottage_owner)
	VALUES ('Praesent auctor erat eros, nec egestas ante fringilla at. In ut enim feugiat, ultricies eros a, lacinia lorem. Aliquam pulvinar id lorem commodo bibendum.', 8, 'Ocean Reset', 45.2, 3.7, 12, 2);
INSERT INTO public.cottage(description, guest_limit, name, price, rating, address, cottage_owner)
	VALUES ('Vivamus pellentesque ex vel ligula lobortis, vel interdum libero pellentesque.', 9, 'Forest Dreams', 29.9, 4.6, 13, 2);

-- ROOMS

INSERT INTO public.room(number_of_beds, cottage_id)	VALUES (3, 1);
INSERT INTO public.room(number_of_beds, cottage_id)	VALUES (4, 1);
INSERT INTO public.room(number_of_beds, cottage_id)	VALUES (3, 2);
INSERT INTO public.room(number_of_beds, cottage_id)	VALUES (6, 2);
INSERT INTO public.room(number_of_beds, cottage_id)	VALUES (2, 4);
INSERT INTO public.room(number_of_beds, cottage_id)	VALUES (1, 4);
INSERT INTO public.room(number_of_beds, cottage_id)	VALUES (8, 4);

-- COTTAGE RULES OF CONDUCT

INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (1, 1);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (1, 2);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (2, 3);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (2, 4);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (3, 2);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (4, 1);
INSERT INTO public.cottage_rules(cottage_id, rule_id)	VALUES (4, 5);

-- COTTAGE UTILITIES

INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (0.0, 1, 1);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (0.0, 4, 1);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (5.0, 3, 2);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (5.0, 4, 2);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (0.0, 2, 3);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (8.0, 1, 4);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (8.0, 3, 4);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (35.0, 3, 5);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (25.0, 4, 5);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (5.5, 2, 6);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (0.0, 3, 7);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id)	VALUES (20.0, 4, 8);

-- COTTAGE IMAGES

INSERT INTO public.cottage_images(cottage_id, image_id)	VALUES (1, 9);
INSERT INTO public.cottage_images(cottage_id, image_id)	VALUES (2, 10);
INSERT INTO public.cottage_images(cottage_id, image_id)	VALUES (3, 11);
INSERT INTO public.cottage_images(cottage_id, image_id)	VALUES (4, 12);

-- ADVENTURES

INSERT INTO public.adventure(cancellation_percentage_keep, description, guest_limit, name, price, rating, address, instructor)
	VALUES (5.5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu nibh consequat, accumsan tortor id, pretium dolor. Aenean eget interdum nisl, nec tempor dolor.', 10, 'Wild Fishing', 25.6, 4.4, 14, 4);
INSERT INTO public.adventure(cancellation_percentage_keep, description, guest_limit, name, price, rating, address, instructor)
	VALUES (2.5, 'Nunc nec commodo lacus, pretium elementum dui. Quisque pellentesque elementum laoreet. Etiam viverra libero libero, vel fermentum odio.', 8, 'Outdoor Retreat', 30.9, 4.6, 15, 4);
INSERT INTO public.adventure(cancellation_percentage_keep, description, guest_limit, name, price, rating, address, instructor)
	VALUES (10.0, 'Proin sodales lobortis pharetra. Curabitur lacus nisl, sagittis ut pretium a, scelerisque sed turpis. Fusce varius laoreet orci a posuere.', 12, 'Calm Rivers', 39.9, 5.0, 16, 4);
INSERT INTO public.adventure(cancellation_percentage_keep, description, guest_limit, name, price, rating, address, instructor)
	VALUES (5.8, 'Nam congue, eros at porttitor porta, justo eros congue mauris, vitae suscipit augue nulla vel mi. Sed vel ante egestas, scelerisque neque a.', 6, 'Fishing With Friends', 45.0, 3.8, 17, 4);

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

INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (0.0, 1, 1);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (0.0, 4, 1);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (5.0, 3, 2);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (5.0, 4, 2);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (0.0, 2, 3);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (8.0, 1, 4);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (8.0, 3, 4);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (35.0, 3, 5);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (25.0, 4, 5);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (5.5, 2, 6);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (0.0, 3, 7);
INSERT INTO public.adventure_utility(price, adventure_id, utility_id)	VALUES (20.0, 4, 8);

-- ADVENTURE EQUIPMENT

--INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (1, 1);
--INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (1, 2);
--INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (2, 3);
--INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (3, 4);
--INSERT INTO public.adventure_navigational_equipment(adventure_id, equipment_id)	VALUES (3, 5);

-- ADVENTURE IMAGES

INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (1,13);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (1, 14);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (2, 15);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (2, 16);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (3, 17);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (3, 18);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (4, 19);
INSERT INTO public.adventure_images(adventure_id, image_id)	VALUES (4, 20);

-- SYSTEM PROPERTIES

INSERT INTO public.system_properties(gold_points, income_percentage, reservation_points, silver_points) VALUES (1500, 10, 15, 750);

-- AVAILABLE TIME PERIOD

INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20211205 10:00:00 AM', '20211225 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20220105 10:00:00 AM', '20220125 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20220105 10:00:00 AM', '20220125 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20211205 10:00:00 AM', '20211225 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20211205 10:00:00 AM', '20211225 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20220105 10:00:00 AM', '20220125 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20220105 10:00:00 AM', '20220125 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20211205 10:00:00 AM', '20211225 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20211205 10:00:00 AM', '20211210 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20211225 10:00:00 AM', '20211230 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20220105 10:00:00 AM', '20220110 10:00:00 AM');
INSERT INTO public.available_time_period(start_date, end_date)	VALUES ('20220113 10:00:00 AM', '20220124 10:00:00 AM');

-- BOAT AVAILABILITY

INSERT INTO public.available_boat_time_period(id, boat)	VALUES (1, 1);
--INSERT INTO public.available_boat_time_period(id, boat)	VALUES (2, 2);
--INSERT INTO public.available_boat_time_period(id, boat)	VALUES (3, 3);
--INSERT INTO public.available_boat_time_period(id, boat)	VALUES (4, 4);

-- COTTAGE AVAILABILITY

INSERT INTO public.available_cottage_time_period(id, cottage)	VALUES (5, 1);
INSERT INTO public.available_cottage_time_period(id, cottage)	VALUES (6, 2);
INSERT INTO public.available_cottage_time_period(id, cottage)	VALUES (7, 3);
INSERT INTO public.available_cottage_time_period(id, cottage)	VALUES (8, 4);

-- FISHING AVAILABILITY

INSERT INTO public.available_instructor_time_period(id, instructor)	VALUES (9, 4);
INSERT INTO public.available_instructor_time_period(id, instructor)	VALUES (10, 4);
INSERT INTO public.available_instructor_time_period(id, instructor)	VALUES (11, 4);
INSERT INTO public.available_instructor_time_period(id, instructor)	VALUES (12, 4);
