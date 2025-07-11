-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fish_blog_web
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `registered_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `role` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'143 ấp Trung Nghĩa, xã Xuân Trường, huyện Xuân Lộc, tỉnh Đồng Nai','al11admin@fishblog.com','AL11 Admin','2025-07-05 16:52:33','$2a$12$zN.GqLUDHomkjWc3lrkrIOD1v9Ps2lM2SE/N66wakbgjTYZDTMUYK','0395373389','2025-05-03 18:41:02','ADMIN','AL11','/upload/avatar/1751709099909_avt.jpg'),(4,'','admin@fishblog.com','Default Admin','2025-07-08 10:59:48','$2a$12$g1cCPaqYfkRncyQpdMdSEe544XbN2nCXAQurtRJ4kVvQjepOfOBJS','','2025-07-05 16:55:18','ADMIN','ADMIN',NULL),(5,'','robertisreal@gmail.com','Robert Is Real','2025-07-08 14:05:30','$2a$12$tNkEli.8twoKkgxhrd/f1OTsVeCogcrkHL.6vmcMpT2RahBmY.eZ2','','2025-07-07 23:22:33','USER','Robert','/upload/avatar/1751958330252_robert.jpg'),(6,'','johnfunny@gmail.com','John Funny','2025-07-08 14:14:36','$2a$12$LHE9F7WMU6e4Wta2bWLdDOPZ9Qo6q3JBwCaxSOEVajGrNQDsGXl0G','','2025-07-08 10:42:20','USER','John','/upload/avatar/1751958875644_john.jpg'),(7,'','andrehihi@gmail.com','Andre Hihi','2025-07-08 14:15:28','$2a$12$U445dYHMuRa0FoJSh/UGyuQbB6GNcU6YYcPgJIxijAJ3FycHgcaN2','','2025-07-08 10:53:26','USER','Andre','/upload/avatar/1751958928172_andre.jpg');
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

-- Dump completed on 2025-07-11 12:07:35
