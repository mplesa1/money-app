insert into users (username, password, enabled)
values ('admin', '$2a$10$eG28hqAjihXGfSyrNUji9OZEGnMNh66uQUjjIBU0OaaE4Os4u1tom', 1);

insert into users (username, password, enabled)
values ('user', '$2a$10$eG28hqAjihXGfSyrNUji9OZEGnMNh66uQUjjIBU0OaaE4Os4u1tom', 1);

insert into users (username, password, enabled)
values ('user', '$2a$10$6guwzIwUDO9cBtl4FNhbH.Bq9QDZEkQHm6/7PH1Sk9buMjfeExfGG', 1);

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
insert into authorities (username, authority) values ('admin', 'ROLE_USER');
insert into authorities (username, authority) values ('user', 'ROLE_USER');