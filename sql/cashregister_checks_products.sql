 SET NAMES utf8 ;
DROP TABLE IF EXISTS `checks_products`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `checks_products` (
  `check_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_quantity` int(11) NOT NULL,
  PRIMARY KEY (`product_id`,`check_id`),
  KEY `checks_products_check_id_fk` (`check_id`),
  CONSTRAINT `checks_products_check_id_fk` FOREIGN KEY (`check_id`) REFERENCES `check` (`id`),
  CONSTRAINT `checks_products_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
