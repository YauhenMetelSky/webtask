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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `is_blocked` tinyint DEFAULT '0',
  `role` enum('ADMIN','GUEST','USER','DOCTOR') NOT NULL DEFAULT 'USER',
  `is_active` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idusers_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Админ','Admin','4d513d3d','ivan@gmail.com','+375296257162',0,'ADMIN',1),(2,'Ivan','Petrov','4d673d3d','ivanpetrov@gmail.com','+375296597309',0,'USER',1),(3,'Stepan','Petrov','4d773d3d','stepan@gmail.com','+375293711317',0,'DOCTOR',1),(4,'Александр','Vaseckin','4e413d3d','SashaVaseckin@gmail.com','+375295046943',1,'USER',1),(5,'Petya','Ivanov','4e513d3d','petyaivanv@gmail.com','+375298021596',0,'USER',1),(6,'Natasha','Petrova','4e673d3d','natashapetrova@gmail.com','+375292349801',0,'USER',1),(7,'Ольга','Vaseckina','4e773d3d','olgavaseckina@gmail.com','+37529669352',0,'USER',1),(8,'Sasha','Ivanov','4f413d3d','sashaivanov@gmail.com','+375297437192',1,'USER',1),(9,'Ira','Ivanova','4f513d3d','iraivanova@gmail.com','+37529529215',0,'DOCTOR',1),(10,'Таня','Petrova','4d54413d','tanyapetrova@gmail.com','+375298918921',0,'USER',1),(11,'Oleg','Petechkin','4d54453d','olegpetechkin@gmail.com','+375298437261',0,'USER',1),(12,'Ivan','Gor','4d54493d','ivangor@gmail.com','+375292570785',0,'USER',1),(13,'Ира','Ivanova','4d544d3d','iraivanova2@gmail.com','+375296829734',0,'USER',1),(14,'Oleg','Obama','4d54513d','olegobama@gmail.com','+375295297760',0,'USER',1),(15,'Sasha','','4d54553d','sashablohin@gmail.com','+375294443002',0,'USER',1),(16,'Олег','Петров','4d54593d','olegpetrov@mail.ru','+375297771344',0,'DOCTOR',1),(17,'Ivan','Burak','4d54497a','ivanburak@tut.by','+375297125474',0,'USER',1),(18,'Jhon','Stepanov','4d54633d','Jhon@mail.ru','+375296685482',0,'USER',1),(19,'Елена','Потапова','4d546b3d','happy@tut.by','+375291681244',0,'DOCTOR',1),(20,'Татьяна','Стрингова','4d6a413d','IvanRus@mail.ru','+375297351433',0,'USER',1),(21,'Tanya','Шрайбман','4d6a453d','Tanya19@mail.ru','yes',0,'USER',1),(22,'Елена','','4d6a453d','Lena@gmail.com','есть',0,'USER',1),(23,'Vasilii','Petrov','4d6a4d3d','Krasotka@gmail.com','+375333496213',0,'USER',1),(24,'Наташа','Иванова','4d6a513d','NatashaIvanova@gmail.com','+375297789231',0,'USER',1),(25,'Sveta','','4d6a553d','Sveta1951@tut.by','+375449856798',0,'USER',1),(30,'Полина123','Сиб','4d6a593d','Polina@gmail.com','+3752976085485',0,'USER',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-30 10:15:27
