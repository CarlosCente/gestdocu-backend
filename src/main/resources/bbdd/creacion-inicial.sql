CREATE DATABASE IF NOT EXISTS gestdocu;
USE gestdocu;

INSERT INTO `role` (name) VALUES ('ROLE_ADMIN');
INSERT INTO `role` (name) VALUES ('ROLE_USER');

INSERT INTO `users` (email, password, name) VALUES ('pruebas@pruebas.com','$2a$12$8zIk28fWd4jdPkJezHS1ouar3hOTqhdZT1GOaDVeulBP/u37HyePe','pruebas')

INSERT INTO user_role (user_id, role_id) VALUES (1,1);
INSERT INTO user_role (user_id, role_id) VALUES (1,2);
