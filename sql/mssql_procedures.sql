-- ======================================
-- Procédures Stockées pour le projet GSB
-- Caroline Jaffré 2SIO
-- ======================================
-- Drop des procédures
DROP PROCEDURE IF EXISTS RechercherLocalite
DROP PROCEDURE IF EXISTS LesCodesMedecins
DROP PROCEDURE IF EXISTS RechercherMedecin
DROP PROCEDURE IF EXISTS RechercherMedicamentCode
DROP PROCEDURE IF EXISTS RechercherMedicamentFamille
DROP PROCEDURE IF EXISTS CollectionMedicaments
DROP PROCEDURE IF EXISTS StockRecherche
DROP PROCEDURE IF EXISTS StockRechercheListe
DROP PROCEDURE IF EXISTS RechercherVisite
DROP PROCEDURE IF EXISTS LesVisites
DROP PROCEDURE IF EXISTS VisiteAjout
DROP PROCEDURE IF EXISTS LesVisitesReferences
DROP PROCEDURE IF EXISTS VisiteSupprimer
DROP PROCEDURE IF EXISTS RechercherVisiteur
DROP PROCEDURE IF EXISTS LesVisiteurs
DROP PROCEDURE IF EXISTS RechercherCodePostal
DROP PROCEDURE IF EXISTS AjouterLocalite
DROP PROCEDURE IF EXISTS AjoutStock
DROP PROCEDURE IF EXISTS UpdateStock
DROP PROCEDURE IF EXISTS ConnexionTest
DROP PROCEDURE IF EXISTS MedecinInsert
DROP PROCEDURE IF EXISTS MedicamentInsert
DROP PROCEDURE IF EXISTS VerifMatricule
DROP PROCEDURE IF EXISTS InsertVisiteur
GO
-- LocaliteDao
CREATE PROCEDURE RechercherLocalite
	@CodePostal VARCHAR(5)
AS
BEGIN
    select * from LOCALITE where CODEPOSTAL= @CodePostal
END
GO

-- MedecinDao
CREATE PROCEDURE LesCodesMedecins
AS
BEGIN
	select CODEMED from MEDECIN
END
GO

CREATE PROCEDURE RechercherMedecin
	@CodeMedecin VARCHAR(4)
AS
BEGIN
	select * from MEDECIN where CODEMED = @CodeMedecin
END
GO

-- MedicamentDao
CREATE PROCEDURE RechercherMedicamentCode
	@CodeMedicament VARCHAR(10)
AS
BEGIN
	select * from MEDICAMENT where MED_DEPOTLEGAL = @CodeMedicament
END
GO

CREATE PROCEDURE RechercherMedicamentFamille
	@Famille VARCHAR(3)
AS
BEGIN
	SELECT MED_DEPOTLEGAL FROM MEDICAMENT where FAM_CODE = @Famille ORDER BY MEDICAMENT.MED_DEPOTLEGAL ASC
END
GO

CREATE PROCEDURE CollectionMedicaments
AS
BEGIN
	SELECT MED_DEPOTLEGAL FROM MEDICAMENT ORDER BY MEDICAMENT.MED_DEPOTLEGAL ASC
END
GO

-- StockDao
CREATE PROCEDURE StockRecherche
	@Matricule CHAR(4), @DepotLegal VARCHAR(10)
AS
BEGIN
	select * from STOCKER where MATRICULE = @Matricule AND DEPOTLEGAL = @DepotLegal 
END
GO

CREATE PROCEDURE StockRechercheListe
	@Matricule CHAR(4)
AS
BEGIN
	select * from STOCKER where MATRICULE = @Matricule
END
GO

-- VisiteDao
CREATE PROCEDURE RechercherVisite
	@Reference CHAR(5)
AS
BEGIN
	select * from VISITE where REFERENCE = @Reference
END
GO

CREATE PROCEDURE LesVisites
AS
BEGIN
	select * from VISITE
END
GO

CREATE PROCEDURE VisiteAjout
	@Reference CHAR(5), @Date CHAR(10), @Commentaire VARCHAR(100), @Matricule CHAR(4), @CodeMed CHAR(4)
AS
BEGIN
	INSERT INTO VISITE VALUES(@Reference, @Date, @Commentaire, @Matricule, @CodeMed)
END
GO

CREATE PROCEDURE LesVisitesReferences
AS
BEGIN
	select REFERENCE from VISITE
END
GO

CREATE PROCEDURE VisiteSupprimer
	@Reference CHAR(5)
