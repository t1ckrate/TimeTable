-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : db
-- Généré le : mer. 13 jan. 2021 à 01:42
-- Version du serveur :  5.7.32
-- Version de PHP : 7.4.11

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `discordBot`
--

-- --------------------------------------------------------

--
-- Structure de la table `calendars`
--

CREATE TABLE `calendars`
(
    `channelId`     bigint(20) NOT NULL,
    `calendarTitle` text NOT NULL,
    `calendarUrl`   text NOT NULL,
    `messageId`     bigint(20) NOT NULL,
    `guildId`       bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events`
(
    `eventId`      bigint(20) NOT NULL,
    `eventName`    text NOT NULL,
    `eventType`    text NOT NULL,
    `eventDesc`    text NOT NULL,
    `deadlineDate` date NOT NULL,
    `channelId`    bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `guilds`
--

CREATE TABLE `guilds`
(
    `guildId` bigint(20) NOT NULL,
    `prefix`  text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `calendars`
--
ALTER TABLE `calendars`
    ADD PRIMARY KEY (`channelId`),
  ADD KEY `guildId` (`guildId`);

--
-- Index pour la table `events`
--
ALTER TABLE `events`
    ADD PRIMARY KEY (`eventId`),
  ADD KEY `channelId` (`channelId`);

--
-- Index pour la table `guilds`
--
ALTER TABLE `guilds`
    ADD PRIMARY KEY (`guildId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
    MODIFY `eventId` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `calendars`
--
ALTER TABLE `calendars`
    ADD CONSTRAINT `calendars_ibfk_1` FOREIGN KEY (`guildId`) REFERENCES `guilds` (`guildId`);

--
-- Contraintes pour la table `events`
--
ALTER TABLE `events`
    ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`channelId`) REFERENCES `calendars` (`channelId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
