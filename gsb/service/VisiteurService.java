package gsb.service;

import java.util.ArrayList;
import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;
/*
 * @author Caroline Jaffré
 */
public class VisiteurService 
{
    public static ArrayList<String> getListeVisiteurs()
    {
        ArrayList<String> laListe = new ArrayList<String>();
		try
        {
		    ArrayList<Visiteur> visiteurs = VisiteurDao.retournerCollectionDesVisiteurs();
            for (Visiteur visiteur : visiteurs)
            {
                laListe.add(visiteur.getMatricule());
            }
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return laListe;
	}   
    
    public static Visiteur rechercher(String unMatricule) {
        return VisiteurDao.rechercher(unMatricule);
    }

    public static ArrayList<String> listeMatricules()
	{
		ArrayList<String> lesMatricules = new ArrayList<String>();

		ArrayList<Visiteur> lesVisiteurs = VisiteurDao.retournerCollectionDesVisiteurs();
		for (int i = 0; i < lesVisiteurs.size(); i++)
		{
			lesMatricules.add(lesVisiteurs.get(i).getMatricule());
		}
		return lesMatricules;
	}
}