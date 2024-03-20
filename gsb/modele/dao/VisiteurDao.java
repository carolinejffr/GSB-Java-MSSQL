package gsb.modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import gsb.modele.Localite;
import gsb.modele.Visiteur;
/*
 * @author Caroline Jaffré
 */
public class VisiteurDao {

    public static Visiteur rechercher(String matriculeVisiteur){
		Visiteur unVisiteur = null;
        Localite uneLocalite= null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC RechercherVisiteur @Matricule ='"+ matriculeVisiteur +"'");
		try {
			if (reqSelection.next()) {
                uneLocalite = LocaliteDao.rechercher(reqSelection.getString(7));
                String telephone = null;
                int prime = 0;
				unVisiteur = new Visiteur(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5), reqSelection.getString(6), uneLocalite, telephone, reqSelection.getString(8), prime, reqSelection.getString(9), reqSelection.getString(10));	
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour EXEC RechercherVisiteur @Matricule ='"+ matriculeVisiteur +"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return unVisiteur;
	}
    public static ArrayList<Visiteur> retournerCollectionDesVisiteurs()
    {
		ArrayList<Visiteur> collectionDesVisiteurs = new ArrayList<Visiteur>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC LesVisiteurs");
		try{
		while (reqSelection.next()) {
			String matricule = reqSelection.getString(1);
		    collectionDesVisiteurs.add(VisiteurDao.rechercher(matricule));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesVisiteurs()");
		}
		return collectionDesVisiteurs;
	}
		public static HashMap<String,Visiteur> retournerDictionnaireDesMedecins(){
			HashMap<String, Visiteur> diccoDesVisiteurs = new HashMap<String, Visiteur>();
			ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC LesVisiteurs");
			try{
				while (reqSelection.next()) {
						String matricule = reqSelection.getString(1);
						diccoDesVisiteurs.put(matricule, VisiteurDao.rechercher(matricule));
					}
				}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("erreur retournerDiccoDesMedecins()");
			}
			return diccoDesVisiteurs;
	}
}
