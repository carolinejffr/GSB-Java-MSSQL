package gsb.service;

import java.util.ArrayList;

import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;

/**
 * Service Visite
 * 
 * @author Gouault Lucas
 */
public class VisiteService {
    /**
     * Renvoie la liste des visiteurs de la base
     *
     * @return La liste des visiteurs
     */
    public static ArrayList<Visite> getListeVisites() {
        ArrayList<Visite> laListe = new ArrayList<Visite>();
        try
        {
            ArrayList<Visite> visites = VisiteDao.retournerCollection();
            for (Visite visite : visites)
            {
                laListe.add(visite);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return laListe;
    }

    /**
     * Ajoute une visite à la base
     *
     * @param  uneVisite  La visite à ajouter
     * @return            0 => Erreur, 1 => OK
     */
    public static int ajouter(Visite uneVisite) {
        if(uneVisite == null) throw new NullPointerException("La visite à ajouter ne doit pas être null.");
        return VisiteDao.ajouter(uneVisite);
    }

    /**
     * Recherche une visite dans la base
     *
     * @param  reference    Identifiant de la visite à rechercher
     * @return              null => Pas trouvé, une visite => OK
     */
    public static Visite rechercherVisite(String reference) {
        if(reference == null) throw new NullPointerException("La référence de la visite à rechercher ne doit pas être null.");
        return VisiteDao.rechercher(reference);
    }

    public static int supprimer(Visite uneVisite) {
        if(uneVisite == null) throw new NullPointerException("La visite à supprimer ne doit pas être null.");
        return VisiteDao.supprimer(uneVisite.getReference());
    }
}