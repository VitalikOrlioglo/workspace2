-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 10. Okt 2019 um 14:17
-- Server-Version: 10.4.6-MariaDB
-- PHP-Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `java2`
--
CREATE DATABASE IF NOT EXISTS `java2` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `java2`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `designpattern`
--

CREATE TABLE IF NOT EXISTS `designpattern` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `beschreibung` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `designpattern`
--

INSERT INTO `designpattern` (`id`, `name`, `beschreibung`) VALUES
(1, 'Singleton', 'Nur ein Objekt'),
(2, 'MVC', 'Trennung Model - View');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matrikelnummer` varchar(10) COLLATE utf8_bin NOT NULL,
  `vorname` varchar(30) COLLATE utf8_bin NOT NULL,
  `nachname` varchar(30) COLLATE utf8_bin NOT NULL,
  `geburtsdatum` date NOT NULL,
  `bild` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `matrikelnummer` (`matrikelnummer`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `student`
--

INSERT INTO `student` (`id`, `matrikelnummer`, `vorname`, `nachname`, `geburtsdatum`, `bild`) VALUES
(3, 'M1', 'Kaban', 'Schwarz', '2019-10-23', 'user1.jpg'),
(4, 'M2', 'Patkan', 'Blau', '2019-10-01', 'user2.jpg');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
