 SET NAMES utf8 ;
DROP TABLE IF EXISTS `check`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `check_sum` int(11) DEFAULT NULL,
  `check_date` date NOT NULL,
  `date_time` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `check_user_id_fk` (`user_id`),
  CONSTRAINT `check_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

