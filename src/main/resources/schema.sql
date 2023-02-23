CREATE DATABASE IF NOT EXISTS jdbc_database;

CREATE TABLE IF NOT EXISTS `jdbc_database`.`users`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NULL,
    `email`    VARCHAR(45) NULL,
    `phone`    VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);

CREATE TABLE IF NOT EXISTS `jdbc_database`.`tasks`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45) NULL,
    `description` VARCHAR(45) NULL,
    `user_id`     INT         NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);