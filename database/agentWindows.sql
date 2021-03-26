-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Mar 26, 2021 at 04:54 PM
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
  `drive` varchar(255) DEFAULT NULL,
  `perc_free_disc_space` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL
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
  `tipologia_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `elenco_clients_elenco_licenze`
--

CREATE TABLE `elenco_clients_elenco_licenze` (
  `licenza_in_uso` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `nome_categoria` varchar(255) DEFAULT NULL
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

-- --------------------------------------------------------

--
-- Table structure for table `tipologie_licenze`
--

CREATE TABLE `tipologie_licenze` (
  `id` int(11) NOT NULL,
  `classe` int(11) NOT NULL,
  `nome_tipologia` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `sottocategoria` int(11) NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `task_category` varchar(255) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `visualizzazione_eventi`
--

INSERT INTO `visualizzazione_eventi` (`id`, `date_and_time`, `id_event`, `info`, `level`, `macro_categoria`, `sottocategoria`, `source`, `task_category`, `id_client`) VALUES
(1, NULL, 999, 'Tua zia', 4, 2, 1, 'XYZ', 'Prosciutto', NULL),
(2, NULL, 333, 'Tua nonna', 5, 3, 1, 'ASD', 'Bresaola', NULL);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK786y98nk3p3c98nu102ju47fp` (`id_client`);

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
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9jagpj9rmxf92n2fnjhmpf033` (`nome_servizio`),
  ADD KEY `FK49h5b4wcnmjx8urvacsfhx84i` (`id_client`);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmp6kno3iqay3n6c4jeak9lb01` (`id_client`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `elenco_clients`
--
ALTER TABLE `elenco_clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `elenco_companies`
--
ALTER TABLE `elenco_companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `elenco_licenze`
--
ALTER TABLE `elenco_licenze`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `real_time`
--
ALTER TABLE `real_time`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tipologia_client`
--
ALTER TABLE `tipologia_client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tipologie_licenze`
--
ALTER TABLE `tipologie_licenze`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `visualizzazione_eventi`
--
ALTER TABLE `visualizzazione_eventi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
-- Constraints for table `conf_total_free_disc_space`
--
ALTER TABLE `conf_total_free_disc_space`
  ADD CONSTRAINT `FK786y98nk3p3c98nu102ju47fp` FOREIGN KEY (`id_client`) REFERENCES `elenco_clients` (`id`);

--
-- Constraints for table `conf_windows_services`
--
ALTER TABLE `conf_windows_services`
  ADD CONSTRAINT `FKgweg5amjhfcpfifx1e0kx7olq` FOREIGN KEY (`nome_servizio`) REFERENCES `monitoraggio` (`nome_servizio`),
  ADD CONSTRAINT `FKmqd53i0wh0trbaw0cs4j9nbmd` FOREIGN KEY (`id_client`) REFERENCES `elenco_clients` (`id`);

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

--
-- Constraints for table `monitoraggio`
--
ALTER TABLE `monitoraggio`
  ADD CONSTRAINT `FK49h5b4wcnmjx8urvacsfhx84i` FOREIGN KEY (`id_client`) REFERENCES `elenco_clients` (`id`);

--
-- Constraints for table `visualizzazione_eventi`
--
ALTER TABLE `visualizzazione_eventi`
  ADD CONSTRAINT `FKmp6kno3iqay3n6c4jeak9lb01` FOREIGN KEY (`id_client`) REFERENCES `elenco_clients` (`id`);
