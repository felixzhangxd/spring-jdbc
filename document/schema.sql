 DROP SCHEMA spring_jdbc; 
 CREATE SCHEMA IF NOT EXISTS spring_jdbc;
 USE spring_jdbc;
 CREATE TABLE IF NOT EXISTS `User` (
 `id` int auto_increment,
 `username` varchar(12) DEFAULT NULL,
 `password` varchar(12) DEFAULT NULL,
 `birthday` date DEFAULT NULL,
 PRIMARY KEY(id))
 ENGINE=InnoDB DEFAULT CHARSET=utf8;

 GRANT ALL ON spring_jdbc.* TO 'jdbc'@'127.0.0.1' IDENTIFIED BY 'jdbc';
