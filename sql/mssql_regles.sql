-- ======================================
-- Règles de gestion du projet GSB
-- Caroline Jaffré 2SIO
-- Règles choisies : RG3 et RG6
-- ======================================
-- RG3 Parties Visites :
-- : On doit connaître précisément les dates de visites faites par les collaborateurs pour produire des statistiques quotidiennes, mensuelles…
-- ======================================
-- D'abord, on va avoir besoin d'une nouvelle table
CREATE TABLE STATISTIQUES (
	MATRICULE  char(4) NOT NULL,
	NOM varchar(50) DEFAULT NULL,
	PRENOM  varchar(50) DEFAULT NULL,
	HEDBOMADAIRE INT DEFAULT NULL,
	MENSUEL INT DEFAULT NULL,
	PRIMARY KEY (MATRICULE)
);
GO
-- On peuple la table avec tous les matricules des visiteurs :
INSERT INTO STATISTIQUES(MATRICULE, NOM, PRENOM)
SELECT MATRICULE, NOM, PRENOM 
FROM VISITEUR ORDER BY MATRICULE ASC
GO
-- On a besoin d'un trigger qui ajoute un visiteur à la table statistique s'il est créé.
CREATE TRIGGER AjoutVisiteurStatistiques 
ON VISITEUR
AFTER INSERT
AS
BEGIN
	INSERT INTO STATISTIQUES(MATRICULE, NOM, PRENOM)
	SELECT MATRICULE, NOM, PRENOM 
	FROM inserted
END;
GO
-- Trigger qui compte les stats de la semaine
CREATE TRIGGER UpdateStatsHebdo
ON VISITE
AFTER INSERT
AS
BEGIN
    UPDATE S
    SET HEDBOMADAIRE = (
        SELECT COUNT(*)
        FROM VISITE V
        WHERE V.MATRICULE = S.MATRICULE
        AND V.DATEVISITE >= DATEADD(WEEK, -1, GETDATE())
    )
    FROM STATISTIQUES S
    JOIN inserted I ON S.MATRICULE = I.MATRICULE;
END;
GO
-- Trigger qui compte les stats du mois
CREATE TRIGGER UpdateStatsMensuel
ON VISITE
AFTER INSERT
AS
BEGIN
    UPDATE S
    SET MENSUEL = (
        SELECT COUNT(*)
        FROM VISITE V
        WHERE V.MATRICULE = S.MATRICULE
        AND V.DATEVISITE >= DATEADD(MONTH, -1, GETDATE())
    )
    FROM STATISTIQUES S
    JOIN inserted I ON S.MATRICULE = I.MATRICULE;
END;
GO
-- Procedure qui donne le podium selon la taille souhaitée
CREATE PROCEDURE GetPodium
	@taillePodium INT
AS
BEGIN
	SELECT TOP( @taillePodium) * FROM STATISTIQUES ORDER BY MENSUEL DESC
END
GO

-- ======================================
-- RG6 Parties Médecins :
-- : une fois par an il est nécessaire de purger la base de données. 
-- : A cette occasion les médecins qui ont pris leur retraite devront être archivés et les visites les concernant seront supprimées.
-- ======================================
-- On commence par ajouter une colomne RETRAITE pour savoir si le médecin est retraité ou non
ALTER TABLE MEDECIN ADD RETRAITE BIT NOT NULL DEFAULT 0
GO
-- On créé une procédure stockée pour mettre un médecin en retraite
CREATE PROCEDURE MettreEnRetraite
	@codeMed CHAR(4)
AS
BEGIN
	UPDATE MEDECIN
	SET RETRAITE = 1
	WHERE CODEMED = @codeMed
END
GO
-- On créé une autre procédure qui supprime tous les médecins retraités
CREATE PROCEDURE PurgeRetraite
AS
BEGIN
	-- suppression des visites associées
	DELETE FROM VISITE
	WHERE CODEMED IN (SELECT CODEMED FROM MEDECIN WHERE RETRAITE = 1);
	-- suppresion du médecin
	DELETE FROM MEDECIN
	WHERE RETRAITE = 1;
END
GO
-- On a besoin d'autres procédures pour l'interface de mise à la retraite
CREATE PROCEDURE ListeMedecinsActifs
AS
BEGIN
	SELECT CODEMED, NOM, PRENOM FROM MEDECIN
	WHERE RETRAITE = 0
END
GO

CREATE PROCEDURE ListeRetraites
AS
BEGIN
	SELECT CODEMED, NOM, PRENOM FROM MEDECIN
	WHERE RETRAITE = 1
END
GO