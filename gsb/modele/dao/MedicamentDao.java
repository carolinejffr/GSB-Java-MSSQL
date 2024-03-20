/*
 * @author Caroline Jaffré
 *
 */
package gsb.modele.dao;

import gsb.modele.Medicament;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * @author Caroline Jaffré
 */
public class MedicamentDao {
	
	public static Medicament rechercher(String codeMedicament){
		Medicament unMedicament=null; //on déclare la variable
		ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC RechercherMedicamentCode @CodeMedicament = '" + codeMedicament + "'");//on se connecte a la bdd et on recherche un médicament avec un code spécifique
		try {
			if (reqSelection.next()) {
				unMedicament = new Medicament(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5), reqSelection.getFloat(6), reqSelection.getString(7), reqSelection.getString(8));	
			};
		}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - RechercherMedicamentCode ='"+codeMedicament+"'");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		
		return unMedicament;
	}

	public static ArrayList<Medicament> retournerMedicamentsParFamille(String codeFamille){
		ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC RechercherMedicamentFamille @Famille = '" + codeFamille + "'");
		try{
		while (reqSelection.next()) {
			String depotLegal = reqSelection.getString(1);
		    collectionDesMedicaments.add(MedicamentDao.rechercher(depotLegal));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesMedecins()");
		}
		return collectionDesMedicaments;
	}

	public static ArrayList<Medicament> retournerCollectionDesMedicaments(){
		ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC CollectionMedicaments");
		try{
		while (reqSelection.next()) {
			String depotLegal = reqSelection.getString(1);
		    collectionDesMedicaments.add(MedicamentDao.rechercher(depotLegal));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesMedecins()");
		}
		return collectionDesMedicaments;
	}

}
