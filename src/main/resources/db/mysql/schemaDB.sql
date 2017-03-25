CREATE DATABASE IF NOT EXISTS shoppinglist;

USE shoppinglist;

CREATE TABLE IF NOT EXISTS `users` (
  `id`       INT(11)      NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(100) NOT NULL,
  `email`    VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_uindex` (`email`),
  UNIQUE KEY `users_password_uindex` (`password`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

CREATE TABLE IF NOT EXISTS `tasks` (
  `id`      INT(11) NOT NULL                AUTO_INCREMENT,
  `title`   TINYTEXT,
  `user_id` INT(11) NOT NULL,
  `status`  ENUM ('INCOMPLETE', 'COMPLETE') DEFAULT 'INCOMPLETE',
  PRIMARY KEY (`id`),
  KEY `tasks_users_id_fk` (`user_id`),
  CONSTRAINT `tasks_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

CREATE TABLE `items` (
  `id`          INT(11) NOT NULL          AUTO_INCREMENT,
  `task_id`     INT(11) NOT NULL,
  `description` TINYTEXT,
  `status`      ENUM ('READY', 'UNREADY') DEFAULT 'UNREADY',
  PRIMARY KEY (`id`),
  KEY `items_tasks_id_fk` (`task_id`),
  CONSTRAINT `items_tasks_id_fk` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

CREATE TABLE IF NOT EXISTS `roles` (
  `id`   INT(11)                NOT NULL AUTO_INCREMENT,
  `role` ENUM ('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roles_role_uindex` (`role`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  KEY `user_roles_users_id_fk` (`user_id`),
  KEY `user_roles_roles_id_fk` (`role_id`),
  CONSTRAINT `user_roles_roles_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `user_roles_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;