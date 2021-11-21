-- ADDRESSES
INSERT INTO address(
	id, city, country, street)
	VALUES (1, 'Eastbourne', 'UK', 'Marine Parade 22');
INSERT INTO address(
    id, city, country, street)
    VALUES (2, 'Novi Sad', 'Serbia', 'Bulevar Oslobodjenja 85');
INSERT INTO address(
	id, city, country, street)
	VALUES (3, 'Brighton', 'UK', 'Kingsway 30');
INSERT INTO address(
	id, city, country, street)
	VALUES (4, 'San Francisco', 'USA', 'Jauss St 7');
INSERT INTO address(
	id, city, country, street)
	VALUES (5, 'Winnipeg', 'Canada', 'Main St 62');

INSERT INTO public.address(
	id, city, country, street)
	VALUES (6, 'Camden', 'Mississippi', '2141 17th Hwy');
INSERT INTO public.address(
	id, city, country, street)
	VALUES (7, 'Bowling Green', 'Kentucky', '2105 Robin Rd #T4');
INSERT INTO public.address(
	id, city, country, street)
	VALUES (8, 'Chenoa', 'Illinois', '601 Emma St');
INSERT INTO public.address(
	id, city, country, street)
	VALUES (9, 'Eustis', 'Florida', '845 Edgewater Cir');

-- USERS
INSERT INTO public.application_user(
	id, email, enabled, last_name, first_name, password, phone, role, address)
	VALUES (1, 'client.email@fishing.com', true, 'Tiegan', 'Glass', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '210-822-0348', '0', '1');
INSERT INTO public.application_user(
	id, email, enabled, last_name, first_name, password, phone, role, address)
	VALUES (2, 'cottage@fishing.com', true, 'Korben', 'Williams', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '812-758-6034', '1', '2');
INSERT INTO public.application_user(
	id, email, enabled, last_name, first_name, password, phone, role, address)
	VALUES (3, 'boat@fishing.com', true, 'Ellouise', 'Price', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '218-851-2525', '2', '3');
INSERT INTO public.application_user(
	id, email, enabled, last_name, first_name, password, phone, role, address)
	VALUES (4, 'adventures@fishing.com', true, 'Filip', 'Magana', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '310-793-7864', '3', '4');
INSERT INTO public.application_user(
	id, email, enabled, last_name, first_name, password, phone, role, address)
	VALUES (5, 'admin@fishing.com', true, 'Admin', 'Admin', '$2a$10$KqyJ6IcfwIwV4pkjbfQ7LeGlhRRHTlIKtPKS7ElLJ0NcsOP2Zgpqm', '978-809-5104', '4', '5');

INSERT INTO public.registered_client(
	is_blocked, penalties, user_id, rank)
	VALUES (false, 0, 1, null);
INSERT INTO public.cottage_owner(
	user_id, rank)
	VALUES (2, null);
INSERT INTO public.boat_owner(
	user_id, rank)
	VALUES (3, null);
INSERT INTO public.fishing_instructor(
	biography, rating, user_id, rank)
	VALUES ('Lorem ipsum dolor sit amet, consectetur adipiscing elit. In blandit leo sed nibh ultrices tincidunt. Donec vulputate sapien ut mauris sagittis laoreet. Phasellus viverra nibh et eros consectetur, id facilisis est lacinia.', 0.0, 4, null);
INSERT INTO public.administrator(
	user_id)
	VALUES (5);

--