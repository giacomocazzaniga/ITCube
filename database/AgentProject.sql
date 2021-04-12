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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conf_total_free_disc_space`
--

LOCK TABLES `conf_total_free_disc_space` WRITE;
/*!40000 ALTER TABLE `conf_total_free_disc_space` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conf_windows_services`
--

LOCK TABLES `conf_windows_services` WRITE;
/*!40000 ALTER TABLE `conf_windows_services` DISABLE KEYS */;
INSERT INTO `conf_windows_services` VALUES (1,'2021-04-09 18:56:07.000000','Adobe Update Service','Adobe Update Service',1,1,4,1,'Adobe Update Service');
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
  `sede` varchar(255) DEFAULT NULL,
  `id_company` int DEFAULT NULL,
  `tipologia_client` int DEFAULT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `licenza_in_uso` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`,`mac_address`),
  KEY `FKqsy0knx8e74cg6ffk7ia8iqae` (`id_company`),
  KEY `FKqnlbboj7jikkkat67dy4cw0dk` (`tipologia_client`),
  CONSTRAINT `FKqnlbboj7jikkkat67dy4cw0dk` FOREIGN KEY (`tipologia_client`) REFERENCES `tipologia_client` (`id`),
  CONSTRAINT `FKqsy0knx8e74cg6ffk7ia8iqae` FOREIGN KEY (`id_company`) REFERENCES `elenco_companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elenco_clients`
--

LOCK TABLES `elenco_clients` WRITE;
/*!40000 ALTER TABLE `elenco_clients` DISABLE KEYS */;
INSERT INTO `elenco_clients` VALUES (1,'00:1B:44:11:3A:B7','DESKTOP-3874','Roma',1,1,'DESKTOP-3874',1),(2,'00:1B:94:51:4N:B0','DESKTOP-2049','Torino',1,1,'DESKTOP-2049',1),(3,'00:09:20:39:4M:J0','SERVER-2209','Bologna',1,2,'SERVER-2209',1),(4,'00:1B:44:11:3A:B1','DESKTOP-000','Milano',1,1,'DESKTOP-000',NULL),(5,'00:1C:33:11:1P:B1','DESKTOP-002','Milano',1,1,'DESKTOP-002',NULL),(6,'00:1C:33:11:1P:A9','DESKTOP-003','Milano',1,1,'DESKTOP-003',NULL),(7,'00:1C:44:11:1P:S1','DESKTOP-004','Milano',1,1,'DESKTOP-004',NULL),(8,'00:4P:44:11:1P:S1','DESKTOP-005','Milano',1,2,'DESKTOP-005',NULL),(9,'00:4P:44:31:AA:S1','DESKTOP-006','Milano',1,2,'DESKTOP-006',NULL),(10,'00:4P:44:31:BB:S1','DESKTOP-007','Milano',1,2,'DESKTOP-007',NULL),(11,'00:4P:44:31:CC:S1','DESKTOP-008','Milano',1,1,'DESKTOP-008',NULL);
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
INSERT INTO `elenco_clients_elenco_licenze` VALUES (1,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elenco_companies`
--

LOCK TABLES `elenco_companies` WRITE;
/*!40000 ALTER TABLE `elenco_companies` DISABLE KEYS */;
INSERT INTO `elenco_companies` VALUES (1,'email@itcubeconsulting.it','alert@itcubeconsulting.it','5f4dcc3b5aa765d61d8327deb882cf99','ITCUBE');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elenco_licenze`
--

LOCK TABLES `elenco_licenze` WRITE;
/*!40000 ALTER TABLE `elenco_licenze` DISABLE KEYS */;
INSERT INTO `elenco_licenze` VALUES (1,'T896-ZJ7Q-E5KP-NKQN','2021-04-09 19:02:39.000000',1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monitoraggio`
--

LOCK TABLES `monitoraggio` WRITE;
/*!40000 ALTER TABLE `monitoraggio` DISABLE KEYS */;
INSERT INTO `monitoraggio` VALUES (1,_binary '','Adobe Update Service',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visualizzazione_eventi`
--

LOCK TABLES `visualizzazione_eventi` WRITE;
/*!40000 ALTER TABLE `visualizzazione_eventi` DISABLE KEYS */;
INSERT INTO `visualizzazione_eventi` VALUES (1,'2021-04-09 18:57:04.000000',100,'Updating Adobe products',1,0,'A','source','category',1,'2021-04-09 18:57:04.000000'),(2,'2021-04-09 18:58:20.000000',100,'Updating Adobe products',1,1,'A','source','category',1,'2021-04-09 18:58:20.000000'),(4,'2021-04-11 10:16:48.000000',100,'Info test A',1,0,'A','Source A',NULL,1,'2021-04-11 18:57:00.000000'),(5,'2021-04-11 10:16:48.000000',100,'Info test H',1,0,'H','Source H',NULL,1,'2021-04-11 18:57:00.000000'),(6,'2021-04-11 10:16:48.000000',100,'Info test S',1,0,'S','Source S',NULL,1,'2021-04-11 18:57:00.000000'),(7,'2021-04-11 10:16:48.000000',100,'Info test C',1,0,'C','Source C',NULL,1,'2021-04-11 18:57:00.000000');
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

-- Dump completed on 2021-04-12 12:09:09
