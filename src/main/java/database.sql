CREATE DATABASE `mynetdisk` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `mynetdisk`;

CREATE TABLE `mynetdisk`.`user`( 
    `id` VARCHAR(40) NOT NULL, 
    `user_name` VARCHAR(20) NOT NULL, 
    `user_password` VARCHAR(20) NOT NULL, 
    `user_email` VARCHAR(20) NOT NULL, 
    PRIMARY KEY (`id`) 
    ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;
CREATE TABLE `mynetdisk`.`share`( 
    `user_id` VARCHAR(40) NOT NULL, 
    `file_address` VARCHAR(50) NOT NULL, 
    `file_password` VARCHAR(20) NOT NULL, 
    `file_share_address` VARCHAR(60) NOT NULL, 
    `file_name` VARCHAR(50) NOT NULL, 
    PRIMARY KEY (`user_id`) 
    ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci; 
CREATE TABLE `mynetdisk`.`file`( 
    `user_id` VARCHAR(40) NOT NULL, 
    `file_name` VARCHAR(50) NOT NULL, 
    `file_address` VARCHAR(50) NOT NULL, 
    `file_size` VARCHAR(50) NOT NULL, 
    `file_format` VARCHAR(10) NOT NULL, 
    `file_share_state` INT(2) NOT NULL DEFAULT 0 COMMENT '文件是否共享', 
    PRIMARY KEY (`user_id`) 
    ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci; 
