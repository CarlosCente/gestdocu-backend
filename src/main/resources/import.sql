/* Creamos algunos usuarios con sus roles */
INSERT INTO `users` (username, password, enabled) VALUES ('pruebas','$2a$12$8zIk28fWd4jdPkJezHS1ouar3hOTqhdZT1GOaDVeulBP/u37HyePe',1);
INSERT INTO `users` (username, password, enabled) VALUES ('admin','$2a$12$sms/Q/206VGtefDsnz913O6BSWajKih4Rc2VJQghb3/2QjujwYE16',1);

INSERT INTO `authorities` (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_USER');