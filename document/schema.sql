 DROP SCHEMA spring_jdbc; 
 CREATE SCHEMA IF NOT EXISTS spring_jdbc;
 USE spring_jdbc;
 CREATE TABLE IF NOT EXISTS `t_user` (
 `id` int auto_increment,
 `name` varchar(12) DEFAULT NULL,
 `password` varchar(12) DEFAULT NULL,
 PRIMARY KEY(id))
 ENGINE=InnoDB DEFAULT CHARSET=utf8;

 GRANT ALL ON spring_jdbc.* TO 'spring_jdbc'@'127.0.0.1' IDENTIFIED BY 'spring_jdbc';
