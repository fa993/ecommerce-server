-- MariaDB dump 10.19  Distrib 10.6.4-MariaDB, for osx10.16 (arm64)
--
-- Host: localhost    Database: ecommerce_server
-- ------------------------------------------------------
-- Server version	10.6.4-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_id` varchar(255) NOT NULL,
  `cost` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `seller_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK8gwmxr8c03vtek0yq8iw9j2mb` (`seller_id`),
  CONSTRAINT `FK8gwmxr8c03vtek0yq8iw9j2mb` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('08eae586-7bcd-4f57-9ad4-0812080c6b0e',57,'Testing 15','e5f3eed4-8792-11ec-b612-6688efc193e1'),('122a971f-e725-4ec1-bbb1-7e63f1edee02',52,'Testing 3','e5f3eed4-8792-11ec-b612-6688efc193e1'),('1ed15ea4-5745-4d35-8181-7a8ac5c1b2de',122,'Testing 10','e5f3eed4-8792-11ec-b612-6688efc193e1'),('41a711e8-3f4d-479f-906c-3c5fa0cb88ca',2,'Testing 14','e5f3eed4-8792-11ec-b612-6688efc193e1'),('60b52e0e-9796-49d8-81eb-cb030b93b49f',112,'Testing 8','e5f3eed4-8792-11ec-b612-6688efc193e1'),('610d9e51-7d5e-49f9-8d97-3c05c4ee9a5e',42,'Testing 2','e5f3eed4-8792-11ec-b612-6688efc193e1'),('66a72056-9352-4db6-82b3-7939d60859f4',92,'Testing 7','e5f3eed4-8792-11ec-b612-6688efc193e1'),('7cbbf59c-6e95-4988-91cb-cf191b1284df',102,'Testing 9','e5f3eed4-8792-11ec-b612-6688efc193e1'),('8a0c8336-d854-4efe-a71a-c6a113d87fc5',32,'Testing 1','e5f3eed4-8792-11ec-b612-6688efc193e1'),('94f620f4-73a9-469f-b75d-d1b3fc3a12e9',82,'Testing 6','e5f3eed4-8792-11ec-b612-6688efc193e1'),('98af748e-7592-4e87-a114-199ad61a63f7',270,'Testing 13','e5f3eed4-8792-11ec-b612-6688efc193e1'),('b5ed5913-ec51-4f0c-b3d4-63681a366bc1',132,'Testing 11','e5f3eed4-8792-11ec-b612-6688efc193e1'),('c2875b1d-d06d-4fbe-b09d-2ca92b20ce5f',62,'Testing 4','e5f3eed4-8792-11ec-b612-6688efc193e1'),('ca9419f0-65b2-4a41-9900-95801f2ea74e',20,'Testing 12','e5f3eed4-8792-11ec-b612-6688efc193e1'),('e88cd37c-53d6-4656-88b5-ba3317d84647',72,'Testing 5','e5f3eed4-8792-11ec-b612-6688efc193e1');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_order`
--

DROP TABLE IF EXISTS `sales_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_order` (
  `order_id` varchar(255) NOT NULL,
  `completed` bit(1) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `buyer_id` varchar(255) DEFAULT NULL,
  `item_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKfcdimsh35dij7axxby9f1y0c3` (`buyer_id`),
  KEY `FKdb7i1uobkquksx4vqe87bk62o` (`item_id`),
  CONSTRAINT `FKdb7i1uobkquksx4vqe87bk62o` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `FKfcdimsh35dij7axxby9f1y0c3` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_order`
--

LOCK TABLES `sales_order` WRITE;
/*!40000 ALTER TABLE `sales_order` DISABLE KEYS */;
INSERT INTO `sales_order` VALUES ('46716ae6-9a25-4ebe-860b-80918f28dae1','',3,'e5f3eed4-8792-11ec-b612-6688efc193e1','98af748e-7592-4e87-a114-199ad61a63f7');
/*!40000 ALTER TABLE `sales_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('e5f3eed4-8792-11ec-b612-6688efc193e1','example@gmail.com','admin',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-07  6:30:30
