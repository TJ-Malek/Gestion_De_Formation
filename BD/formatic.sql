-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 01 juin 2021 à 02:59
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `formatic`
--
CREATE DATABASE formatic;
-- --------------------------------------------------------

--
-- Structure de la table `chapitre`
--

CREATE TABLE `chapitre` (
  `id` bigint(20) NOT NULL,
  `id_Cours` bigint(20) NOT NULL,
  `designation` varchar(500) NOT NULL,
  `date_ajout` datetime NOT NULL,
  `etat` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `chapitre`
--

INSERT INTO `chapitre` (`id`, `id_Cours`, `designation`, `date_ajout`, `etat`) VALUES
(2, 7, 'Structures conditionnelles', '2021-05-31 23:36:26', 1);

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `id` bigint(20) NOT NULL,
  `id_Cursus` bigint(20) NOT NULL,
  `designation` varchar(500) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `date_ajout` datetime NOT NULL,
  `etat` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `id_Cursus`, `designation`, `image`, `date_ajout`, `etat`) VALUES
(7, 2, 'PHP', NULL, '2021-05-31 16:41:59', 0);

-- --------------------------------------------------------

--
-- Structure de la table `cursus`
--

CREATE TABLE `cursus` (
  `id` bigint(20) NOT NULL,
  `designation` varchar(500) NOT NULL,
  `contenu` varchar(500) NOT NULL,
  `pdf` varchar(500) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `volume_horaire` int(11) NOT NULL,
  `date_ajout` datetime NOT NULL,
  `id_Formateur` bigint(20) NOT NULL,
  `etat` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cursus`
--

INSERT INTO `cursus` (`id`, `designation`, `contenu`, `pdf`, `image`, `volume_horaire`, `date_ajout`, `id_Formateur`, `etat`) VALUES
(2, 'PHP', 'PHP5,SYMFONY', NULL, NULL, 10, '2021-05-31 16:41:43', 8, 1);

-- --------------------------------------------------------

--
-- Structure de la table `cursus_suivis`
--

CREATE TABLE `cursus_suivis` (
  `id` bigint(20) NOT NULL,
  `id_Utilisateur` bigint(20) NOT NULL,
  `id_Chapitre` bigint(20) DEFAULT NULL,
  `id_Cours` bigint(20) DEFAULT NULL,
  `id_Cursus` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cursus_suivis`
--

INSERT INTO `cursus_suivis` (`id`, `id_Utilisateur`, `id_Chapitre`, `id_Cours`, `id_Cursus`) VALUES
(1, 8, NULL, NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `formateur`
--

CREATE TABLE `formateur` (
  `id` bigint(20) NOT NULL,
  `telephone` int(11) NOT NULL,
  `parcours` varchar(1000) NOT NULL,
  `etat` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `formateur`
--

INSERT INTO `formateur` (`id`, `telephone`, `parcours`, `etat`) VALUES
(8, 69457812, 'développeur ANDROID', 0);

-- --------------------------------------------------------

--
-- Structure de la table `section`
--

CREATE TABLE `section` (
  `id` bigint(20) NOT NULL,
  `id_Chapitre` bigint(20) NOT NULL,
  `designation` varchar(500) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `contenu` varchar(500) NOT NULL,
  `date_ajout` datetime NOT NULL,
  `etat` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `section`
--

INSERT INTO `section` (`id`, `id_Chapitre`, `designation`, `image`, `contenu`, `date_ajout`, `etat`) VALUES
(1, 2, 'if else', NULL, 'if else, permet de tester si une condtion est respectée ou non.Dans notre exmple ci dessous, si la condition est respectée, elle renvoit \"instruction1\", sinon \"instruction2\"\r\n<?php\r\nif(condition) {\r\n    // instruction1;\r\n}\r\nelse{\r\n    // instruction2;\r\n}\r\n?> ', '2021-06-01 00:26:22', 0);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(500) NOT NULL,
  `prenom` varchar(500) NOT NULL,
  `role` varchar(100) NOT NULL,
  `email` varchar(500) NOT NULL,
  `mdp` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `role`, `email`, `mdp`) VALUES
(6, 'utilisateur1Nom', 'utilisateur1Prenom', 'etudiant', 'utilisateur1@gmail.com', '123456'),
(8, 'tajine', 'malek', 'etudiant', 'malektajine@gmail.com', '123'),
(9, 'admin', 'admin', 'admin', 'admin@gmail.com', '123');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chapitre`
--
ALTER TABLE `chapitre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_Cours` (`id_Cours`);

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_Cursus` (`id_Cursus`);

--
-- Index pour la table `cursus`
--
ALTER TABLE `cursus`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_Formateur` (`id_Formateur`);

--
-- Index pour la table `cursus_suivis`
--
ALTER TABLE `cursus_suivis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_Utilisateur` (`id_Utilisateur`),
  ADD KEY `id_Chapitre` (`id_Chapitre`),
  ADD KEY `id_Cours` (`id_Cours`),
  ADD KEY `id_Cursus` (`id_Cursus`);

--
-- Index pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Index pour la table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_Chapitre` (`id_Chapitre`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `chapitre`
--
ALTER TABLE `chapitre`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `cursus`
--
ALTER TABLE `cursus`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `cursus_suivis`
--
ALTER TABLE `cursus_suivis`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `section`
--
ALTER TABLE `section`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `chapitre`
--
ALTER TABLE `chapitre`
  ADD CONSTRAINT `fk_idCours_Chapitre` FOREIGN KEY (`id_Cours`) REFERENCES `cours` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `fk_idCursus_Cours` FOREIGN KEY (`id_Cursus`) REFERENCES `cursus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cursus`
--
ALTER TABLE `cursus`
  ADD CONSTRAINT `fk_idFormateur_Cursus` FOREIGN KEY (`id_Formateur`) REFERENCES `formateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cursus_suivis`
--
ALTER TABLE `cursus_suivis`
  ADD CONSTRAINT `fk_Chapitre_CursusSuivis` FOREIGN KEY (`id_Chapitre`) REFERENCES `chapitre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Cours_CursusSuivis` FOREIGN KEY (`id_Cours`) REFERENCES `cours` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Cursus_CursusSuivis` FOREIGN KEY (`id_Cursus`) REFERENCES `cursus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD CONSTRAINT `fk_idUtilisateur_Formateur` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `section`
--
ALTER TABLE `section`
  ADD CONSTRAINT `fk_idChapitre_Section` FOREIGN KEY (`id_Chapitre`) REFERENCES `chapitre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
