/*
 * Créé le 23 févr. 2015
 *
 */
package gsb.service;

import java.util.ArrayList;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;

/**
 * @author Caroline Jaffré
 */
public class MedecinService {
	
	public static Medecin rechercherMedecin(String unCodeMedecin){
		Medecin unMedecin = null;
		try{
		if (unCodeMedecin==null) {
            throw new Exception("Donnée obligatoire : code");
        }
		unMedecin = MedecinDao.rechercher(unCodeMedecin);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return unMedecin;
	}

    public static ArrayList<Medecin> retournerCollectionDesMedecins() {
        return MedecinDao.retournerCollectionDesMedecins();
    }

	public static ArrayList<String> listeCodesMedecin()
	{
		ArrayList<String> lesCodes = new ArrayList<String>();

		ArrayList<Medecin> lesMedecins = MedecinDao.retournerCollectionDesMedecins();
		for (int i = 0; i < lesMedecins.size(); i++)
		{
			lesCodes.add(lesMedecins.get(i).getCodeMed());
		}
		return lesCodes;
	}
	
}