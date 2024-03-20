package gsb.service;

import java.sql.ResultSet;

import gsb.modele.Localite;
import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.LocaliteDao;
/*
 * Services liés à Localite
 * @author Caroline Jaffré
 */
public class LocaliteService {
    /*
     * Retourne un code postal à partir d'une ville
     * @return le code postal en String
     */
    public static String getCodePostal(String uneVille)
    {
        String leCodePostal = null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC RechercherCodePostal @Ville = '"+ uneVille+"'");
		try {
			if (reqSelection.next()) {
				leCodePostal = reqSelection.getString(1);	
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - RechercherCodePostal @Ville = '"+ uneVille+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return leCodePostal;
    }

    /*
     * Créé une nouvelle localité
     * @return code qui indique si la requête a réussi ou non.
     * 0 = échec de la requête
     * 1 = requête réussi mais la localité n'a pas été créée (normalement impossible si on est bien en root sous MySQL)
     * 2 = OK
     */
    public static int ajoutLocaliteBDD(String nomVille, String codePostal)
    {
        int codeRequete = 0;
        int reqMaj = ConnexionMySql.execReqMaj("EXEC AjouterLocalite @CodePostal = '" + codePostal + "', @NomVille = '" + nomVille + "'");

		ConnexionMySql.fermerConnexionBd();
        if (reqMaj == 1)
        {
            codeRequete = 1;
            Localite laLocalite = LocaliteDao.rechercher(codePostal);
            if (laLocalite != null)
            {
                codeRequete = 2;
            }
        }

        return codeRequete;
    }
    
    
}
