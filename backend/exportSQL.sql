-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Mar 25, 2021 at 08:08 AM
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
  `drive` varchar(255) DEFAULT NULL,
  `perc_free_disc_space` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `date_and_time` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `conf_windows_services`
--

CREATE TABLE `conf_windows_services` (
  `id` int(11) NOT NULL,
  `nome_servizio` varchar(255) DEFAULT NULL,
  `notifica` varchar(255) DEFAULT NULL,
  `stato` varchar(255) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `attivo` bit(1) NOT NULL,
  `esecuzione` bit(1) NOT NULL,
  `tipo_alert` int(11) NOT NULL,
  `date_and_time` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `elenco_clients`
--

CREATE TABLE `elenco_clients` (
  `id` int(11) NOT NULL,
  `mac_address` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `id_company` int(11) DEFAULT NULL,
  `licenza_in_uso` int(11) DEFAULT NULL,
  `tipologia_client` int(11) DEFAULT NULL,
  `sede` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `elenco_clients`
--

INSERT INTO `elenco_clients` (`id`, `mac_address`, `nome`, `id_company`, `licenza_in_uso`, `tipologia_client`, `sede`) VALUES
(6, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(7, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(8, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(9, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(10, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(11, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(12, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(13, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(14, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(15, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(16, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(17, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(18, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(19, 'XX-XX-XX-XX-XX-XX', 'Nuovo Client', 7, NULL, 1, 'Milano'),
(20, '12-34-56-78-09-10', 'Desktop XYZ', 7, NULL, 1, 'Crema');

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
(6, 7),
(7, 7),
(8, 7),
(9, 7),
(10, 7),
(11, 7),
(12, 7),
(18, 7),
(19, 7),
(20, 7);

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
(2, 'alertModificato@alertModificato.it', 'alertModificato@alertModificato.it', 'password', 'Nuova ragione sociale'),
(4, 'prova3@prova3.it', 'alert@prova3.it', 'password', 'Prova3'),
(5, 'prova4@prova4.it', 'alert@prova4.it', 'password', 'Prova4'),
(6, 'prova5@prova5.it', 'alert@prova5.it', 'password', 'Prova5'),
(7, 'newCompany@prova.it', 'alert@newCompany.it', 'password', 'New Company');

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
(6, 'HgUM-IUGy-yLqn-sKN0', '2021-03-12 14:50:25.122000', 6, 1),
(7, 'YWZY-LpOg-86Su-e9wd', '2021-03-18 15:20:03.657000', 7, 1),
(8, 'Ek3G-u0d6-endn-JsdD', '2021-03-19 16:28:59.782000', 7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `elenco_operazioni`
--

CREATE TABLE `elenco_operazioni` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `tabella_riferimento` varchar(255) DEFAULT NULL,
  `licenza_riferimento` int(11) NOT NULL,
  `stato` bit(1) NOT NULL
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
  `classe` int(11) NOT NULL,
  `nome_categoria` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `macro_categorie_eventi`
--

INSERT INTO `macro_categorie_eventi` (`id`, `classe`, `nome_categoria`) VALUES
(1, 1, 'Custom views'),
(2, 2, 'Windows Logs'),
(3, 3, 'Applications and Services Logs');

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
(1, 1, 'Sistema Operativo'),
(2, 2, 'Backup'),
(3, 3, 'Antivirus'),
(4, 4, 'Rete'),
(5, 5, 'Vulnerabilità');

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
  `sottocategoria` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `task_category` varchar(255) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `macro_categoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ADD KEY `FKmqd53i0wh0trbaw0cs4j9nbmd` (`id_client`);

--
-- Indexes for table `elenco_clients`
--
ALTER TABLE `elenco_clients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqsy0knx8e74cg6ffk7ia8iqae` (`id_company`),
  ADD KEY `FK20p0tg69yae8snat3h776dg0q` (`licenza_in_uso`),
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
  ADD KEY `FKmp6kno3iqay3n6c4jeak9lb01` (`id_client`),
  ADD KEY `FKb2qd6hbgsf4l8gh9xm1jek2vi` (`macro_categoria`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `elenco_companies`
--
ALTER TABLE `elenco_companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `elenco_licenze`
--
ALTER TABLE `elenco_licenze`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `elenco_operazioni`
--
ALTER TABLE `elenco_operazioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `macro_categorie_eventi`
--
ALTER TABLE `macro_categorie_eventi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `FKmqd53i0wh0trbaw0cs4j9nbmd` FOREIGN KEY (`id_client`) REFERENCES `elenco_clients` (`id`);

--
-- Constraints for table `elenco_clients`
--
ALTER TABLE `elenco_clients`
  ADD CONSTRAINT `FK20p0tg69yae8snat3h776dg0q` FOREIGN KEY (`licenza_in_uso`) REFERENCES `elenco_licenze` (`id`),
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
-- Constraints for table `visualizzazione_eventi`
--
ALTER TABLE `visualizzazione_eventi`
  ADD CONSTRAINT `FKb2qd6hbgsf4l8gh9xm1jek2vi` FOREIGN KEY (`macro_categoria`) REFERENCES `macro_categorie_eventi` (`id`),
  ADD CONSTRAINT `FKmp6kno3iqay3n6c4jeak9lb01` FOREIGN KEY (`id_client`) REFERENCES `elenco_clients` (`id`);
