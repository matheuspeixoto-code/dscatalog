insert into tb_user(first_name, last_name, email, password) values ('Maria', 'Silva', 'maria@email.com', '123456');

insert into tb_user(first_name, last_name, email, password) values ('Joao', 'Souza', 'joao@email.com', '123456');

insert into tb_role(authority) values ('ROLE_OPERATOR');
insert into tb_role(authority) values ('ROLE_ADMIN');

insert into tb_user_role(user_id, role_id) values (1,2);
insert into tb_user_role(user_id, role_id) values (1,1);
insert into tb_user_role(user_id, role_id) values (2,1);


insert into category(name) values ('Books');
insert into category(name) values ('Electronics');
insert into category(name) values ('Computers');
insert into category(name) values ('Games');
insert into category(name) values ('Movies');
insert into category(name) values ('Music');
insert into category(name) values ('Clothing');
insert into category(name) values ('Shoes');
insert into category(name) values ('Sports');
insert into category(name) values ('Outdoors');
insert into category(name) values ('Home');
insert into category(name) values ('Garden');

insert into tb_product(name, description, price, img_url, date) values ('Notebook Dell','Notebook Dell Inspiron',3500.00,'img1.jpg','2024-01-10T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Mouse Gamer','Mouse RGB 7200 DPI',150.00,'img2.jpg','2024-01-11T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Teclado Mecânico','Teclado switch blue',320.00,'img3.jpg','2024-01-12T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Monitor 24','Monitor Full HD',900.00,'img4.jpg','2024-01-13T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Headset Gamer','Headset com microfone',250.00,'img5.jpg','2024-01-14T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Cadeira Gamer','Cadeira ergonômica',1200.00,'img6.jpg','2024-01-15T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Tênis Nike','Tênis esportivo',450.00,'img7.jpg','2024-01-16T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Camisa Esportiva','Camisa dry fit',120.00,'img8.jpg','2024-01-17T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Bola de Futebol','Bola oficial',90.00,'img9.jpg','2024-01-18T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Violão','Violão acústico',600.00,'img10.jpg','2024-01-19T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Livro Java','Aprenda Java moderno',80.00,'img11.jpg','2024-01-20T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Filme Matrix','BluRay Matrix',60.00,'img12.jpg','2024-01-21T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Guitarra','Guitarra elétrica',1500.00,'img13.jpg','2024-01-22T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Barraca','Barraca camping',300.00,'img14.jpg','2024-01-23T10:00:00Z');
insert into tb_product(name, description, price, img_url, date) values ('Cafeteira','Cafeteira elétrica',200.00,'img15.jpg','2024-01-24T10:00:00Z');

insert into tb_product_category(product_id, category_id) values (1,2);
insert into tb_product_category(product_id, category_id) values (1,3);

insert into tb_product_category(product_id, category_id) values (2,3);

insert into tb_product_category(product_id, category_id) values (3,3);

insert into tb_product_category(product_id, category_id) values (4,2);
insert into tb_product_category(product_id, category_id) values (4,3);

insert into tb_product_category(product_id, category_id) values (5,2);

insert into tb_product_category(product_id, category_id) values (6,4);

insert into tb_product_category(product_id, category_id) values (7,8);
insert into tb_product_category(product_id, category_id) values (7,9);

insert into tb_product_category(product_id, category_id) values (8,7);
insert into tb_product_category(product_id, category_id) values (8,9);

insert into tb_product_category(product_id, category_id) values (9,9);

insert into tb_product_category(product_id, category_id) values (10,6);

insert into tb_product_category(product_id, category_id) values (11,1);

insert into tb_product_category(product_id, category_id) values (12,5);

insert into tb_product_category(product_id, category_id) values (13,6);

insert into tb_product_category(product_id, category_id) values (14,10);

insert into tb_product_category(product_id, category_id) values (15,11);