DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  menu_upvote_id     INTEGER NOT NULL DEFAULT 0,
  name               VARCHAR NOT NULL,
  email              VARCHAR NOT NULL,
  password           VARCHAR NOT NULL,
  registered         TIMESTAMP DEFAULT now()
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id            INTEGER NOT NULL,
  role               VARCHAR NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name               VARCHAR NOT NULL,
  description        VARCHAR,
  current_menu_id    INTEGER
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE menus
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name               VARCHAR NOT NULL,
  current_upvotes    INTEGER DEFAULT 0,
  all_upvotes        INTEGER DEFAULT 0,
  description        VARCHAR,
  restaurant_id      INTEGER,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE dishes
(
  id                 INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name               VARCHAR NOT NULL,
  description        VARCHAR,
  price              NUMERIC(6, 2) NOT NULL,
  position           INTEGER NOT NULL,
  menu_id            INTEGER NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
)
