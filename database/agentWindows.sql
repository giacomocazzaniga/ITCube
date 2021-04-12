-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2021 at 01:50 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agentwindows`
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

-- --------------------------------------------------------

--
-- Table structure for table `conf_windows_services`
--

CREATE TABLE `conf_windows_services` (
  `id` int(11) NOT NULL,
  `date_and_time` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `service_type` int(11) NOT NULL,
  `start_type` int(11) NOT NULL,
  `stato` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `nome_servizio` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `conf_windows_services`
--

INSERT INTO `conf_windows_services` (`id`, `date_and_time`, `description`, `display_name`, `service_type`, `start_type`, `stato`, `id_client`, `nome_servizio`) VALUES
(1, '2021-04-09 18:56:07.000000', 'Adobe Update Service', 'Adobe Update Service', 1, 1, 4, 1, 'Adobe Update Service');

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
(1, '00:1B:44:11:3A:B7', 'DESKTOP-3874', 'Roma', 1, 1, 'DESKTOP-3874', 1),
(2, '00:1B:94:51:4N:B0', 'DESKTOP-2049', 'Torino', 1, 1, 'DESKTOP-2049', 1),
(3, '00:09:20:39:4M:J0', 'SERVER-2209', 'Bologna', 1, 2, 'SERVER-2209', 1),
(4, '00:1B:44:11:3A:B1', 'DESKTOP-000', 'Milano', 1, 1, 'DESKTOP-000', NULL),
(5, '00:1C:33:11:1P:B1', 'DESKTOP-002', 'Milano', 1, 1, 'DESKTOP-002', NULL),
(6, '00:1C:33:11:1P:A9', 'DESKTOP-003', 'Milano', 1, 1, 'DESKTOP-003', NULL),
(7, '00:1C:44:11:1P:S1', 'DESKTOP-004', 'Milano', 1, 1, 'DESKTOP-004', NULL),
(8, '00:4P:44:11:1P:S1', 'DESKTOP-005', 'Milano', 1, 2, 'DESKTOP-005', NULL),
(9, '00:4P:44:31:AA:S1', 'DESKTOP-006', 'Milano', 1, 2, 'DESKTOP-006', NULL),
(10, '00:4P:44:31:BB:S1', 'DESKTOP-007', 'Milano', 1, 2, 'DESKTOP-007', NULL),
(11, '00:4P:44:31:CC:S1', 'DESKTOP-008', 'Milano', 1, 1, 'DESKTOP-008', NULL);

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
(11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `elenco_companies`
--

CREATE TABLE `elenco_companies` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_alert` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `ragione_sociale` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `elenco_companies`
--

INSERT INTO `elenco_companies` (`id`, `email`, `email_alert`, `password`, `ragione_sociale`) VALUES
(1, 'email@itcubeconsulting.it', 'alert@itcubeconsulting.it', '5f4dcc3b5aa765d61d8327deb882cf99', 'ITCUBE');

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
(1, 'T896-ZJ7Q-E5KP-NKQN', '2021-04-09 19:02:39.000000', 1, 1);

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
(1, b'0', 'Adobe Update Service', 1);

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
(7, '2021-04-11 10:16:48.000000', 100, 'Info test C', 1, 0, 'C', 'Source C', NULL, 1, '2021-04-11 18:57:00.000000');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `conf_windows_services`
--
ALTER TABLE `conf_windows_services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `elenco_clients`
--
ALTER TABLE `elenco_clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `elenco_companies`
--
ALTER TABLE `elenco_companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `elenco_licenze`
--
ALTER TABLE `elenco_licenze`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `real_time`
--
ALTER TABLE `real_time`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
