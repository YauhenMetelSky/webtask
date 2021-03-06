-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: projectdb
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `id_appointment` bigint NOT NULL AUTO_INCREMENT,
  `id_client` bigint NOT NULL,
  `id_doctor` bigint NOT NULL,
  `date` date NOT NULL,
  `start` time NOT NULL,
  `id_procedure` bigint NOT NULL,
  `status` enum('CLAIMED','CONFIRMED','CANCELED','ENDED','FINISHED') NOT NULL,
  `end` time NOT NULL DEFAULT '00:00:00',
  PRIMARY KEY (`id_appointment`),
  KEY `client_id_idx` (`id_client`),
  KEY `id_doctor_idx` (`id_doctor`),
  KEY `id_procedure_idx` (`id_procedure`),
  CONSTRAINT `id_doctor` FOREIGN KEY (`id_doctor`) REFERENCES `users` (`user_id`),
  CONSTRAINT `id_procedure` FOREIGN KEY (`id_procedure`) REFERENCES `procedures` (`procedure_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,2,16,'2021-05-27','10:15:00',17,'ENDED','10:45:00'),(2,2,19,'2021-05-31','09:30:00',17,'CANCELED','10:00:00'),(3,2,19,'2021-05-31','10:00:00',17,'ENDED','10:30:00'),(15,2,3,'2021-05-15','12:30:00',17,'ENDED','13:00:00'),(16,2,19,'2021-05-31','09:45:00',17,'ENDED','10:15:00'),(17,2,19,'2021-05-31','09:45:00',17,'CANCELED','10:15:00'),(18,24,19,'2021-06-04','12:00:00',19,'ENDED','12:30:00'),(19,2,3,'2021-06-11','14:15:00',20,'ENDED','15:00:00'),(20,2,3,'2021-06-08','12:00:00',19,'ENDED','13:00:00'),(21,5,9,'2021-06-09','14:00:00',20,'ENDED','14:45:00'),(22,6,9,'2021-06-09','15:00:00',19,'ENDED','16:00:00'),(23,8,9,'2021-06-09','17:00:00',19,'ENDED','18:00:00'),(24,8,9,'2021-06-19','15:00:00',21,'ENDED','15:40:00'),(25,13,9,'2021-06-17','09:00:00',20,'ENDED','09:45:00'),(26,21,19,'2021-06-12','13:00:00',20,'ENDED','13:45:00'),(27,21,19,'2021-06-12','16:00:00',21,'ENDED','16:40:00'),(28,23,19,'2021-06-17','09:30:00',21,'ENDED','10:10:00'),(29,24,19,'2021-06-17','13:00:00',20,'ENDED','13:45:00'),(30,30,19,'2021-06-17','19:45:00',20,'ENDED','20:30:00'),(31,8,9,'2021-06-19','13:00:00',19,'ENDED','14:00:00'),(32,10,9,'2021-06-19','14:00:00',20,'ENDED','14:45:00'),(33,10,9,'2021-06-16','09:00:00',20,'ENDED','09:45:00'),(34,5,9,'2021-06-17','11:45:00',21,'ENDED','12:25:00'),(35,6,9,'2021-06-17','17:00:00',17,'ENDED','17:50:00'),(36,8,9,'2021-06-17','10:00:00',19,'ENDED','11:00:00'),(37,10,9,'2021-06-17','16:00:00',20,'ENDED','16:45:00'),(38,11,9,'2021-06-17','12:45:00',21,'ENDED','13:25:00'),(39,11,9,'2021-06-19','12:00:00',20,'ENDED','12:45:00'),(40,13,9,'2021-06-17','20:00:00',19,'ENDED','21:00:00'),(41,2,9,'2021-06-19','12:45:00',19,'ENDED','13:45:00'),(42,2,3,'2021-06-24','09:00:00',19,'ENDED','10:00:00'),(43,2,16,'2021-06-25','13:00:00',19,'ENDED','14:00:00'),(44,2,3,'2021-06-22','12:15:00',20,'ENDED','13:00:00'),(45,2,16,'2021-06-25','12:00:00',19,'ENDED','13:00:00'),(46,2,19,'2021-07-06','09:30:00',20,'CONFIRMED','10:15:00'),(47,2,19,'2021-07-20','09:00:00',20,'CONFIRMED','09:45:00'),(48,2,19,'2021-07-31','09:00:00',20,'CANCELED','09:45:00'),(49,5,3,'2021-07-05','11:30:00',19,'CONFIRMED','12:30:00'),(50,5,16,'2021-07-02','16:00:00',20,'CONFIRMED','16:45:00'),(51,5,16,'2021-07-17','16:00:00',20,'CONFIRMED','16:45:00'),(52,5,16,'2021-07-31','16:00:00',20,'CONFIRMED','16:45:00'),(53,6,3,'2021-07-06','14:00:00',21,'CONFIRMED','14:40:00'),(54,7,3,'2021-07-12','10:30:00',21,'CONFIRMED','11:10:00'),(55,10,3,'2021-07-17','11:00:00',21,'CONFIRMED','11:40:00'),(56,11,3,'2021-07-17','09:00:00',21,'CONFIRMED','09:40:00'),(57,12,9,'2021-07-12','12:00:00',19,'CONFIRMED','13:00:00'),(58,12,3,'2021-07-17','13:00:00',21,'CONFIRMED','13:40:00'),(59,13,19,'2021-07-01','09:00:00',20,'CONFIRMED','09:45:00'),(60,13,19,'2021-07-15','15:00:00',20,'CONFIRMED','15:45:00'),(61,13,19,'2021-07-31','09:00:00',20,'CONFIRMED','09:45:00'),(62,30,3,'2021-06-27','17:00:00',19,'ENDED','18:00:00'),(63,23,3,'2021-06-27','14:30:00',20,'FINISHED','15:15:00'),(64,25,3,'2021-06-27','13:00:00',21,'FINISHED','13:40:00'),(65,24,3,'2021-06-27','13:45:00',21,'FINISHED','14:25:00'),(66,21,9,'2021-07-20','09:30:00',19,'CLAIMED','10:30:00');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-30 10:15:26
