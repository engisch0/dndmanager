-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Erstellungszeit: 25. Jul 2025 um 21:48
-- Server-Version: 11.5.2-MariaDB
-- PHP-Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `projekt_praktikumsersatz`
--

DELIMITER $$
--
-- Funktionen
--
DROP FUNCTION IF EXISTS `f_checkIfUserIsPlayerOrGameMaster`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `f_checkIfUserIsPlayerOrGameMaster` (`usernameParam` VARCHAR(1000)) RETURNS INT(11)  BEGIN
    -- ID in der Spieler-Tabelle
    DECLARE idInThePlayerName INT DEFAULT -1;

    -- Die ID des Benutzers wird aufgerufen
    DECLARE userIDVar INT DEFAULT -1;
    SELECT t_user.userID
    FROM t_user
    WHERE t_user.username = usernameParam
    INTO userIDVar;

    -- Wenn der Benutzername nicht gefunden wurde, wird die Funktion beendet
    IF userIDVar = -1 THEN
        RETURN -1; -- das heißt, dieses Benutzername wurde nicht gefunden.
    END IF;

    -- Jetzt wird bestimmt, ob der Benutzer ein Spieler ist, indem sein Referenz in der Spieler-Tabelle überprüft wird.
    SELECT t_player.playerID
    FROM t_player
    JOIN t_user ON t_user.userID = t_player.playerID
    WHERE t_user.userID = t_player.playerID
    INTO idInThePlayerName;

    IF idInThePlayerName = userIDVar THEN -- Die Tabellen Spieler und Spielleiter haben dasselbe Nummer wie die in der ID der Benutzer-Tabelle.
        RETURN 1; -- Benutzer ist ein Spieler
    ELSE
        RETURN 2; -- Benutzer ist ein Spielleiter
    END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_character`
--

DROP TABLE IF EXISTS `t_character`;
CREATE TABLE IF NOT EXISTS `t_character` (
  `characterID` int(11) NOT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `race` varchar(1000) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `size` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`characterID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Daten für Tabelle `t_character`
--

INSERT INTO `t_character` (`characterID`, `name`, `race`, `age`, `size`) VALUES
(1, 'Drache', 'Drachenrasse', 10, 1.76);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_gamemaster`
--

DROP TABLE IF EXISTS `t_gamemaster`;
CREATE TABLE IF NOT EXISTS `t_gamemaster` (
  `gamemasterID` int(11) NOT NULL,
  PRIMARY KEY (`gamemasterID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Daten für Tabelle `t_gamemaster`
--

INSERT INTO `t_gamemaster` (`gamemasterID`) VALUES
(2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_ownerofcharacter`
--

DROP TABLE IF EXISTS `t_ownerofcharacter`;
CREATE TABLE IF NOT EXISTS `t_ownerofcharacter` (
  `playerID` int(11) DEFAULT NULL,
  `characterID` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Daten für Tabelle `t_ownerofcharacter`
--

INSERT INTO `t_ownerofcharacter` (`playerID`, `characterID`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_player`
--

DROP TABLE IF EXISTS `t_player`;
CREATE TABLE IF NOT EXISTS `t_player` (
  `playerID` int(11) NOT NULL,
  PRIMARY KEY (`playerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Daten für Tabelle `t_player`
--

INSERT INTO `t_player` (`playerID`) VALUES
(1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_user`
--

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
  `userID` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `last_login` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Daten für Tabelle `t_user`
--

INSERT INTO `t_user` (`userID`, `username`, `password`, `last_login`, `created_at`) VALUES
(1, 'player1', '1234', '2025-07-25 21:44:06', '2025-07-25 21:44:06'),
(2, 'gamemaster1', '1234', '2025-07-25 21:44:25', '2025-07-25 21:44:25'),
(3, 'testuser', 'testuser', '2025-07-17 11:15:06', '2025-07-17 11:15:06');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
