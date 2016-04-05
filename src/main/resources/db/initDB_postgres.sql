DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval(global_seq),
  name               VARCHAR NOT NULL,
  email              VARCHAR NOT NULL,
  password           VARCHAR NOT NULL,
  registered         TIMESTAMP DEFAULT now()
);

CREATE TABLE user_roles
(
  user_id            INTEGER NOT NULL,
  role               VARCHAR NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval(global_seq),
  name               VARCHAR NOT NULL,
  description        VARCHAR
);

CREATE TABLE menus
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval(global_seq),
  name               VARCHAR NOT NULL,
  descriptioin       VARCHAR,
  restaurant_id      INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval(global_seq),
  comment            VARCHAR,
  date_time          TIMESTAMP NOT NULL DEFAULT now(),
  user_id            INTEGER NOT NULL,
  menu_id            INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
)