AS
BEGIN
	DELETE FROM VISITE WHERE REFERENCE = @Reference
END
GO

-- VisiteurDao
CREATE PROCEDURE RechercherVisiteur
	@Matricule CHAR(4)
AS
BEGIN
	select * from VISITEUR where MATRICULE = @Matricule
END
GO

CREATE PROCEDURE LesVisiteurs
AS
BEGIN
	SELECT MATRICULE FROM VISITEUR ORDER BY VISITEUR.MATRICULE ASC
END
GO

-- LocaliteService
CREATE PROCEDURE RechercherCodePostal
	@Ville VARCHAR(50)
AS
BEGIN
	SELECT CODEPOSTAL FROM LOCALITE WHERE VILLE = @Ville
END
GO

CREATE PROCEDURE AjouterLocalite
	@CodePostal VARCHAR(5), @NomVille VARCHAR(50)
AS
BEGIN
	INSERT INTO LOCALITE (CODEPOSTAL, VILLE) VALUES (@CodePostal, @NomVille)
END
GO

-- StockService
CREATE PROCEDURE AjoutStock
	@Quantite INT, @Matricule VARCHAR(5), @DepotLegal VARCHAR(10)
AS
BEGIN
	INSERT INTO STOCKER (QTESTOCK, MATRICULE, DEPOTLEGAL) VALUES (@Quantite, @Matricule, @DepotLegal)
END
GO

CREATE PROCEDURE UpdateStock
	@Quantite INT, @Matricule CHAR(4), @DepotLegal VARCHAR(10)
AS
BEGIN
	UPDATE STOCKER SET QTESTOCK = @Quantite WHERE Matricule = @Matricule AND DEPOTLEGAL = @DepotLegal
END
GO

-- ConnexionTest
CREATE PROCEDURE ConnexionTest
AS
BEGIN
	select * from LOCALITE
END
GO

-- JIFMedecin
CREATE PROCEDURE MedecinInsert
	@CodeMed CHAR(4), @Nom VARCHAR(50), @Prenom VARCHAR(50), @Adresse VARCHAR(50), @CodePostal VARCHAR(5), @Telephone VARCHAR(15), @Potentiel VARCHAR(50), @Specialite VARCHAR(50)
AS
BEGIN
	INSERT INTO medecin (CODEMED, NOM, PRENOM, ADRESSE, CODEPOSTAL, TELEPHONE, POTENTIEL, SPECIALITE) VALUES (@CodeMed, @Nom, @Prenom, @Adresse, @CodePostal, @Telephone, @Potentiel, @Specialite)
END
GO

-- JIFMedicament
CREATE PROCEDURE MedicamentInsert
	@DepotLegal VARCHAR(10), @Nom VARCHAR(25), @Compo VARCHAR(255), @Effets VARCHAR(255), @ContreIndic VARCHAR (255), @Prix FLOAT, @FamCode VARCHAR(3), @FamLib VARCHAR(80)
AS
BEGIN
	INSERT INTO medicament (MED_DEPOTLEGAL, MED_NOMCOMMERCIAL, MED_COMPOSITION, MED_EFFETS, MED_CONTREINDIC, MED_PRIXECHANTILLON, FAM_CODE, FAM_LIBELLE) VALUES (@DepotLegal, @Nom, @Compo, @Effets, @ContreIndic, @Prix, @FamCode, @FamLib)
END
GO

-- JIFVisiteur
CREATE PROCEDURE VerifMatricule
	@Matricule CHAR(4)
AS
BEGIN
	SELECT MATRICULE FROM VISITEUR WHERE MATRICULE = @Matricule
END
GO

CREATE PROCEDURE InsertVisiteur
	@Matricule CHAR(4), @Nom VARCHAR(50), @Prenom VARCHAR(50), @Login VARCHAR(50), @Mdp VARCHAR(20), @Adresse VARCHAR(50), @CodePostal VARCHAR(5), @DateEntree DATETIME, @CodeUnit CHAR(4), @NomUnit VARCHAR(50)
AS
BEGIN
	INSERT INTO VISITEUR (MATRICULE, NOM, PRENOM, LOGIN, MDP, ADRESSE, CODEPOSTAL, DATEENTREE, CODEUNIT, NOMUNIT) VALUES (@Matricule, @Nom, @Prenom, @Login, @Mdp, @Adresse, @CodePostal, @DateEntree, @CodeUnit, @NomUnit)
END
GO