-- phpMyAdmin SQL Dump
-- version 3.3.7deb7
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mar 17 Novembre 2015 à 11:19
-- Version du serveur: 5.1.63
-- Version de PHP: 5.3.3-7+squeeze14

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `GSB`
--

-- --------------------------------------------------------

--
-- Structure de la table `LOCALITE`
--

Drop Database if exists gsbv2;
Create database gsbv2;
use gsbv2;

CREATE TABLE IF NOT EXISTS `LOCALITE` (
  `CODEPOSTAL` varchar(5) NOT NULL,
  `VILLE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODEPOSTAL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `LOCALITE`
--

INSERT INTO `LOCALITE` (`CODEPOSTAL`, `VILLE`) VALUES
('13012', 'Allauh'),
('13015', 'Marseille'),
('13025', 'Berre'),
('23100', 'La souteraine'),
('23120', 'GrandBourg'),
('23200', 'Guéret'),
('44000', 'Nantes'),
('45000', 'Orléans'),
('46000', 'Cahors'),
('46123', 'Gramat'),
('46200', 'Lalbenque'),
('46250', 'Montcuq'),
('46512', 'Bessines'),
('75011', 'Paris 11'),
('75017', 'Paris 17'),
('75019', 'Paris 19'),
('93100', 'Montreuil'),
('93210', 'Rosny'),
('93230', 'Romainville'),
('94000', 'Créteil');

-- --------------------------------------------------------

--
-- Structure de la table `MEDECIN`
--

CREATE TABLE IF NOT EXISTS `MEDECIN` (
  `CODEMED` char(4) NOT NULL,
  `NOM` varchar(50) DEFAULT NULL,
  `PRENOM` varchar(50) DEFAULT NULL,
  `ADRESSE` varchar(50) DEFAULT NULL,
  `CODEPOSTAL` varchar(5) NOT NULL,
  `TELEPHONE` varchar(15) DEFAULT NULL,
  `POTENTIEL` varchar(50) DEFAULT NULL,
  `SPECIALITE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODEMED`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `MEDECIN`
--

INSERT INTO `MEDECIN` (`CODEMED`, `NOM`, `PRENOM`, `ADRESSE`, `CODEPOSTAL`, `TELEPHONE`, `POTENTIEL`, `SPECIALITE`) VALUES
('m001', 'SMITH', 'JEAN', '5 rue de la Poste', '23200', '05-55-12-65-45', '', 'Cardiologue'),
('m002', 'DURAND', 'PAUL', '34 boulevard Malherbes', '13015', '04-91-67-45-98', '', 'Généraliste'),
('m003', 'JACQUES', 'ERIC', '45 rue des Tilleuls', '13025', '04-42-65-43-89', '', 'Rhumatologue'),
('m004', 'LE GUEN', 'HENRI', '6 rue de la Paix', '44000', '02-40-88-77-76', '', 'Généraliste'),
('m005', 'PAVERNE', 'PATRICK', '89 avenue Jean Jaures', '13015', '04-91-88-54-89', '', 'Dermatologue'),
('m006', 'LEMALIN', 'EMILE', '67 rue de la Mairie', '45000', '02-38-67-98-22', '', 'Neurologue'),
('m007', 'DECHAVANNE', 'LAURENT', '7 impasse de l Etang', '93210', '01-48-67-55-44', '', 'Pédiatrie'),
('m008', 'KAMELIN', 'JACQUES', '9 rue des Mimosas', '23120', '05-55-98-67-44', '', 'Psychiatrie'),
('m009', 'TULAN', 'PIERRE', '4 avenue Mendes France', '13015', '04-91-56-34-99', '', 'Allergologue'),
('m010', 'BANIZE', 'MARIE', '27 rue des Fleurs', '75019', '01-44-33-56-21', '', 'Gériatrie'),
('m011', 'DEVE', 'ANNIE', '8 rue des Platanes', '46000', '05-65-78-76-77', '', 'Généraliste'),
('m012', 'CLEMENCEAU', 'MARC', '6 rue Nationale', '46512', '05-61-56-29-35', '', 'Rhumatologue'),
('m013', 'BELLENOS', 'MICHEL', '89 rue des Embruns', '45000', '02-38-99-76-34', '', 'Neurologue'),
('m014', 'FUMEL', 'ANNE-MARIE', '8 rue des écoles', '44000', '02-40-78-43-22', '', 'Généraliste'),
('m015', 'GARDES', 'JEAN-LOUIS', '3 rue du 4 Septembre', '23100', '05-55-78-23-12', '', 'Cardiologue'),
('m016', 'GUYOT', 'BENOIT', '5 rue de la Mairie', '46512', '04-50-67-34-22', '', 'Allergologue'),
('m017', 'WILSON', 'YANN', '34 rue Paul Eluard', '93210', '01-48-77-98-34', '', 'Généraliste'),
('m018', 'MENJOUE', 'GERARD', '21 rue Jules Rimet', '93210', '01-55-76-43-54', '', 'Pédiatrie'),
('m019', 'TRANSCEN', 'JEAN', '12 rue des Pinsons', '44000', '02-40-78-45-66', '', 'Généraliste'),
('m020', 'LAGADEC', 'FREDERIQUE', '67 rue des Chênes', '13025', '02-48-54-53-44', '', 'Dermatologue');

-- --------------------------------------------------------

--
-- Structure de la table `MEDICAMENT`
--

CREATE TABLE IF NOT EXISTS `MEDICAMENT` (
  `MED_DEPOTLEGAL` varchar(10) NOT NULL DEFAULT '',
  `MED_NOMCOMMERCIAL` varchar(25) DEFAULT NULL,
  `MED_COMPOSITION` varchar(255) DEFAULT NULL,
  `MED_EFFETS` varchar(255) DEFAULT NULL,
  `MED_CONTREINDIC` varchar(255) DEFAULT NULL,
  `MED_PRIXECHANTILLON` float DEFAULT NULL,
  `FAM_CODE` varchar(3) DEFAULT NULL,
  `FAM_LIBELLE` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`MED_DEPOTLEGAL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `MEDICAMENT`
--

INSERT INTO `MEDICAMENT` (`MED_DEPOTLEGAL`, `MED_NOMCOMMERCIAL`, `MED_COMPOSITION`, `MED_EFFETS`, `MED_CONTREINDIC`, `MED_PRIXECHANTILLON`, `FAM_CODE`, `FAM_LIBELLE`) VALUES
('3MYC7', 'TRIMYCINE', 'Triamcinolone (acétonide) + Néomycine + Nystatine', 'Ce médicament est un corticoïde à  activité forte ou très forte associé à  un antibiotique et un antifongique, utilisé en application locale dans certaines atteintes cutanées surinfectées.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants, d''infections de la peau ou de parasitisme non traités, d''acné. Ne pas appliquer sur une plaie, ni sous un pansement occlusif.', NULL, 'CRT', 'Corticoïde, antibiotique et antifongique à  usage local'),
('ADIMOL9', 'ADIMOL', 'Amoxicilline + Acide clavulanique', 'Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux pénicillines ou aux céphalosporines.', NULL, 'ABP', 'Antibiotique de la famille des béta-lactamines (pénicilline A)'),
('AMOPIL7', 'AMOPIL', 'Amoxicilline', 'Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux pénicillines. Il doit être administré avec prudence en cas d''allergie aux céphalosporines.', NULL, 'ABP', 'Antibiotique de la famille des béta-lactamines (pénicilline A)'),
('AMOX45', 'AMOXAR', 'Amoxicilline', 'Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques.', 'La prise de ce médicament peut rendre positifs les tests de dépistage du dopage.', NULL, 'ABP', 'Antibiotique de la famille des béta-lactamines (pénicilline A)'),
('AMOXIG12', 'AMOXI Gé', 'Amoxicilline', 'Ce médicament, plus puissant que les pénicillines simples, est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux pénicillines. Il doit être administré avec prudence en cas d''allergie aux céphalosporines.', NULL, 'ABP', 'Antibiotique de la famille des béta-lactamines (pénicilline A)'),
('APATOUX22', 'APATOUX Vitamine C', 'Tyrothricine + Tétracaïne + Acide ascorbique (Vitamine C)', 'Ce médicament est utilisé pour traiter les affections de la bouche et de la gorge.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants, en cas de phénylcétonurie et chez l''enfant de moins de 6 ans.', NULL, 'ALO', 'Antibiotique local (ORL)'),
('BACTIG10', 'BACTIGEL', 'Erythromycine', 'Ce médicament est utilisé en application locale pour traiter l''acné et les infections cutanées bactériennes associées.', 'Ce médicament est contre-indiqué en cas d''allergie aux antibiotiques de la famille des macrolides ou des lincosanides.', NULL, 'ABC', 'Antibiotique antiacnéique local'),
('BACTIV13', 'BACTIVIL', 'Erythromycine', 'Ce médicament est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux macrolides (dont le chef de file est l''érythromycine).', NULL, 'AFM', 'Antibiotique de la famille des macrolides'),
('BITALV', 'BIVALIC', 'Dextropropoxyphène + Paracétamol', 'Ce médicament est utilisé pour traiter les douleurs d''intensité modérée ou intense.', 'Ce médicament est contre-indiqué en cas d''allergie aux médicaments de cette famille, d''insuffisance hépatique ou d''insuffisance rénale.', NULL, 'AAA', 'Antalgiques antipyrétiques en association'),
('CARTION6', 'CARTION', 'Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol', 'Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre.', 'Ce médicament est contre-indiqué en cas de troubles de la coagulation (tendances aux hémorragies), d''ulcère gastroduodénal, maladies graves du foie.', NULL, 'AAA', 'Antalgiques antipyrétiques en association'),
('CLAZER6', 'CLAZER', 'Clarithromycine', 'Ce médicament est utilisé pour traiter des infections bactériennes spécifiques. Il est également utilisé dans le traitement de l''ulcère gastro-duodénal, en association avec d''autres médicaments.', 'Ce médicament est contre-indiqué en cas d''allergie aux macrolides (dont le chef de file est l''érythromycine).', NULL, 'AFM', 'Antibiotique de la famille des macrolides'),
('DEPRIL9', 'DEPRAMIL', 'Clomipramine', 'Ce médicament est utilisé pour traiter les épisodes dépressifs sévères, certaines douleurs rebelles, les troubles obsessionnels compulsifs et certaines énurésies chez l''enfant.', 'Ce médicament est contre-indiqué en cas de glaucome ou d''adénome de la prostate, d''infarctus récent, ou si vous avez reà§u un traitement par IMAO durant les 2 semaines précédentes ou en cas d''allergie aux antidépresseurs imipraminiques.', NULL, 'AIM', 'Antidépresseur imipraminique (tricyclique)'),
('DIMIRTAM6', 'DIMIRTAM', 'Mirtazapine', 'Ce médicament est utilisé pour traiter les épisodes dépressifs sévères.', 'La prise de ce produit est contre-indiquée en cas de d''allergie à  l''un des constituants.', NULL, 'AAC', 'Antidépresseur d''action centrale'),
('DOLRIL7', 'DOLORIL', 'Acide acétylsalicylique (aspirine) + Acide ascorbique (Vitamine C) + Paracétamol', 'Ce médicament est utilisé dans le traitement symptomatique de la douleur ou de la fièvre.', 'Ce médicament est contre-indiqué en cas d''allergie au paracétamol ou aux salicylates.', NULL, 'AAA', 'Antalgiques antipyrétiques en association'),
('DORNOM8', 'NORMADOR', 'Doxylamine', 'Ce médicament est utilisé pour traiter l''insomnie chez l''adulte.', 'Ce médicament est contre-indiqué en cas de glaucome, de certains troubles urinaires (rétention urinaire) et chez l''enfant de moins de 15 ans.', NULL, 'HYP', 'Hypnotique antihistaminique'),
('EQUILARX6', 'EQUILAR', 'Méclozine', 'Ce médicament est utilisé pour traiter les vertiges et pour prévenir le mal des transports.', 'Ce médicament ne doit pas être utilisé en cas d''allergie au produit, en cas de glaucome ou de rétention urinaire.', NULL, 'AAH', 'Antivertigineux antihistaminique H1'),
('EVILR7', 'EVEILLOR', 'Adrafinil', 'Ce médicament est utilisé pour traiter les troubles de la vigilance et certains symptomes neurologiques chez le sujet agé.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants.', NULL, 'PSA', 'Psychostimulant, antiasthénique'),
('INSXT5', 'INSECTIL', 'Diphénydramine', 'Ce médicament est utilisé en application locale sur les piqûres d''insecte et l''urticaire.', 'Ce médicament est contre-indiqué en cas d''allergie aux antihistaminiques.', NULL, 'AH', 'Antihistaminique H1 local'),
('JOVAI8', 'JOVENIL', 'Josamycine', 'Ce médicament est utilisé pour traiter des infections bactériennes spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie aux macrolides (dont le chef de file est l''érythromycine).', NULL, 'AFM', 'Antibiotique de la famille des macrolides'),
('LIDOXY23', 'LIDOXYTRACINE', 'Oxytétracycline +Lidocaïne', 'Ce médicament est utilisé en injection intramusculaire pour traiter certaines infections spécifiques.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants. Il ne doit pas être associé aux rétinoïdes.', NULL, 'AFC', 'Antibiotique de la famille des cyclines'),
('LITHOR12', 'LITHORINE', 'Lithium', 'Ce médicament est indiqué dans la prévention des psychoses maniaco-dépressives ou pour traiter les états maniaques.', 'Ce médicament ne doit pas être utilisé si vous êtes allergique au lithium. Avant de prendre ce traitement, signalez à  votre médecin traitant si vous souffrez d''insuffisance rénale, ou si vous avez un régime sans sel.', NULL, 'AP', 'Antipsychotique normothymique'),
('PARMOL16', 'PARMOCODEINE', 'Codéine + Paracétamol', 'Ce médicament est utilisé pour le traitement des douleurs lorsque des antalgiques simples ne sont pas assez efficaces.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants, chez l''enfant de moins de 15 Kg, en cas d''insuffisance hépatique ou respiratoire, d''asthme, de phénylcétonurie et chez la femme qui allaite.', NULL, 'AA', 'Antalgiques en association'),
('PHYSOI8', 'PHYSICOR', 'Sulbutiamine', 'Ce médicament est utilisé pour traiter les baisses d''activité physique ou psychique, souvent dans un contexte de dépression.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants.', NULL, 'PSA', 'Psychostimulant, antiasthénique'),
('PIRIZ8', 'PIRIZAN', 'Pyrazinamide', 'Ce médicament est utilisé, en association à  d''autres antibiotiques, pour traiter la tuberculose.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants, d''insuffisance rénale ou hépatique, d''hyperuricémie ou de porphyrie.', NULL, 'ABA', 'Antibiotique antituberculeux'),
('POMDI20', 'POMADINE', 'Bacitracine', 'Ce médicament est utilisé pour traiter les infections oculaires de la surface de l''oeil.', 'Ce médicament est contre-indiqué en cas d''allergie aux antibiotiques appliqués localement.', NULL, 'AO', 'Antibiotique ophtalmique'),
('TROXT21', 'TROXADET', 'Paroxétine', 'Ce médicament est utilisé pour traiter la dépression et les troubles obsessionnels compulsifs. Il peut également être utilisé en prévention des crises de panique avec ou sans agoraphobie.', 'Ce médicament est contre-indiqué en cas d''allergie au produit.', NULL, 'AIN', 'Antidépresseur inhibiteur sélectif de la recapture de la sérotonine'),
('TXISOL22', 'TOUXISOL Vitamine C', 'Tyrothricine + Acide ascorbique (Vitamine C)', 'Ce médicament est utilisé pour traiter les affections de la bouche et de la gorge.', 'Ce médicament est contre-indiqué en cas d''allergie à  l''un des constituants et chez l''enfant de moins de 6 ans.', NULL, 'ALO', 'Antibiotique local (ORL)'),
('URIEG6', 'URIREGUL', 'Fosfomycine trométamol', 'Ce médicament est utilisé pour traiter les infections urinaires simples chez la femme de moins de 65 ans.', 'La prise de ce médicament est contre-indiquée en cas d''allergie à  l''un des constituants et d''insuffisance rénale.', NULL, 'AUM', 'Antibiotique urinaire minute');

-- --------------------------------------------------------

--
-- Structure de la table `VISITE`
--

CREATE TABLE IF NOT EXISTS `VISITE` (
  `REFERENCE` char(5) NOT NULL,
  `DATEVISITE` char(10) DEFAULT NULL,
  `COMMENTAIRE` varchar(100) DEFAULT NULL,
  `MATRICULE` char(4) NOT NULL,
  `CODEMED` char(4) NOT NULL,
  PRIMARY KEY (`REFERENCE`),
  KEY `MATRICULE` (`MATRICULE`),
  KEY `CODEMED` (`CODEMED`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `VISITE`
--

INSERT INTO `VISITE` (`REFERENCE`, `DATEVISITE`, `COMMENTAIRE`, `MATRICULE`, `CODEMED`) VALUES
('v0001', '2002-01-20', '', 'b59', 'm001'),
('v0002', '2000-10-01', '', 'e39', 'm007'),
('v0003', '2001-05-10', '', 'e49', 'm005'),
('v0004', '2000-07-11', '', 'd13', 'm004'),
('v0005', '2006-12-22', '', 'c54', 'm008'),
('v0006', '2005-12-10', '', 'e22', 'm010'),
('v0007', '2006-02-03', '', 'e22', 'm014'),
('v0008', '2008-10-27', '', 'b34', 'm020'),
('v0009', '2001-06-18', '', 'e49', 'm005'),
('v0010', '2007-01-20', '', 'c54', 'm008'),
('v0011', '2002-02-13', '', 'f21', 'm011'),
('v0012', '2002-10-01', '', 'f39', 'm015'),
('v0013', '2002-03-10', '', 'f21', 'm012'),
('v0014', '2008-11-03', '', 'b34', 'm020'),
('v0015', '2006-04-20', '', 'e22', 'm010'),
('v0016', '2003-10-14', '', 'f21', 'm001'),
('v0017', '2002-07-04', '', 'e49', 'm005'),
('v0018', '2009-12-11', '', 'b28', 'm002'),
('v0019', '2001-01-10', '', 'e39', 'm007'),
('v0020', '2010-01-05', '', 'b28', 'm011'),
('v0021', '2000-09-03', '', 'd13', 'm004'),
('v0022', '2010-02-05', '', 'b28', 'm002'),
('v0023', '2002-06-02', '', 'b13', 'm009');

-- --------------------------------------------------------

--
-- Structure de la table `VISITEUR`
--

CREATE TABLE IF NOT EXISTS `VISITEUR` (
  `MATRICULE` char(4) NOT NULL,
  `NOM` varchar(50) DEFAULT NULL,
  `PRENOM` varchar(50) DEFAULT NULL,
  `LOGIN` varchar(50) DEFAULT NULL,
  `MDP` varchar(20) DEFAULT NULL,
  `ADRESSE` varchar(50) DEFAULT NULL,
  `CODEPOSTAL` varchar(5) NOT NULL,
  `DATEENTREE` datetime DEFAULT NULL,
  `CODEUNIT` char(4) NOT NULL,
  `NOMUNIT` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MATRICULE`),
  KEY `CODEPOSTAL` (`CODEPOSTAL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `STOCKER` (
	`QTESTOCK` int NOT NULL ,
	`MATRICULE` char(4) NOT NULL,
    `DEPOTLEGAL` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `STOCKER`
  ADD CONSTRAINT `STOCKER_ibfk_1` FOREIGN KEY (`MATRICULE`) REFERENCES `VISITEUR` (`MATRICULE`),
  ADD CONSTRAINT `STOCKER_ibfk_2` FOREIGN KEY (`DEPOTLEGAL`) REFERENCES `MEDICAMENT` (`MED_DEPOTLEGAL`)
;

CREATE TABLE IF NOT EXISTS `OFFRIR` (
    `QTEOFFERTE` int NOT NULL,
    `REFERENCE` char(5) NOT NULL,
    `DEPOTLEGAL` char(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `OFFRIR`
  ADD CONSTRAINT `OFFRIR_ibfk_1` FOREIGN KEY (`REFERENCE`) REFERENCES `VISITE` (`REFERENCE`),
  ADD CONSTRAINT `OFFRIR_ibfk_2` FOREIGN KEY (`DEPOTLEGAL`) REFERENCES `MEDICAMENT` (`MED_DEPOTLEGAL`)
;
--
-- Contenu de la table `VISITEUR`
--

INSERT INTO `VISITEUR` (`MATRICULE`, `NOM`, `PRENOM`, `LOGIN`, `MDP`, `ADRESSE`, `CODEPOSTAL`, `DATEENTREE`, `CODEUNIT`, `NOMUNIT`) VALUES
('a131', 'Villechalane', 'Louis', 'lvillachane', 'jux7g', '8 rue des Charmes', '46000', '2005-12-21', 'SW', 'SWISS'),
('a17', 'Andre', 'David', 'dandre', 'oppg5', '1 rue Petit', '46200', '1998-11-23', 'BO', 'BOURDIN'),
('a55', 'Bedos', 'Christian', 'cbedos', 'gmhxd', '1 rue Peranud', '46250', '1995-01-12', 'BO', 'BOURDIN'),
('a93', 'Tusseau', 'Louis', 'ltusseau', 'ktp3s', '22 rue des Ternes', '46123', '2000-05-01', 'SW', 'SWISS'),
('b13', 'Bentot', 'Pascal', 'pbentot', 'doyw1', '11 allée des Cerises', '46512', '1992-07-09', 'BO', 'BOURDIN'),
('b16', 'Bioret', 'Luc', 'lbioret', 'hrjfs', '1 Avenue gambetta', '46000', '1998-05-11', 'SW', 'SWISS'),
('b19', 'Bunisset', 'Francis', 'fbunisset', '4vbnd', '10 rue des Perles', '93100', '1987-10-21', 'BO', 'BOURDIN'),
('b25', 'Bunisset', 'Denise', 'dbunisset', 's1y1r', '23 rue Manin', '75019', '2010-12-05', 'SW', 'SWISS'),
('b28', 'Cacheux', 'Bernard', 'bcacheux', 'uf7r3', '114 rue Blanche', '75017', '2009-11-12', 'SW', 'SWISS'),
('b34', 'Cadic', 'Eric', 'ecadic', '6u8dc', '123 avenue de la République', '75011', '2008-09-23', 'BO', 'BOURDIN'),
('b4', 'Charoze', 'Catherine', 'ccharoze', 'u817o', '100 rue Petit', '75019', '2005-11-12', 'SW', 'SWISS'),
('b50', 'Clepkens', 'Christophe', 'cclepkens', 'bw1us', '12 allée des Anges', '93230', '2003-08-11', 'BO', 'BOURDIN'),
('b59', 'Cottin', 'Vincenne', 'vcottin', '2hoh9', '36 rue Des Roches', '93100', '2001-11-18', 'SW', 'SWISS'),
('c14', 'Daburon', 'François', 'fdaburon', '7oqpv', '13 rue de Chanzy', '94000', '2002-02-11', 'SW', 'SWISS'),
('c3', 'De', 'Philippe', 'pde', 'gk9kx', '13 rue Barthes', '94000', '2010-12-14', 'BO', 'BOURDIN'),
('c54', 'Debelle', 'Michel', 'mdebelle', 'od5rt', '181 avenue Barbusse', '93210', '2006-11-23', 'BO', 'BOURDIN'),
('d13', 'Debelle', 'Jeanne', 'jdebelle', 'nvwqq', '134 allée des Joncs', '44000', '2000-05-11', 'SW', 'SWISS'),
('d51', 'Debroise', 'Michel', 'mdebroise', 'sghkb', '2 Bld Jourdain', '44000', '2001-04-17', 'BO', 'BOURDIN'),
('e22', 'Desmarquest', 'Nathalie', 'ndesmarquest', 'f1fob', '14 Place d Arc', '45000', '2005-11-12', 'SW', 'SWISS'),
('e24', 'Desnost', 'Pierre', 'pdesnost', '4k2o5', '16 avenue des Cèdres', '23200', '2001-02-05', 'BO', 'BOURDIN'),
('e39', 'Dudouit', 'Frédéric', 'fdudouit', '44im8', '18 rue de l église', '23120', '2000-08-01', 'SW', 'SWISS'),
('e49', 'Duncombe', 'Claude', 'cduncombe', 'qf77j', '19 rue de la tour', '23100', '1987-10-10', 'SW', 'SWISS'),
('e5', 'Enault-Pascreau', 'Céline', 'cenault', 'y2qdu', '25 place de la gare', '23200', '1995-09-01', 'BO', 'BOURDIN'),
('e52', 'Eynde', 'Valérie', 'veynde', 'i7sn3', '3 Grand Place', '13015', '1999-11-01', 'BO', 'BOURDIN'),
('f21', 'Finck', 'Jacques', 'jfinck', 'mpb3t', '10 avenue du Prado', '13012', '2001-11-10', 'SW', 'SWISS'),
('f39', 'Frémont', 'Fernande', 'ffremont', 'xs5tq', '4 route de la mer', '13012', '1998-10-01', 'BO', 'BOURDIN'),
('f4', 'Gest', 'Alain', 'agest', 'dywvt', '30 avenue de la mer', '13025', '1985-11-01', 'SW', 'SWISS');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `VISITE`
--
ALTER TABLE `VISITE`
  ADD CONSTRAINT `VISITE_ibfk_1` FOREIGN KEY (`MATRICULE`) REFERENCES `VISITEUR` (`MATRICULE`),
  ADD CONSTRAINT `VISITE_ibfk_2` FOREIGN KEY (`CODEMED`) REFERENCES `MEDECIN` (`CODEMED`);

--
-- Contraintes pour la table `VISITEUR`
--
ALTER TABLE `MEDECIN`
	ADD CONSTRAINT `MEDECIN_ibfk_1` FOREIGN KEY (`CODEPOSTAL`) REFERENCES `LOCALITE` (`CODEPOSTAL`)
;
ALTER TABLE `VISITEUR`
  ADD CONSTRAINT `VISITEUR_ibfk_1` FOREIGN KEY (`CODEPOSTAL`) REFERENCES `LOCALITE` (`CODEPOSTAL`)
;
