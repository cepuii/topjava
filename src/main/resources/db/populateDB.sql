DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-12-19 10:07:04', 'Admin Breakfast', 416, 100001),
       ('2021-07-11 06:51:10', 'Breakfast', 2128, 100000),
       ('2020-11-12 05:31:33', 'Afternoon snack', 1084, 100000);
