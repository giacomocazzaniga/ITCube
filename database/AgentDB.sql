-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: agentwindows
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
-- Table structure for table `alert`
--

DROP TABLE IF EXISTS `alert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alert` (
  `id` int NOT NULL AUTO_INCREMENT,
  `corpo_messaggio` varchar(255) DEFAULT NULL,
  `date_and_time_alert` datetime(6) DEFAULT NULL,
  `date_and_time_mail` datetime(6) DEFAULT NULL,
  `id_client` int NOT NULL,
  `id_company` int NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert`
--

LOCK TABLES `alert` WRITE;
/*!40000 ALTER TABLE `alert` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conf_total_free_disc_space`
--

DROP TABLE IF EXISTS `conf_total_free_disc_space`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conf_total_free_disc_space` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_and_time` datetime(6) DEFAULT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `drive` varchar(255) DEFAULT NULL,
  `id_client` int NOT NULL,
  `perc_free_disc_space` double NOT NULL,
  `total_free_disc_space` bigint NOT NULL,
  `total_size` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conf_total_free_disc_space`
--

LOCK TABLES `conf_total_free_disc_space` WRITE;
/*!40000 ALTER TABLE `conf_total_free_disc_space` DISABLE KEYS */;
INSERT INTO `conf_total_free_disc_space` VALUES (1,'2021-04-16 09:35:56.780395','C','C',1,20,20000000000000000,100000000000000000),(5,'2021-04-13 12:20:34.997982','C','C',14,10,10000000000000000,100000000000000000),(6,'2021-04-13 12:20:34.997982','D','D',14,20,20000000000000000,100000000000000000),(7,'2021-04-16 09:35:56.780395','D','D',1,90,90000000000000000,100000000000000000),(8,'2021-04-20 10:04:14.615092','C','C',16,20,20000000000000000,100000000000000000),(9,'2021-04-20 10:04:14.615092','D','D',16,90,90000000000000000,100000000000000000),(10,'2021-04-20 14:21:18.593214','C','C',17,20,20000000000000000,100000000000000000),(11,'2021-04-20 14:21:18.593214','D','D',17,90,90000000000000000,100000000000000000);
/*!40000 ALTER TABLE `conf_total_free_disc_space` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conf_windows_services`
--

DROP TABLE IF EXISTS `conf_windows_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conf_windows_services` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_and_time` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `service_type` int NOT NULL,
  `start_type` int NOT NULL,
  `stato` int NOT NULL,
  `id_client` int DEFAULT NULL,
  `nome_servizio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmqd53i0wh0trbaw0cs4j9nbmd` (`id_client`),
  KEY `FKgweg5amjhfcpfifx1e0kx7olq` (`nome_servizio`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conf_windows_services`
--

LOCK TABLES `conf_windows_services` WRITE;
/*!40000 ALTER TABLE `conf_windows_services` DISABLE KEYS */;
INSERT INTO `conf_windows_services` VALUES (2,'2021-04-12 14:10:54.155083',NULL,'ActiveX Installer (AxInstSV)3',1,1,1,1,'ActiveX Installer (AxInstSV)3'),(3,'2021-04-12 14:10:54.155083',NULL,'Adobe Genuine Software Integrity Service3',1,1,1,1,'Adobe Genuine Software Integrity Service3'),(4,'2021-04-12 14:10:54.155083',NULL,'AdobeUpdateService3',1,1,1,1,'AdobeUpdateService3'),(5,'2021-04-12 14:13:03.690581',NULL,'ActiveX Installer (AxInstSV)3',1,1,1,1,'ActiveX Installer (AxInstSV)3'),(6,'2021-04-12 14:13:03.690581',NULL,'Adobe Genuine Software Integrity Service3',1,1,1,1,'Adobe Genuine Software Integrity Service3'),(7,'2021-04-12 14:13:03.690581',NULL,'AdobeUpdateService3',1,1,4,1,'AdobeUpdateService3'),(8,'2021-04-13 10:29:17.883958',NULL,'ActiveX Installer (AxInstSV)',1,1,4,14,'ActiveX Installer (AxInstSV)'),(9,'2021-04-13 10:29:17.883958',NULL,'Adobe Genuine Software Integrity Service',1,1,4,14,'Adobe Genuine Software Integrity Service'),(10,'2021-04-13 10:29:17.883958',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(11,'2021-04-13 10:30:49.141768',NULL,'ActiveX Installer (AxInstSV)',1,1,1,14,'ActiveX Installer (AxInstSV)'),(12,'2021-04-13 10:30:49.141768',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(13,'2021-04-13 10:30:49.141768',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(14,'2021-04-13 11:02:09.247302',NULL,'ActiveX Installer (AxInstSV)',1,1,1,14,'ActiveX Installer (AxInstSV)'),(15,'2021-04-13 11:02:09.247302',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(16,'2021-04-13 11:02:09.247302',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(17,'2021-04-13 11:02:40.095747',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(18,'2021-04-13 11:02:40.095747',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(19,'2021-04-13 11:02:40.095747',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(20,'2021-04-13 11:07:48.704012',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(21,'2021-04-13 11:07:48.704012',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(22,'2021-04-13 11:07:48.704012',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(23,'2021-04-13 11:08:25.890125',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(24,'2021-04-13 11:08:25.890125',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(25,'2021-04-13 11:08:25.890125',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(26,'2021-04-13 12:15:32.133611',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(27,'2021-04-13 12:15:32.133611',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(28,'2021-04-13 12:15:32.133611',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(29,'2021-04-13 12:16:19.897418',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(30,'2021-04-13 12:16:19.897418',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(31,'2021-04-13 12:16:19.897418',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(32,'2021-04-13 12:16:49.344468',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(33,'2021-04-13 12:16:49.344468',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(34,'2021-04-13 12:16:49.344468',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(35,'2021-04-13 12:17:15.291751',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(36,'2021-04-13 12:17:15.291751',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(37,'2021-04-13 12:17:15.291751',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(38,'2021-04-13 12:20:34.997982',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(39,'2021-04-13 12:20:34.997982',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(40,'2021-04-13 12:20:34.997982',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(41,'2021-04-13 12:23:07.534573',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(42,'2021-04-13 12:23:07.534573',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(43,'2021-04-13 12:23:07.534573',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(44,'2021-04-13 12:26:01.900035',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(45,'2021-04-13 12:26:01.900035',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(46,'2021-04-13 12:26:01.900035',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(47,'2021-04-13 12:28:03.742594',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(48,'2021-04-13 12:28:03.742594',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(49,'2021-04-13 12:28:03.742594',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(50,'2021-04-13 12:29:11.605838',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(51,'2021-04-13 12:29:11.605838',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(52,'2021-04-13 12:29:11.605838',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(53,'2021-04-13 12:30:19.049638',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(54,'2021-04-13 12:30:19.049638',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(55,'2021-04-13 12:30:19.049638',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(56,'2021-04-13 12:32:37.633081',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,14,'ActiveX Installer (AxInstSV)A'),(57,'2021-04-13 12:32:37.633081',NULL,'Adobe Genuine Software Integrity Service',1,1,2,14,'Adobe Genuine Software Integrity Service'),(58,'2021-04-13 12:32:37.633081',NULL,'AdobeUpdateService',1,1,4,14,'AdobeUpdateService'),(59,'2021-04-13 13:01:38.538568',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(60,'2021-04-13 13:01:38.538568',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(61,'2021-04-13 13:01:38.538568',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(62,'2021-04-13 15:58:16.469808',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(63,'2021-04-13 15:58:16.469808',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(64,'2021-04-13 15:58:16.469808',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(65,'2021-04-13 15:58:25.578141',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(66,'2021-04-13 15:58:25.578141',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(67,'2021-04-13 15:58:25.578141',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(68,'2021-04-13 15:59:01.979374',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(69,'2021-04-13 15:59:01.979374',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(70,'2021-04-13 15:59:01.979374',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(71,'2021-04-13 16:00:05.892493',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(72,'2021-04-13 16:00:05.892493',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(73,'2021-04-13 16:00:05.892493',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(74,'2021-04-13 16:00:20.918120',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(75,'2021-04-13 16:00:20.918120',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(76,'2021-04-13 16:00:20.918120',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(77,'2021-04-13 16:02:36.474883',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(78,'2021-04-13 16:02:36.474883',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(79,'2021-04-13 16:02:36.474883',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(80,'2021-04-13 16:03:24.012160',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(81,'2021-04-13 16:03:24.012160',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(82,'2021-04-13 16:03:24.012160',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(83,'2021-04-13 16:03:44.400709',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(84,'2021-04-13 16:03:44.400709',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(85,'2021-04-13 16:03:44.400709',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(86,'2021-04-13 16:05:29.027978',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(87,'2021-04-13 16:05:29.027978',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(88,'2021-04-13 16:05:29.027978',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(89,'2021-04-13 16:12:02.026025',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(90,'2021-04-13 16:12:02.026025',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(91,'2021-04-13 16:12:02.026025',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(92,'2021-04-13 16:14:33.772937',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(93,'2021-04-13 16:14:33.772937',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(94,'2021-04-13 16:14:33.772937',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(95,'2021-04-16 09:25:33.037965',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(96,'2021-04-16 09:25:33.037965',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(97,'2021-04-16 09:25:33.037965',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(98,'2021-04-16 09:26:40.093312',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(99,'2021-04-16 09:26:40.093312',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(100,'2021-04-16 09:26:40.093312',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(101,'2021-04-16 09:35:56.780395',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,1,'ActiveX Installer (AxInstSV)A'),(102,'2021-04-16 09:35:56.780395',NULL,'Adobe Genuine Software Integrity Service',1,1,2,1,'Adobe Genuine Software Integrity Service'),(103,'2021-04-16 09:35:56.780395',NULL,'AdobeUpdateService',1,1,4,1,'AdobeUpdateService'),(104,'2021-04-20 10:04:14.615092',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,16,'ActiveX Installer (AxInstSV)A'),(105,'2021-04-20 10:04:14.615092',NULL,'Adobe Genuine Software Integrity Service',1,1,2,16,'Adobe Genuine Software Integrity Service'),(106,'2021-04-20 10:04:14.615092',NULL,'AdobeUpdateService',1,1,4,16,'AdobeUpdateService'),(107,'2021-04-20 14:21:18.593214',NULL,'ActiveX Installer (AxInstSV)A',1,1,1,17,'ActiveX Installer (AxInstSV)A'),(108,'2021-04-20 14:21:18.593214',NULL,'Adobe Genuine Software Integrity Service',1,1,2,17,'Adobe Genuine Software Integrity Service'),(109,'2021-04-20 14:21:18.593214',NULL,'AdobeUpdateService',1,1,4,17,'AdobeUpdateService');
/*!40000 ALTER TABLE `conf_windows_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `config` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tempo` int NOT NULL,
  `id_client` int DEFAULT NULL,
  `id_operazione` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8j4kjhx1h1yvrbt9b6jtpb3v0` (`id_client`),
  KEY `FKqgiq5kj4lb986182etqdvfx4w` (`id_operazione`),
  CONSTRAINT `FK8j4kjhx1h1yvrbt9b6jtpb3v0` FOREIGN KEY (`id_client`) REFERENCES `elenco_clients` (`id`),
  CONSTRAINT `FKqgiq5kj4lb986182etqdvfx4w` FOREIGN KEY (`id_operazione`) REFERENCES `elenco_operazioni` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elenco_clients`
--

DROP TABLE IF EXISTS `elenco_clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elenco_clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mac_address` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sede` int DEFAULT NULL,
  `id_company` int DEFAULT NULL,
  `tipologia_client` int DEFAULT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `licenza_in_uso` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`,`mac_address`),
  KEY `FKqsy0knx8e74cg6ffk7ia8iqae` (`id_company`),
  KEY `FKqnlbboj7jikkkat67dy4cw0dk` (`tipologia_client`),
  KEY `fk_sedi_idx` (`sede`),
  CONSTRAINT `fk_sedi` FOREIGN KEY (`sede`) REFERENCES `sedi` (`id`),
  CONSTRAINT `FKqnlbboj7jikkkat67dy4cw0dk` FOREIGN KEY (`tipologia_client`) REFERENCES `tipologia_client` (`id`),
  CONSTRAINT `FKqsy0knx8e74cg6ffk7ia8iqae` FOREIGN KEY (`id_company`) REFERENCES `elenco_companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elenco_clients`
--

LOCK TABLES `elenco_clients` WRITE;
/*!40000 ALTER TABLE `elenco_clients` DISABLE KEYS */;
INSERT INTO `elenco_clients` VALUES (1,'00:1B:44:11:3A:B7','DESKTOP-3874',4,1,1,'DESKTOP-3874',NULL),(2,'00:1B:94:51:4N:B0','DESKTOP-2049',3,1,1,'DESKTOP-2049',NULL),(3,'00:09:20:39:4M:J0','SERVER-2209',3,1,2,'SERVER-2209',NULL),(4,'00:1B:44:11:3A:B1','DESKTOP-000',2,1,1,'DESKTOP-000',NULL),(5,'00:1C:33:11:1P:B1','DESKTOP-002',2,1,1,'DESKTOP-002',NULL),(6,'00:1C:33:11:1P:A9','DESKTOP-003',2,1,1,'DESKTOP-003',NULL),(7,'00:1C:44:11:1P:S1','DESKTOP-004',2,1,1,'DESKTOP-004',NULL),(8,'00:4P:44:11:1P:S1','DESKTOP-005',2,1,2,'DESKTOP-005',NULL),(9,'00:4P:44:31:AA:S1','DESKTOP-006',2,1,2,'DESKTOP-006',NULL),(10,'00:4P:44:31:BB:S1','DESKTOP-007',2,1,2,'DESKTOP-007',NULL),(11,'00:4P:44:31:CC:S1','DESKTOP-008',2,1,1,'DESKTOP-008',NULL),(13,'00:4P:44:31:SC:S1','DESKTOP-009',5,1,1,'DESKTOP-009',NULL),(14,'00:4P:24:31:SC:S1','DESKTOP-009',4,2,1,'DESKTOP-009',NULL),(15,'00:4P:24:31:SA:S1','DESKTOP-111',0,3,1,'DESKTOP-111',NULL),(16,'00:4P:24:31:QA:S1','DESKTOP-111',19,4,1,'DESKTOP-111',NULL),(17,'00:4P:11:31:QA:S1','DESKTOP-222',17,4,1,'DESKTOP-222',NULL),(18,'00:SO:11:31:QA:S1','DESKTOP-333',17,4,2,'DESKTOP-333',NULL),(19,'00:SO:11:31:SA:S1','DESKTOP-343',19,4,2,'DESKTOP-343',NULL),(20,'00:4P:24:31:ZP:S1','DESKTOP-123',NULL,6,1,'DESKTOP-123',NULL),(21,'00:0Q:24:31:ZP:S1','DESKTOP-666',NULL,7,1,'DESKTOP-666',NULL);
/*!40000 ALTER TABLE `elenco_clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elenco_clients_elenco_licenze`
--

DROP TABLE IF EXISTS `elenco_clients_elenco_licenze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elenco_clients_elenco_licenze` (
  `licenza_in_uso` int NOT NULL,
  `id` int NOT NULL,
  KEY `FKeirmks1ey8igkqvsptltrgia2` (`id`),
  KEY `FKat02ecko02jonr3e6pkqxbx2x` (`licenza_in_uso`),
  CONSTRAINT `FKat02ecko02jonr3e6pkqxbx2x` FOREIGN KEY (`licenza_in_uso`) REFERENCES `elenco_clients` (`id`),
  CONSTRAINT `FKeirmks1ey8igkqvsptltrgia2` FOREIGN KEY (`id`) REFERENCES `elenco_licenze` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elenco_clients_elenco_licenze`
--

LOCK TABLES `elenco_clients_elenco_licenze` WRITE;
/*!40000 ALTER TABLE `elenco_clients_elenco_licenze` DISABLE KEYS */;
INSERT INTO `elenco_clients_elenco_licenze` VALUES (1,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(13,1),(14,2),(15,3),(16,4),(17,4),(18,4),(19,4),(20,6),(21,7);
/*!40000 ALTER TABLE `elenco_clients_elenco_licenze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elenco_companies`
--

DROP TABLE IF EXISTS `elenco_companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elenco_companies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `email_alert` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `ragione_sociale` varchar(255) DEFAULT NULL,
  `chiave_di_registrazione` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elenco_companies`
--

LOCK TABLES `elenco_companies` WRITE;
/*!40000 ALTER TABLE `elenco_companies` DISABLE KEYS */;
INSERT INTO `elenco_companies` VALUES (1,'email@itcubeconsulting.it','alert@itcubeconsulting.it','5f4dcc3b5aa765d61d8327deb882cf99','ITCUBE','AAA2-KMZL-6ZK6-SP5C'),(2,'aa@itcubeconsulting.it','alert@itcubeconsulting.it','5f4dcc3b5aa765d61d8327deb882cf99','ITCubalibre Consulting','BBB2-KMZL-6ZK6-PO9A'),(3,'jack@itcubeconsulting.it','alert@itcubeconsulting.it','5f4dcc3b5aa765d61d8327deb882cf99','ITCube prova','ULD2-KMZL-6ZK6-HR1L'),(4,'giacomo@mail.it','giacomo@alert.it','5f4dcc3b5aa765d61d8327deb882cf99','ITGiacomo3','QK61-5F5J-V2TJ-V4C5'),(5,'giacomo2@mail.it','giacomo@alert.it','5f4dcc3b5aa765d61d8327deb882cf99','ITGiacomo','A36K-DRP4-2LZN-QVC5'),(6,'jack@mail.it','giacomo@alert.it','5f4dcc3b5aa765d61d8327deb882cf99','ITJack','SH4H-K7K5-Q84E-U1P5'),(7,'jack2@mail.it','giacomo@alert.it','5f4dcc3b5aa765d61d8327deb882cf99','ITJack2','5LHF-Z75L-6SGL-RMJL');
/*!40000 ALTER TABLE `elenco_companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elenco_licenze`
--

DROP TABLE IF EXISTS `elenco_licenze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elenco_licenze` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codice` varchar(255) DEFAULT NULL,
  `scadenza` datetime(6) DEFAULT NULL,
  `acquistato_da` int DEFAULT NULL,
  `id_tipo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3krlis5duo9x7953t77ncn8h1` (`acquistato_da`),
  KEY `FKsjuw7sojqvfxwha7chpnva5vm` (`id_tipo`),
  CONSTRAINT `FK3krlis5duo9x7953t77ncn8h1` FOREIGN KEY (`acquistato_da`) REFERENCES `elenco_companies` (`id`),
  CONSTRAINT `FKsjuw7sojqvfxwha7chpnva5vm` FOREIGN KEY (`id_tipo`) REFERENCES `tipologie_licenze` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elenco_licenze`
--

LOCK TABLES `elenco_licenze` WRITE;
/*!40000 ALTER TABLE `elenco_licenze` DISABLE KEYS */;
INSERT INTO `elenco_licenze` VALUES (1,'T896-ZJ7Q-E5KP-NKQN','2021-04-09 19:02:39.000000',1,1),(2,'H22R-GZP5-TN2M-NMA5','2021-04-13 10:19:26.764000',2,1),(3,'ULD2-KMZL-6ZK6-HR1L','2021-04-14 10:36:16.653000',3,1),(4,'9C32-F6EH-M3AS-BB8H','2021-10-19 09:32:32.932000',4,1),(5,'9VLG-PVL7-LL9P-TC6N','2021-10-20 12:32:54.440000',5,1),(6,'L3CU-EKQK-6BD7-ZSN5','2021-10-21 11:20:48.596000',6,1),(7,'UZ3Z-A6H6-QV47-5DFM','2021-10-21 11:40:40.117000',7,1);
/*!40000 ALTER TABLE `elenco_licenze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elenco_operazioni`
--

DROP TABLE IF EXISTS `elenco_operazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elenco_operazioni` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `stato` bit(1) NOT NULL,
  `tabella_riferimento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elenco_operazioni`
--

LOCK TABLES `elenco_operazioni` WRITE;
/*!40000 ALTER TABLE `elenco_operazioni` DISABLE KEYS */;
/*!40000 ALTER TABLE `elenco_operazioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elenco_operazioni_tipologie_licenze`
--

DROP TABLE IF EXISTS `elenco_operazioni_tipologie_licenze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elenco_operazioni_tipologie_licenze` (
  `licenza_riferimento` int NOT NULL,
  `classe` int NOT NULL,
  KEY `FKlmdm9vojwtqko36rp5jxvp7mu` (`classe`),
  KEY `FKmfp6ymqxcmbwyqgu7xoo9v0ef` (`licenza_riferimento`),
  CONSTRAINT `FKlmdm9vojwtqko36rp5jxvp7mu` FOREIGN KEY (`classe`) REFERENCES `elenco_operazioni` (`id`),
  CONSTRAINT `FKmfp6ymqxcmbwyqgu7xoo9v0ef` FOREIGN KEY (`licenza_riferimento`) REFERENCES `tipologie_licenze` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elenco_operazioni_tipologie_licenze`
--

LOCK TABLES `elenco_operazioni_tipologie_licenze` WRITE;
/*!40000 ALTER TABLE `elenco_operazioni_tipologie_licenze` DISABLE KEYS */;
/*!40000 ALTER TABLE `elenco_operazioni_tipologie_licenze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `macro_categorie_eventi`
--

DROP TABLE IF EXISTS `macro_categorie_eventi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `macro_categorie_eventi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome_categoria` varchar(255) DEFAULT NULL,
  `classe` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `macro_categorie_eventi`
--

LOCK TABLES `macro_categorie_eventi` WRITE;
/*!40000 ALTER TABLE `macro_categorie_eventi` DISABLE KEYS */;
/*!40000 ALTER TABLE `macro_categorie_eventi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monitoraggio`
--

DROP TABLE IF EXISTS `monitoraggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monitoraggio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `monitora` bit(1) NOT NULL,
  `nome_servizio` varchar(255) DEFAULT NULL,
  `id_client` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monitoraggio`
--

LOCK TABLES `monitoraggio` WRITE;
/*!40000 ALTER TABLE `monitoraggio` DISABLE KEYS */;
INSERT INTO `monitoraggio` VALUES (2,_binary '','ActiveX Installer (AxInstSV)3',1),(3,_binary '','Adobe Genuine Software Integrity Service3',1),(4,_binary '','AdobeUpdateService3',1),(5,_binary '','ActiveX Installer (AxInstSV)',14),(6,_binary '','Adobe Genuine Software Integrity Service',14),(7,_binary '','AdobeUpdateService',14),(8,_binary '','ActiveX Installer (AxInstSV)A',14),(9,_binary '','ActiveX Installer (AxInstSV)A',1),(10,_binary '','Adobe Genuine Software Integrity Service',1),(11,_binary '','AdobeUpdateService',1),(12,_binary '','ActiveX Installer (AxInstSV)A',16),(13,_binary '','Adobe Genuine Software Integrity Service',16),(14,_binary '','AdobeUpdateService',16),(15,_binary '','ActiveX Installer (AxInstSV)A',17),(16,_binary '','Adobe Genuine Software Integrity Service',17),(17,_binary '','AdobeUpdateService',17);
/*!40000 ALTER TABLE `monitoraggio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `real_time`
--

DROP TABLE IF EXISTS `real_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `real_time` (
  `id` int NOT NULL AUTO_INCREMENT,
  `operazione` varchar(255) DEFAULT NULL,
  `stato` int NOT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `real_time`
--

LOCK TABLES `real_time` WRITE;
/*!40000 ALTER TABLE `real_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `real_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sedi`
--

DROP TABLE IF EXISTS `sedi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sedi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_company` int NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbj8at8i4m45weh87qxr58qu6h` (`id_company`),
  CONSTRAINT `FKbj8at8i4m45weh87qxr58qu6h` FOREIGN KEY (`id_company`) REFERENCES `elenco_companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sedi`
--

LOCK TABLES `sedi` WRITE;
/*!40000 ALTER TABLE `sedi` DISABLE KEYS */;
INSERT INTO `sedi` VALUES (0,3,'Senza sede'),(1,1,'Milano'),(2,1,'Roma'),(3,1,'Torino'),(4,2,'Verona'),(5,1,'Senza sede'),(15,4,'Savona'),(17,4,'Palermo'),(19,4,'Roma'),(31,6,'Palermo'),(32,7,'Roma');
/*!40000 ALTER TABLE `sedi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipologia_client`
--

DROP TABLE IF EXISTS `tipologia_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipologia_client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome_tipologia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipologia_client`
--

LOCK TABLES `tipologia_client` WRITE;
/*!40000 ALTER TABLE `tipologia_client` DISABLE KEYS */;
INSERT INTO `tipologia_client` VALUES (1,'Client'),(2,'Server');
/*!40000 ALTER TABLE `tipologia_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipologie_licenze`
--

DROP TABLE IF EXISTS `tipologie_licenze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipologie_licenze` (
  `id` int NOT NULL AUTO_INCREMENT,
  `classe` int NOT NULL,
  `nome_tipologia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipologie_licenze`
--

LOCK TABLES `tipologie_licenze` WRITE;
/*!40000 ALTER TABLE `tipologie_licenze` DISABLE KEYS */;
INSERT INTO `tipologie_licenze` VALUES (1,1,'SISTEMA OPERATIVO'),(2,2,'BACKUP'),(3,3,'ANTIVIRUS'),(4,4,'RETE'),(5,5,'VULNERABILITA\'');
/*!40000 ALTER TABLE `tipologie_licenze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visualizzazione_eventi`
--

DROP TABLE IF EXISTS `visualizzazione_eventi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visualizzazione_eventi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_and_time` datetime(6) DEFAULT NULL,
  `id_event` int NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `level` int NOT NULL,
  `macro_categoria` int NOT NULL,
  `sottocategoria` varchar(25) NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `task_category` varchar(255) DEFAULT NULL,
  `id_client` int DEFAULT NULL,
  `date_and_time_evento` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visualizzazione_eventi`
--

LOCK TABLES `visualizzazione_eventi` WRITE;
/*!40000 ALTER TABLE `visualizzazione_eventi` DISABLE KEYS */;
INSERT INTO `visualizzazione_eventi` VALUES (1,'2021-04-09 18:57:04.000000',100,'Updating Adobe products',1,0,'A','source','category',1,'2021-04-09 18:57:04.000000'),(2,'2021-04-09 18:58:20.000000',100,'Updating Adobe products',1,1,'A','source','category',1,'2021-04-09 18:58:20.000000'),(4,'2021-04-11 10:16:48.000000',100,'Info test A',1,0,'A','Source A',NULL,1,'2021-04-11 18:57:00.000000'),(5,'2021-04-11 10:16:48.000000',100,'Info test H',1,0,'H','Source H',NULL,1,'2021-04-11 18:57:00.000000'),(6,'2021-04-11 10:16:48.000000',100,'Info test S',1,0,'S','Source S',NULL,1,'2021-04-11 18:57:00.000000'),(7,'2021-04-11 10:16:48.000000',100,'Info test C',1,0,'C','Source C',NULL,1,'2021-04-11 18:57:00.000000'),(8,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 18:57:00.000000'),(9,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 17:57:00.000000'),(10,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 16:57:00.000000'),(11,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 15:57:00.000000'),(12,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 15:30:00.000000'),(13,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 19:02:00.000000'),(14,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 10:57:00.000000'),(15,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 15:53:00.000000'),(16,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 12:12:00.000000'),(17,'2021-04-13 11:12:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 11:11:00.000000'),(18,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 09:22:00.000000'),(19,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 15:55:00.000000'),(20,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 20:57:00.000000'),(21,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 06:57:00.000000'),(22,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 11:21:00.000000'),(23,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 23:12:00.000000'),(24,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 08:12:00.000000'),(25,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 18:12:00.000000'),(26,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 11:52:00.000000'),(27,'2021-04-13 11:11:03.763081',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 07:29:00.000000'),(28,'2021-04-13 11:11:03.763081',100,'Info test H',1,0,'H','Source H',NULL,14,'2021-04-12 18:57:00.000000'),(29,'2021-04-13 11:11:03.763081',100,'Info test S',1,0,'S','Source S',NULL,14,'2021-04-12 18:57:00.000000'),(30,'2021-04-13 11:11:03.763081',100,'Info test C',1,0,'C','Source C',NULL,14,'2021-04-12 18:57:00.000000'),(31,'2021-04-13 11:11:03.763081',100,'Info test C',1,0,'C','Source C',NULL,14,'2021-04-12 10:00:00.000000'),(32,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 18:57:00.000000'),(33,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 17:57:00.000000'),(34,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 16:57:00.000000'),(35,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 15:57:00.000000'),(36,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 15:30:00.000000'),(37,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 19:02:00.000000'),(38,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 10:57:00.000000'),(39,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 15:53:00.000000'),(40,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 12:12:00.000000'),(41,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 11:11:00.000000'),(42,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 09:22:00.000000'),(43,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 15:55:00.000000'),(44,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 20:57:00.000000'),(45,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 06:57:00.000000'),(46,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 11:21:00.000000'),(47,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 23:12:00.000000'),(48,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 08:12:00.000000'),(49,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 18:12:00.000000'),(50,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 11:52:00.000000'),(51,'2021-04-20 14:19:04.317259',100,'Info test A',1,0,'A','Source A',NULL,14,'2021-04-12 07:29:00.000000'),(52,'2021-04-20 14:19:04.317259',100,'Info test H',1,0,'H','Source H',NULL,14,'2021-04-12 18:57:00.000000'),(53,'2021-04-20 14:19:04.317259',100,'Info test S',1,0,'S','Source S',NULL,14,'2021-04-12 18:57:00.000000'),(54,'2021-04-20 14:19:04.317259',100,'Info test C',1,0,'C','Source C',NULL,14,'2021-04-12 18:57:00.000000'),(55,'2021-04-20 14:19:04.317259',100,'Info test C',1,0,'C','Source C',NULL,14,'2021-04-12 10:00:00.000000'),(56,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 18:57:00.000000'),(57,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 17:57:00.000000'),(58,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 16:57:00.000000'),(59,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 15:57:00.000000'),(60,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 15:30:00.000000'),(61,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 19:02:00.000000'),(62,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 10:57:00.000000'),(63,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 15:53:00.000000'),(64,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 12:12:00.000000'),(65,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 11:11:00.000000'),(66,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 09:22:00.000000'),(67,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 15:55:00.000000'),(68,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 20:57:00.000000'),(69,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 06:57:00.000000'),(70,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 11:21:00.000000'),(71,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 23:12:00.000000'),(72,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 08:12:00.000000'),(73,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 18:12:00.000000'),(74,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 11:52:00.000000'),(75,'2021-04-20 14:20:29.902498',100,'Info test A',1,0,'A','Source A',NULL,17,'2021-04-12 07:29:00.000000'),(76,'2021-04-20 14:20:29.902498',100,'Info test H',1,0,'H','Source H',NULL,17,'2021-04-12 18:57:00.000000'),(77,'2021-04-20 14:20:29.902498',100,'Info test S',1,0,'S','Source S',NULL,17,'2021-04-12 18:57:00.000000'),(78,'2021-04-20 14:20:29.902498',100,'Info test C',1,0,'C','Source C',NULL,17,'2021-04-12 18:57:00.000000'),(79,'2021-04-20 14:20:29.902498',100,'Info test C',1,0,'C','Source C',NULL,17,'2021-04-12 10:00:00.000000');
/*!40000 ALTER TABLE `visualizzazione_eventi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-21 12:06:56
