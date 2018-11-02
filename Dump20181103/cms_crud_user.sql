-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: cms_crud
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `ACTIVE` tinyint(4) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `fk_USER_ROLE1_idx` (`ROLE_ID`),
  CONSTRAINT `fk_USER_ROLE1` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'harsha','jayamanna','harsha1.kj89@gmail.com','$2a$10$a9JFudwlwkNADC6QhTXfKePb3.Nnd6sEd7toenYowDviLIDNnDQ/m',1,0),(2,'poo','qweqwe','qwe@gm.com','$2a$10$QG9CqgpZ3JMfH3cu2k4CHOiQV5nM0M18HpVe.dGxD0BllA.Yvzbp2',1,0),(3,'asdasd','asdasd','asda@fg.com','$2a$10$ZZ3xft4oMwt4RwA6DmEvGe.AdYkbAZidKviU6a.O0VT943y90L3RK',1,0),(4,'harsha123','jayamanna','harsha1.kj89@gmail.com','$2a$10$DjekXiTk0eDudkfSclnS2e/XuNUK7ua7CYP8oKVU01U0CINwWyIK2',0,0),(5,'harsha','jayamanna','harsha.kj89@gmail.com','$2a$10$yFBWhlFTmymweiYe/LYvSu.gwSKj7NXjvKAU0o2QHrlU7EHra6wvu',1,1),(6,'qwe','qwe','pooja@gmail.com','$2a$10$W/RZLZNrEdQdcxGnVxLzV.9Wu9ZKefxW/3etzTjP7RWo4MvSIy1q6',1,0);
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

-- Dump completed on 2018-11-03  7:31:38
