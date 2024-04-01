-- ======================================
-- Règles de gestion du projet GSB
-- Caroline Jaffré 2SIO
-- Règles choisies : RG4 et RG3
-- ======================================
-- RG4 Partie Visites : 
-- Ce qui est important c'est la périodicité des visites puisque l'on voit les praticiens tous les 6 à 8 mois. 
-- Ça nécessite de bien tenir à jour les dates de visite pour chaque praticien.
-- ======================================
-- On commence par modifier la table MEDECIN pour ajouter un champ qui tient compte de la dernière date de visite.
ALTER TABLE MEDECIN ADD DERNIEREVISITE char(10) DEFAULT NULL;
GO
-- Il faut mettre en place un trigger qui actualise cette nouvelle colonne à la création d'une nouvelle visite.
CREATE TRIGGER MajDerniereVisite 
ON VISITE
AFTER INSERT
AS
BEGIN
    UPDATE MEDECIN
    SET DERNIEREVISITE = i.DATEVISITE
    FROM MEDECIN m
    INNER JOIN inserted i ON m.CODEMED = i.CODEMED;
END;
GO
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
