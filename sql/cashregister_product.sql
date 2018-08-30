 SET NAMES utf8 ;
DROP TABLE IF EXISTS `product`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vendor_code` varchar(20) NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `quantity_in_stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_vendor_code_uindex` (`vendor_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

