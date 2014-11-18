CREATE SCHEMA IF NOT EXISTS spring_jdbc DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE spring_jdbc;

CREATE TABLE IF NOT EXISTS `spring_jdbc`.`user` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(12) NOT NULL,
 `is_deleted` TINYINT(1) NOT NULL DEFAULT 0,
 `birthday` DATE NOT NULL,     -- DEFAULT 无用
 `online_time` TIME NOT NULL,  -- DEFAULT 无用
 `add_time` DATETIME NOT NULL, -- DEFAULT 无用  
 `latest_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY(id))
ENGINE=InnoDB;

GRANT ALL ON spring_jdbc.* TO `spring_jdbc`@`127.0.0.1` IDENTIFIED BY 'spring_jdbc';
