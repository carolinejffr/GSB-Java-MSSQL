package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Stocker;
import gsb.modele.Visiteur;

/*
 * @author Caroline Jaffré
 */
public class StockDao 
{
    public static Stocker rechercher(String matricule, String depotLegal)
    {
        Stocker stock = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC StockRecherche @Matricule ='" + matricule +"', @DepotLegal = '" + depotLegal + "'");
		try {
			if (reqSelection.next()) 
            {
                Visiteur unVisiteur = VisiteurDao.rechercher(matricule);
                Medicament unMedicament = MedicamentDao.rechercher(depotLegal);

				stock = new Stocker(reqSelection.getInt(1), unVisiteur, unMedicament);	
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour EXEC StockRecherche @Matricule ='" + matricule +"', @DepotLegal = '" + depotLegal + "'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return stock;
    }
	 public static ArrayList<Stocker> rechercher(String matricule)
	 {
		ArrayList<Stocker> listeStock = new ArrayList<Stocker>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC StockRechercheListe @Matricule = '"+matricule+"'");
		try {
			while (reqSelection.next()) 
            {
                Visiteur unVisiteur = VisiteurDao.rechercher(matricule);
                Medicament unMedicament = MedicamentDao.rechercher(reqSelection.getString(3));

				Stocker unStock = new Stocker(reqSelection.getInt(1), unVisiteur, unMedicament);
				listeStock.add(unStock);
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - StockRechercheListe @Matricule = '"+matricule+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return listeStock;
	 }
}
