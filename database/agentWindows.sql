-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 09, 2021 at 03:34 PM
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
(1, '2021-04-09 16:28:36.937747', 'Nuovo Disco 1', 'Disco 1', 3, 62.5, 20000, 32000),
(2, '2021-04-09 16:28:36.937747', 'Nuovo Disco 2', 'Disco 2', 3, 53.84615384615385, 7000, 13000);

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
(3, '2021-04-08 14:52:34.158157', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(4, '2021-04-08 14:52:34.158157', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(5, '2021-04-08 14:56:53.950022', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(6, '2021-04-08 14:56:53.950022', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(7, '2021-04-08 14:58:09.256694', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(8, '2021-04-08 14:58:09.256694', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(9, '2021-04-08 15:01:35.195411', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(10, '2021-04-08 15:01:35.195411', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(11, '2021-04-08 15:02:09.680904', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(12, '2021-04-08 15:02:09.680904', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(13, '2021-04-08 15:03:49.138922', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(14, '2021-04-08 15:03:49.138922', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(15, '2021-04-08 15:04:24.471745', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(16, '2021-04-08 15:04:24.471745', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(17, '2021-04-08 15:05:04.225321', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(18, '2021-04-08 15:05:04.225321', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(19, '2021-04-08 15:09:59.572757', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(20, '2021-04-08 15:09:59.572757', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(21, '2021-04-08 15:14:11.427701', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(22, '2021-04-08 15:14:11.427701', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(23, '2021-04-08 15:20:37.869466', NULL, 'BBB', 3, 2, 1, 1, 'AAA'),
(24, '2021-04-08 15:20:37.869466', NULL, 'DDD', 6, 5, 4, 1, 'CCC'),
(25, '2021-04-08 15:21:43.667291', NULL, 'www', 3, 2, 1, 1, 'NuovoServizio1'),
(26, '2021-04-08 15:21:43.667291', NULL, 'qqq', 6, 5, 4, 1, 'NuovoServizio2'),
(27, '2021-04-08 15:22:21.636640', NULL, 'www', 3, 2, 1, 1, 'NuovoServizio1'),
(28, '2021-04-08 15:22:21.636640', NULL, 'qqq', 6, 5, 4, 1, 'NuovoServizio2'),
(29, '2021-04-08 15:22:54.846360', NULL, 'www', 3, 2, 1, 1, 'NuovoServizio1'),
(30, '2021-04-08 15:22:54.846360', NULL, 'qqq', 6, 5, 4, 1, 'NuovoServizio2'),
(31, '2021-04-08 15:23:20.143197', NULL, 'www', 3, 2, 1, 1, 'NuovoServizio1'),
(32, '2021-04-08 15:23:20.143197', NULL, 'qqq', 6, 5, 4, 1, 'NuovoServizio2'),
(33, '2021-04-08 15:23:26.514552', NULL, 'www', 3, 2, 1, 1, 'NuovoServizio1'),
(34, '2021-04-08 15:23:26.514552', NULL, 'qqq', 6, 5, 4, 1, 'NuovoServizio2'),
(35, '2021-04-08 15:28:14.800353', NULL, 'www', 3, 2, 1, 1, 'NuovoServizio1'),
(36, '2021-04-08 15:28:14.800353', NULL, 'qqq', 6, 5, 4, 1, 'NuovoServizio2'),
(37, '2021-04-08 15:28:41.275378', NULL, 'www', 3, 2, 1, 1, 'NuovoServizio1'),
(38, '2021-04-08 15:28:41.275378', NULL, 'qqq', 6, 5, 4, 1, 'NuovoServizio2'),
(39, '2021-04-08 15:31:06.470254', NULL, 'www', 3, 2, 1, 3, 'NuovoServizio1'),
(40, '2021-04-08 15:31:06.470254', NULL, 'qqq', 6, 5, 4, 3, 'NuovoServizio2'),
(41, '2021-04-08 15:32:18.699747', NULL, 'www', 3, 2, 1, 3, 'prova'),
(42, '2021-04-08 15:32:18.699747', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(43, '2021-04-08 15:35:43.125683', NULL, 'www', 3, 2, 1, 3, 'prova'),
(44, '2021-04-08 15:35:43.125683', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(45, '2021-04-09 15:46:08.725101', NULL, 'www', 3, 2, 1, 3, 'prova'),
(46, '2021-04-09 15:46:08.725101', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(47, '2021-04-09 15:47:21.931276', NULL, 'www', 3, 2, 1, 3, 'prova'),
(48, '2021-04-09 15:47:21.931276', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(49, '2021-04-09 15:49:17.273554', NULL, 'www', 3, 2, 1, 3, 'prova'),
(50, '2021-04-09 15:49:17.273554', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(51, '2021-04-09 15:50:39.717633', NULL, 'www', 3, 2, 1, 3, 'prova'),
(52, '2021-04-09 15:50:39.717633', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(53, '2021-04-09 16:05:30.043096', NULL, 'www', 3, 2, 1, 3, 'prova'),
(54, '2021-04-09 16:05:30.043096', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(55, '2021-04-09 16:08:17.540682', NULL, 'www', 3, 2, 1, 3, 'prova'),
(56, '2021-04-09 16:08:17.540682', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(57, '2021-04-09 16:08:34.362559', NULL, 'www', 3, 2, 1, 3, 'prova'),
(58, '2021-04-09 16:08:34.362559', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(59, '2021-04-09 16:11:08.899736', NULL, 'www', 3, 2, 1, 3, 'prova'),
(60, '2021-04-09 16:11:08.899736', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(61, '2021-04-09 16:17:52.658760', NULL, 'www', 3, 2, 1, 3, 'prova'),
(62, '2021-04-09 16:17:52.658760', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(63, '2021-04-09 16:20:47.525267', NULL, 'www', 3, 2, 1, 3, 'prova'),
(64, '2021-04-09 16:20:47.525267', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(65, '2021-04-09 16:22:21.595437', NULL, 'www', 3, 2, 1, 3, 'prova'),
(66, '2021-04-09 16:22:21.595437', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(67, '2021-04-09 16:22:58.642193', NULL, 'www', 3, 2, 1, 3, 'prova'),
(68, '2021-04-09 16:22:58.642193', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(69, '2021-04-09 16:24:03.028779', NULL, 'www', 3, 2, 1, 3, 'prova'),
(70, '2021-04-09 16:24:03.028779', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(71, '2021-04-09 16:24:23.309149', NULL, 'www', 3, 2, 1, 3, 'prova'),
(72, '2021-04-09 16:24:23.309149', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(73, '2021-04-09 16:25:57.595914', NULL, 'www', 3, 2, 1, 3, 'prova'),
(74, '2021-04-09 16:25:57.595914', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(75, '2021-04-09 16:27:05.583746', NULL, 'www', 3, 2, 1, 3, 'prova'),
(76, '2021-04-09 16:27:05.583746', NULL, 'qqq', 6, 5, 4, 3, 'lalalala'),
(77, '2021-04-09 16:28:36.937747', NULL, 'www', 3, 2, 1, 3, 'prova'),
(78, '2021-04-09 16:28:36.937747', NULL, 'qqq', 6, 5, 4, 3, 'lalalala');

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
  `descrizione` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `elenco_clients`
--

INSERT INTO `elenco_clients` (`id`, `mac_address`, `nome`, `sede`, `id_company`, `tipologia_client`, `descrizione`) VALUES
(1, NULL, 'Agent Client', NULL, 3, NULL, 'Questa è una nuova macchina client'),
(2, NULL, 'Agent Client', NULL, 3, NULL, 'Questa è una nuova macchina client'),
(3, 'MACAddress', 'Agent Client', NULL, 3, NULL, 'Questa è una nuova macchina client'),
(5, 'XX-XX-XX-XX-XX-XX', 'Prova finale', NULL, 3, 1, 'Funziona?');

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
(2, 1),
(3, 1),
(5, 1);

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
(1, 'io@prova.it', 'alert@io.it', 'password', 'io'),
(2, 'pio@prova.it', 'alert@pio.it', 'password', 'pio'),
(3, 're@prova.it', 'alert@re.it', 'password', 're');

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
(1, '9PV9AAPMSU2R2AKL', '2021-04-02 16:17:04.643000', 3, NULL);

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
(2, b'1', 'AAA', 1),
(3, b'1', 'CCC', 1),
(7, b'1', 'NuovoServizio1', 1),
(8, b'1', 'NuovoServizio2', 1),
(11, b'1', 'NuovoServizio1', 3),
(12, b'1', 'NuovoServizio2', 3),
(13, b'1', 'prova', 3),
(14, b'1', 'lalalala', 3);

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
(2, '2021-04-09 17:26:58.598270', 945, 'Info3', 6, 0, 'H', 'LOP', NULL, 1, '2020-07-21 15:35:00.000000'),
(3, '2021-04-09 17:26:58.598270', 564, 'Info4', 12, 0, 'H', 'ETR', NULL, 1, '2020-07-21 15:35:00.000000'),
(4, '2021-04-09 17:26:58.598270', 867, 'Info5', 5, 0, 'S', 'EBV', NULL, 1, '2020-07-21 15:35:00.000000'),
(5, '2021-04-09 17:26:58.598270', 967, 'Info6', 11, 0, 'S', '549', NULL, 1, '2020-07-21 15:35:00.000000'),
(6, '2021-04-09 17:26:58.598270', 385, 'Info7', 7, 0, 'C', 'PRE', NULL, 1, '2020-07-21 15:35:00.000000'),
(7, '2021-04-09 17:26:58.598270', 943, 'Info8', 9, 0, 'C', 'TYU', NULL, 1, '2020-07-21 15:35:00.000000'),
(8, '2021-04-09 17:32:39.632328', 324, 'Info1', 3, 0, 'A', 'CVX', NULL, 1, '2020-07-21 15:35:00.000000'),
(9, '2021-04-09 17:32:39.632328', 124, 'Info2', 4, 0, 'A', 'XYZ', NULL, 1, '2020-07-21 15:35:00.000000'),
(10, '2021-04-09 17:32:39.632328', 945, 'Info3', 6, 0, 'H', 'LOP', NULL, 1, '2020-07-21 15:35:00.000000'),
(11, '2021-04-09 17:32:39.632328', 564, 'Info4', 12, 0, 'H', 'ETR', NULL, 1, '2020-07-21 15:35:00.000000'),
(12, '2021-04-09 17:32:39.632328', 867, 'Info5', 5, 0, 'S', 'EBV', NULL, 1, '2020-07-21 15:35:00.000000'),
(13, '2021-04-09 17:32:39.632328', 967, 'Info6', 11, 0, 'S', '549', NULL, 1, '2020-07-21 15:35:00.000000'),
(14, '2021-04-09 17:32:39.632328', 385, 'Info7', 7, 0, 'C', 'PRE', NULL, 1, '2020-07-21 15:35:00.000000'),
(15, '2021-04-09 17:32:39.632328', 943, 'Info8', 9, 0, 'C', 'TYU', NULL, 1, '2020-07-21 15:35:00.000000');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `conf_windows_services`
--
ALTER TABLE `conf_windows_services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT for table `elenco_clients`
--
ALTER TABLE `elenco_clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `elenco_companies`
--
ALTER TABLE `elenco_companies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `visualizzazione_eventi`
--
ALTER TABLE `visualizzazione_eventi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

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
