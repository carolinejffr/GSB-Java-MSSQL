package gsb.service;

import java.util.ArrayList;

import gsb.modele.Stocker;
import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.StockDao;

/**
 * @author Caroline Jaffré
 */
public class StockService 
{

    public static int ajoutStock(int quantite, String matricule, String depotLegal)
    {
        int codeRequete = 0;
        int reqMaj = ConnexionMySql.execReqMaj("EXEC AjoutStock @Quantite ='" + quantite + "', @Matricule = '" + matricule + "', @DepotLegal = '" + depotLegal + "') ");

		ConnexionMySql.fermerConnexionBd();
        if (reqMaj == 1)
        {
            codeRequete = 1;
            Stocker stock = StockDao.rechercher(matricule, depotLegal);
            if (stock != null)
            {
                codeRequete = 2;
            }
        }
        ConnexionMySql.fermerConnexionBd();
        return codeRequete;
    }
    public static Stocker rechercherStock(String matricule, String depotLegal)
    {
        Stocker stock = StockDao.rechercher(matricule, depotLegal);
		return stock;
    }
    public static int updateStock(Stocker unStock)
    {
        int codeRequete = 0;
        int reqMaj = ConnexionMySql.execReqMaj("EXEC UpdateStock @Quantite = " + unStock.getQteStock() + ", @Matricule = '" + unStock.getUnVisiteur().getMatricule() +"', @DepotLegal = '" + unStock.getUnMedicament().getDepotLegal() + "'");
        ConnexionMySql.fermerConnexionBd();
        if (reqMaj == 1)
        {
            codeRequete = 1;
            Stocker stock = StockDao.rechercher(unStock.getUnVisiteur().getMatricule(), unStock.getUnMedicament().getDepotLegal());
            if (stock != null)
            {
                codeRequete = 2;
            }
        }
        ConnexionMySql.fermerConnexionBd();
        return codeRequete;
    }
    public static ArrayList<Stocker> listeStockVisiteur(String unMatricule)
    {
        ArrayList<Stocker> laListe = StockDao.rechercher(unMatricule);

        return laListe;
    }
 }
    
