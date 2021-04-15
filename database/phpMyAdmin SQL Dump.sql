-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 15, 2021 at 03:06 PM
-- Server version: 5.7.32
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `agentWindows`
--

-- --------------------------------------------------------

--
-- Table structure for table `config`
--

CREATE TABLE `config` (
  `id` int(11) NOT NULL,
  `tempo` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `id_operazione` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `conf_total_free_disc_space`
--

CREATE TABLE `conf_total_free_disc_space` (
  `id` int(11) NOT NULL,
  `date_and_time` datetime(6) DEFAULT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `drive` varchar(255) DEFAULT NULL,
  `id_client` int(11) NOT NULL,
  `perc_free_disc_space` double NOT NULL,
  `total_free_disc_space` bigint(20) NOT NULL,
  `total_size` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `conf_total_free_disc_space`
--

INSERT INTO `conf_total_free_disc_space` (`id`, `date_and_time`, `descrizione`, `drive`, `id_client`, `perc_free_disc_space`, `total_free_disc_space`, `total_size`) VALUES
(1, '2021-04-12 14:13:27.000000', 'C', 'C', 1, 10, 1000, 10000),
(3, '2021-04-13 09:17:43.000000', 'C', 'C', 13, 1, 19000000000, 30000000000),
(4, '2021-04-13 12:10:59.000000', 'D', 'D', 1, 50, 5000, 10000),
(5, '2021-04-13 12:12:20.000000', 'C', 'C', 2, 10, 1000, 10000),
(6, '2021-04-13 12:12:20.000000', 'D', 'D', 2, 50, 5000, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `conf_windows_services`
--

CREATE TABLE `conf_windows_services` (
  `id` int(11) NOT NULL,
  `date_and_time` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `service_type` varchar(11) NOT NULL,
  `start_type` int(11) NOT NULL,
  `stato` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `nome_servizio` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `conf_windows_services`
--

INSERT INTO `conf_windows_services` (`id`, `date_and_time`, `description`, `display_name`, `service_type`, `start_type`, `stato`, `id_client`, `nome_servizio`) VALUES
(1, '2021-04-09 18:56:07.000000', 'Adobe Update Service', 'Adobe Update Service', '1', 1, 4, 1, 'Adobe Update Service'),
(2, '2021-04-12 14:13:27.000000', NULL, 'ActiveX Installer (AxInstSV)2', '1', 1, 1, 1, 'ActiveX Installer (AxInstSV)2'),
(3, '2021-04-12 14:13:27.000000', NULL, 'Adobe Genuine Software Integrity Service2', '1', 1, 1, 1, 'Adobe Genuine Software Integrity Service2'),
(4, '2021-04-12 14:13:27.000000', NULL, 'AdobeUpdateService2', '1', 1, 1, 1, 'AdobeUpdateService2'),
(9, '2021-04-13 10:04:33.000000', NULL, 'AdobeUpdateService', '1', 1, 4, 14, 'AdobeUpdateService'),
(10, '2021-04-13 10:04:58.000000', NULL, 'AdobeUpdateService', '1', 1, 1, 14, 'AdobeUpdateService'),
(11, '2021-04-13 12:10:59.000000', NULL, 'AdobeUpdateService', '1', 1, 4, 1, 'AdobeUpdateService'),
(12, '2021-04-13 12:11:33.000000', NULL, 'AdobeUpdateService', '1', 1, 1, 1, 'AdobeUpdateService'),
(13, '2021-04-13 12:12:20.000000', NULL, 'AdobeUpdateService', '1', 1, 1, 2, 'AdobeUpdateService'),
(14, '2021-04-13 12:12:46.000000', NULL, 'AdobeUpdateService', '1', 1, 4, 1, 'AdobeUpdateService');

-- --------------------------------------------------------

--
-- Table structure for table `elenco_clients`
--

CREATE TABLE `elenco_clients` (
  `id` int(11) NOT NULL,
  `mac_address` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sede` varchar(255) DEFAULT NULL,
  `id_company` int(11) DEFAULT NULL,
  `tipologia_client` int(11) DEFAULT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `licenza_in_uso` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `elenco_clients`
--

INSERT INTO `elenco_clients` (`id`, `mac_address`, `nome`, `sede`, `id_company`, `tipologia_client`, `descrizione`, `licenza_in_uso`) VALUES
(1, '00:1B:44:11:3A:B7', 'DESKTOP-3874', 'Vicenza', 1, 1, 'DESKTOP-3874', 1),
(2, '00:1B:94:51:4N:B0', 'DESKTOP-2049', 'Roma', 1, 1, 'DESKTOP-2049', 1),
(3, '00:09:20:39:4M:J0', 'SERVER-2209', 'Bologna', 1, 2, 'SERVER-2209', 1),
(4, '00:1B:44:11:3A:B1', 'DESKTOP-000', 'Roma', 1, 1, 'DESKTOP-000', NULL),
(5, '00:1C:33:11:1P:B1', 'DESKTOP-002', 'Roma', 1, 1, 'DESKTOP-002', NULL),
(6, '00:1C:33:11:1P:A9', 'DESKTOP-003', 'Roma', 1, 1, 'DESKTOP-003', NULL),
(7, '00:1C:44:11:1P:S1', 'DESKTOP-004', 'Roma', 1, 1, 'DESKTOP-004', NULL),
(8, '00:4P:44:11:1P:S1', 'DESKTOP-005', 'Roma', 1, 2, 'DESKTOP-005', NULL),
(9, '00:4P:44:31:AA:S1', 'DESKTOP-006', 'Roma', 1, 2, 'DESKTOP-006', NULL),
(10, '00:4P:44:31:BB:S1', 'DESKTOP-007', 'Roma', 1, 2, 'DESKTOP-007', NULL),
(11, '00:4P:44:31:CC:S1', 'DESKTOP-008', 'Roma', 1, 1, 'DESKTOP-008', NULL),
(12, '00:4P:44:31:SC:S1', 'DESKTOP-009', 'Napoli', 1, 1, 'DESKTOP-009', NULL),
(13, '99:1B:98:11:3A:B7', 'DESKTOP-010', 'Senza sede', 1, 1, 'DESKTOP-010', NULL),
(14, '99:1B:98:11:3A:B7', 'DESKTOP-001', 'Vicenza', 2, 1, 'DESKTOP-001', NULL),
(15, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 'Senza sede', 1, 1, 'Prova', NULL),
(17, '12-34-56', 'Nuovo Client', 'Senza sede', 3, 1, 'Prova', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `elenco_clients_elenco_licenze`
--

CREATE TABLE `elenco_clients_elenco_licenze` (
  `licenza_in_uso` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `elenco_clients_elenco_licenze`
--

INSERT INTO `elenco_clients_elenco_licenze` (`licenza_in_uso`, `id`) VALUES
(1, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(1, 2),
(12, 1),
(13, 1),
(14, 3),
(15, 1),
(17, 4);

-- --------------------------------------------------------

--
-- Table structure for table `elenco_companies`
--

CREATE TABLE `elenco_companies` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_alert` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `ragione_sociale` varchar(255) DEFAULT NULL,
  `chiave_di_registrazione` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `elenco_companies`
--

INSERT INTO `elenco_companies` (`id`, `email`, `email_alert`, `password`, `ragione_sociale`, `chiave_di_registrazione`) VALUES
(1, 'email@itcubeconsulting.it', 'alert@itcubeconsulting.it', 'password', 'ITCUBE', NULL),
(2, 'email2@itcubeconsulting.it', 'asdjkb@wivub.com', '5f4dcc3b5aa765d61d8327deb882cf99', 'prova 1', NULL),
(3, 'a@prova.it', 'alert@a.it', 'password', 'a', 'LLT3-CR55-3NFR-NJQ5');

-- --------------------------------------------------------

--
-- Table structure for table `elenco_licenze`
--

CREATE TABLE `elenco_licenze` (
  `id` int(11) NOT NULL,
  `codice` varchar(255) DEFAULT NULL,
  `scadenza` datetime(6) DEFAULT NULL,
  `acquistato_da` int(11) DEFAULT NULL,
  `id_tipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `elenco_licenze`
--

INSERT INTO `elenco_licenze` (`id`, `codice`, `scadenza`, `acquistato_da`, `id_tipo`) VALUES
(1, 'T896-ZJ7Q-E5KP-NKQN', '2021-04-09 19:02:39.000000', 1, 1),
(2, '45V8-HD4M-KL23-33NB', '2022-01-20 14:37:35.000000', 1, 3),
(3, '9BR9-FGDK-91RS-DFU5', '2021-04-13 09:59:27.000000', 2, 1),
(4, 'CDUL-A3MH-3ELC-CDLG', '2021-04-15 16:30:47.706000', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `elenco_operazioni`
--

CREATE TABLE `elenco_operazioni` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `stato` bit(1) NOT NULL,
  `tabella_riferimento` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `elenco_operazioni_tipologie_licenze`
--

CREATE TABLE `elenco_operazioni_tipologie_licenze` (
  `licenza_riferimento` int(11) NOT NULL,
  `classe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `macro_categorie_eventi`
--

CREATE TABLE `macro_categorie_eventi` (
  `id` int(11) NOT NULL,
  `nome_categoria` varchar(255) DEFAULT NULL,
  `classe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `monitoraggio`
--

CREATE TABLE `monitoraggio` (
  `id` int(11) NOT NULL,
  `monitora` bit(1) NOT NULL,
  `nome_servizio` varchar(255) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `monitoraggio`
--

INSERT INTO `monitoraggio` (`id`, `monitora`, `nome_servizio`, `id_client`) VALUES
(1, b'0', 'Adobe Update Service', 1),
(2, b'0', 'ActiveX Installer (AxInstSV)2', 1),
(3, b'1', 'Adobe Genuine Software Integrity Service2', 1),
(4, b'1', 'AdobeUpdateService2', 1),
(7, b'0', 'AdobeUpdateService', 14),
(8, b'0', 'AdobeUpdateService', 1),
(9, b'1', 'AdobeUpdateService', 2);

-- --------------------------------------------------------

--
-- Table structure for table `real_time`
--

CREATE TABLE `real_time` (
  `id` int(11) NOT NULL,
  `operazione` varchar(255) DEFAULT NULL,
  `stato` int(11) NOT NULL,
  `timestamp` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `sedi`
--

CREATE TABLE `sedi` (
  `id` int(11) NOT NULL,
  `id_company` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sedi`
--

INSERT INTO `sedi` (`id`, `id_company`, `nome`) VALUES
(5, 1, 'Milano'),
(4, 1, 'Monza');

-- --------------------------------------------------------

--
-- Table structure for table `tipologia_client`
--

CREATE TABLE `tipologia_client` (
  `id` int(11) NOT NULL,
  `nome_tipologia` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tipologia_client`
--

INSERT INTO `tipologia_client` (`id`, `nome_tipologia`) VALUES
(1, 'Client'),
(2, 'Server');

-- --------------------------------------------------------

--
-- Table structure for table `tipologie_licenze`
--

CREATE TABLE `tipologie_licenze` (
  `id` int(11) NOT NULL,
  `classe` int(11) NOT NULL,
  `nome_tipologia` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tipologie_licenze`
--

INSERT INTO `tipologie_licenze` (`id`, `classe`, `nome_tipologia`) VALUES
(1, 1, 'SISTEMA OPERATIVO'),
(2, 2, 'BACKUP'),
(3, 3, 'ANTIVIRUS'),
(4, 4, 'RETE'),
(5, 5, 'VULNERABILITA\'');

-- --------------------------------------------------------

--
-- Table structure for table `visualizzazione_eventi`
--

CREATE TABLE `visualizzazione_eventi` (
  `id` int(11) NOT NULL,
  `date_and_time` datetime(6) DEFAULT NULL,
  `id_event` int(11) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `macro_categoria` int(11) NOT NULL,
  `sottocategoria` varchar(25) NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `task_category` varchar(255) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `date_and_time_evento` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `visualizzazione_eventi`
--

INSERT INTO `visualizzazione_eventi` (`id`, `date_and_time`, `id_event`, `info`, `level`, `macro_categoria`, `sottocategoria`, `source`, `task_category`, `id_client`, `date_and_time_evento`) VALUES
(1, '2021-04-12 18:57:04.000000', 100, 'Updating Adobe products', 1, 0, 'A', 'source', 'category', 1, '2021-04-09 18:57:04.000000'),
(2, '2021-04-09 18:58:20.000000', 100, 'Updating Adobe products', 4, 1, 'A', 'source', 'category', 1, '2021-04-09 18:58:20.000000'),
(4, '2021-04-11 10:16:48.000000', 100, 'Info test A', 1, 0, 'A', 'Source A', NULL, 1, '2021-04-11 18:57:00.000000'),
(5, '2021-04-11 10:16:48.000000', 100, 'Info test H', 1, 0, 'H', 'Source H', NULL, 1, '2021-04-11 18:57:00.000000'),
(6, '2021-04-11 10:16:48.000000', 100, 'Info test S', 1, 0, 'S', 'Source S', NULL, 1, '2021-04-11 18:57:00.000000'),
(7, '2021-04-11 10:16:48.000000', 100, 'Info test C', 1, 0, 'C', 'Source C', NULL, 1, '2021-04-11 18:57:00.000000'),
(8, '2021-04-13 12:14:25.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(9, '2021-04-13 12:14:26.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(10, '2021-04-13 12:14:27.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(11, '2021-04-13 12:14:28.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(12, '2021-04-13 12:14:28.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(13, '2021-04-13 12:14:29.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(14, '2021-04-13 12:14:30.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(15, '2021-04-13 12:14:30.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(16, '2021-04-13 12:14:31.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(17, '2021-04-13 12:14:31.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(18, '2021-04-13 12:14:31.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(19, '2021-04-13 12:14:32.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(20, '2021-04-13 12:14:32.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(21, '2021-04-13 12:14:33.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(22, '2021-04-13 12:14:33.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(23, '2021-04-13 12:14:34.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(24, '2021-04-13 12:14:34.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(25, '2021-04-13 12:14:35.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(26, '2021-04-13 12:14:35.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(27, '2021-04-13 12:14:36.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(28, '2021-04-13 12:14:36.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000'),
(29, '2021-04-13 12:14:37.000000', 100, NULL, 1, 0, 'C', NULL, NULL, 1, '2021-04-11 20:57:00.000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `config`
--
ALTER TABLE `config`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8j4kjhx1h1yvrbt9b6jtpb3v0` (`id_client`),
  ADD KEY `FKqgiq5kj4lb986182etqdvfx4w` (`id_operazione`);

--
-- Indexes for table `conf_total_free_disc_space`
--
ALTER TABLE `conf_total_free_disc_space`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `conf_windows_services`
--
ALTER TABLE `conf_windows_services`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmqd53i0wh0trbaw0cs4j9nbmd` (`id_client`),
  ADD KEY `FKgweg5amjhfcpfifx1e0kx7olq` (`nome_servizio`);

--
-- Indexes for table `elenco_clients`
--
ALTER TABLE `elenco_clients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nome` (`nome`,`mac_address`),
  ADD KEY `FKqsy0knx8e74cg6ffk7ia8iqae` (`id_company`),
  ADD KEY `FKqnlbboj7jikkkat67dy4cw0dk` (`tipologia_client`);

--
-- Indexes for table `elenco_clients_elenco_licenze`
--
ALTER TABLE `elenco_clients_elenco_licenze`
  ADD KEY `FKeirmks1ey8igkqvsptltrgia2` (`id`),
  ADD KEY `FKat02ecko02jonr3e6pkqxbx2x` (`licenza_in_uso`);

--
-- Indexes for table `elenco_companies`
--
ALTER TABLE `elenco_companies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `elenco_licenze`
--
ALTER TABLE `elenco_licenze`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3krlis5duo9x7953t77ncn8h1` (`acquistato_da`),
  ADD KEY `FKsjuw7sojqvfxwha7chpnva5vm` (`id_tipo`);

--
-- Indexes for table `elenco_operazioni`
--
ALTER TABLE `elenco_operazioni`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `elenco_operazioni_tipologie_licenze`
--
ALTER TABLE `elenco_operazioni_tipologie_licenze`
  ADD KEY `FKlmdm9vojwtqko36rp5jxvp7mu` (`classe`),
  ADD KEY `FKmfp6ymqxcmbwyqgu7xoo9v0ef` (`licenza_riferimento`);

--
-- Indexes for table `macro_categorie_eventi`
--
ALTER TABLE `macro_categorie_eventi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `monitoraggio`
--
ALTER TABLE `monitoraggio`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `real_time`
--
ALTER TABLE `real_time`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sedi`
--
ALTER TABLE `sedi`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_company` (`id_company`,`nome`);

--
-- Indexes for table `tipologia_client`
--
ALTER TABLE `tipologia_client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tipologie_licenze`
--
ALTER TABLE `tipologie_licenze`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `visualizzazione_eventi`
--
ALTER TABLE `visualizzazione_eventi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `config`
--
ALTER TABLE `config`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `conf_total_free_disc_space`
--
ALTER TABLE `conf_total_free_disc_space`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `conf_windows_services`
--
ALTER TABLE `conf_windows_services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `elenco_clients`
--
ALTER TABLE `elenco_clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `elenco_companies`
--
ALTER TABLE `elenco_companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `elenco_licenze`
--
ALTER TABLE `elenco_licenze`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `elenco_operazioni`
--
ALTER TABLE `elenco_operazioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `macro_categorie_eventi`
--
ALTER TABLE `macro_categorie_eventi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `monitoraggio`
--
ALTER TABLE `monitoraggio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `real_time`
--
ALTER TABLE `real_time`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sedi`
--
ALTER TABLE `sedi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tipologia_client`
--
ALTER TABLE `tipologia_client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tipologie_licenze`
--
ALTER TABLE `tipologie_licenze`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `visualizzazione_eventi`
--
ALTER TABLE `visualizzazione_eventi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `config`
--
ALTER TABLE `config`
  ADD CONSTRAINT `FK8j4kjhx1h1yvrbt9b6jtpb3v0` FOREIGN KEY (`id_client`) REFERENCES `elenco_clients` (`id`),
  ADD CONSTRAINT `FKqgiq5kj4lb986182etqdvfx4w` FOREIGN KEY (`id_operazione`) REFERENCES `elenco_operazioni` (`id`);

--
-- Constraints for table `elenco_clients`
--
ALTER TABLE `elenco_clients`
  ADD CONSTRAINT `FKqnlbboj7jikkkat67dy4cw0dk` FOREIGN KEY (`tipologia_client`) REFERENCES `tipologia_client` (`id`),
  ADD CONSTRAINT `FKqsy0knx8e74cg6ffk7ia8iqae` FOREIGN KEY (`id_company`) REFERENCES `elenco_companies` (`id`);

--
-- Constraints for table `elenco_clients_elenco_licenze`
--
ALTER TABLE `elenco_clients_elenco_licenze`
  ADD CONSTRAINT `FKat02ecko02jonr3e6pkqxbx2x` FOREIGN KEY (`licenza_in_uso`) REFERENCES `elenco_clients` (`id`),
  ADD CONSTRAINT `FKbro1cgnpaacrotn2o8e7rjwgy` FOREIGN KEY (`id`) REFERENCES `elenco_clients` (`id`),
  ADD CONSTRAINT `FKeirmks1ey8igkqvsptltrgia2` FOREIGN KEY (`id`) REFERENCES `elenco_licenze` (`id`);

--
-- Constraints for table `elenco_licenze`
--
ALTER TABLE `elenco_licenze`
  ADD CONSTRAINT `FK3krlis5duo9x7953t77ncn8h1` FOREIGN KEY (`acquistato_da`) REFERENCES `elenco_companies` (`id`),
  ADD CONSTRAINT `FKsjuw7sojqvfxwha7chpnva5vm` FOREIGN KEY (`id_tipo`) REFERENCES `tipologie_licenze` (`id`);

--
-- Constraints for table `elenco_operazioni_tipologie_licenze`
--
ALTER TABLE `elenco_operazioni_tipologie_licenze`
  ADD CONSTRAINT `FKlmdm9vojwtqko36rp5jxvp7mu` FOREIGN KEY (`classe`) REFERENCES `elenco_operazioni` (`id`),
  ADD CONSTRAINT `FKmfp6ymqxcmbwyqgu7xoo9v0ef` FOREIGN KEY (`licenza_riferimento`) REFERENCES `tipologie_licenze` (`id`);
