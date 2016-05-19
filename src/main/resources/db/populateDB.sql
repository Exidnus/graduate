DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Vasiliy', 'vasiliy@yandex.ru', '12345'),
  ('Karl', 'karl@google.com', 'abcd');

INSERT INTO user_roles VALUES
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_USER'),
  (100001, 'ROLE_ADMIN');

INSERT INTO restaurants (name, description) VALUES
  ('Тайский ресторан', 'Маленький ресторан с тайской едой, шеф-повар родом из Таиланда!'),
  ('Pizza and sushi', 'Интересное смешение культур: в этом ресторане подают как пиццу, так и суши'),
  ('Coffee and delicious cakes', 'Любите кофе с вкусными пирожными? Тогда вам сюда.');

INSERT INTO menus (name, description, restaurant_id, current_upvotes, all_upvotes) VALUES
  ('Тайское меню 1', 'Стандартное меню', 100002, 15, 15),
  ('Тайское меню 2', 'Меню для праздничных дней', 100002, 25, 75),
  ('Pizza and sushi menu 1', 'Standart menu', 100003, 43, 43),
  ('Coffee and delicious cakes standart menu', 'Just standart menu', 100004, 0, 0);

INSERT INTO dishes (name, description, price, position, menu_id) VALUES
  ('Тайское обычное блюдо 1', 'Описание обычного блюда 1', 155.00, 0, 100005),
  ('Тайское обычное блюдо 2', 'Описание обычного блюда 2', 45.00, 1, 100005),
  ('Тайское обычное блюдо 3', 'Описание обычного блюда 3', 25.25, 2, 100005),
  ('Тайское праздничное блюдо 1', 'Описание праздничного блюда 1', 250.45, 0, 100006),
  ('Тайское праздничное блюдо 2', 'Описание праздничного блюда 2', 55.00, 1, 100006),
  ('Pizza and sushi dish 1', 'Description pizza and sushi dish 1', 120.00, 0, 100007),
  ('Pizza and sushi dish 2', 'Description pizza and sushi dish 2', 100.00, 1, 100007),
  ('Pizza and sushi dish 3', 'Description pizza and sushi dish 3', 10.45, 2, 100007),
  ('Pizza and sushi dish 4', 'Description pizza and sushi dish 4', 27.30, 3, 100007),
  ('Pizza and sushi dish 5', 'Description pizza and sushi dish 1', 5.50, 4, 100007),
  ('Coffee and delicious cakes блюдо 1', 'Описание блюда Coffee and delicious cakes 1', 110.75, 0, 100008),
  ('Coffee and delicious cakes блюдо 2', 'Описание блюда Coffee and delicious cakes 2', 25.00, 1, 100008);

UPDATE restaurants SET current_menu_id=100006 WHERE id=100002;
UPDATE restaurants SET current_menu_id=100007 WHERE id=100003;
