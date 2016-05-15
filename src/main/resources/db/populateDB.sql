DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'email@yandex.ru', '12345');