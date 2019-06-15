insert into users (username, password, enabled)
values ('admin', '$2a$10$eG28hqAjihXGfSyrNUji9OZEGnMNh66uQUjjIBU0OaaE4Os4u1tom', 1);

insert into users (username, password, enabled)
values ('user', '$2a$10$eG28hqAjihXGfSyrNUji9OZEGnMNh66uQUjjIBU0OaaE4Os4u1tom', 1);

insert into users (username, password, enabled)
values ('user', '$2a$10$6guwzIwUDO9cBtl4FNhbH.Bq9QDZEkQHm6/7PH1Sk9buMjfeExfGG', 1);

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
insert into authorities (username, authority) values ('admin', 'ROLE_USER');
insert into authorities (username, authority) values ('user', 'ROLE_USER');

insert into wallet values (1,'CASH', 'admin', 1);
insert into wallet values (2,'CARD', 'user', 2);

insert into expense values (1,'Kava', 10, 'DRINKS', 1);
insert into expense values (3, 'Kava Julius', 10, 'DRINKS', 1);
insert into expense values (2, 'Ćevapi', 10, 'FOOD', 1);