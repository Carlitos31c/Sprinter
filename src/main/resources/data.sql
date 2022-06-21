insert into sprinter.brands (id, name) values (1, 'Adidas');
insert into sprinter.brands (id, name) values (2, 'Nike');

insert into sprinter.items (name, reference_code, brand_id, size, material, color, sport, creation_date)
    values ('Camiseta manga corta running', 'AD643346', 1, 'XS', 'Poliester', 'Blanco', 'Fitness', '2022-01-01T00:00:000Z');
insert into sprinter.items (name, reference_code, brand_id, size, material, color, sport, creation_date)
    values ('Camiseta manga larga running', 'AD234623', 1, 'S', 'Poliester', 'Rojo', 'Fitness', '2022-01-01T00:00:000Z');
insert into sprinter.items (name, reference_code, brand_id, size, material, color, sport, creation_date)
    values ('Camiseta manga corta running', 'NK195431', 2, 'XS', 'Poliester', 'Negro', 'Fitness', '2022-01-01T00:00:000Z');
insert into sprinter.items (name, reference_code, brand_id, size, material, color, sport, creation_date)
    values ('Camiseta manga larga running', 'NK154234', 2, 'S', 'Poliester', 'Amarillo', 'Fitness', '2022-01-01T00:00:000Z');