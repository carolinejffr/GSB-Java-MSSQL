/*
 * Créé le 22 févr. 2015
 *
 */
package gsb.modele.dao;

import gsb.modele.Localite;

import java.sql.ResultSet;


/**
 * @author Caroline Jaffré
 */
public class LocaliteDao {
	
	public static Localite rechercher(String codeLocalite){
		Localite uneLocalite=null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC RechercherLocalite @CodePostal = "+codeLocalite);
		try {
			if (reqSelection.next()) {
				uneLocalite = new Localite(reqSelection.getString(1), reqSelection.getString(2));	
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête appelant la procédure RechercherLocalite");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return uneLocalite;
	}

}
