CREATE DATABASE IF NOT EXISTS shoppinglist;

USE shoppinglist;

CREATE TABLE IF NOT EXISTS `users`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_uindex` (`email`),
  UNIQUE KEY `users_password_uindex` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` tinytext,
  `user_id` int(11) NOT NULL,
  `status` enum('INCOMPLETE','COMPLETE') DEFAULT 'INCOMPLETE',
  PRIMARY KEY (`id`),
  KEY `tasks_users_id_fk` (`user_id`),
  CONSTRAINT `tasks_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `description` tinytext,
  `status` enum('READY','UNREADY') DEFAULT 'UNREADY',
  PRIMARY KEY (`id`),
  KEY `items_tasks_id_fk` (`task_id`),
  CONSTRAINT `items_tasks_id_fk` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;