CREATE DATABASE IF NOT EXISTS gestdocu;
USE gestdocu;

--Roles para la propia empresa principal
INSERT INTO `role` (name) VALUES ('ROLE_ADMIN');
INSERT INTO `role` (name) VALUES ('ROLE_USER');
--Roles para las empresas clientes
INSERT INTO `role` (name) VALUES ('ROLE_SUB_ADMIN');
INSERT INTO `role` (name) VALUES ('ROLE_SUB_USER');

--Usuario inicial de administracion (admin/admin) que tiene todos los permisos
INSERT INTO `users` (email, password, name) VALUES (null,'$2a$10$U0Q9RYSCAdI9Xdz1dyUbn.PF9ANbwd9lHbTWZn427sYMuwjdwVPGG','admin')
INSERT INTO user_role (user_id, role_id) VALUES (1,1);
INSERT INTO user_role (user_id, role_id) VALUES (1,2);

