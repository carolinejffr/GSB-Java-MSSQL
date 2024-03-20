package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import gsb.modele.Visite;

/**
 * Dao Visite
 * 
 * @author Gouault Lucas
 * @author Caroline Jaffré
 */
public class VisiteDao {
    /**
     * Recherche une visite dans la base à partir d'un identifiant
     *
     * @param  reference  L'identifiant de la visite à rechercher
     * @return            null => Pas trouvé, une visite => OK
     */
    public static Visite rechercher(String reference) {
        Visite visite = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC RechercherVisite @Reference = '" + reference + "'");
        
        try {
            if (reqSelection.next()) {
                visite = new Visite(   
                    reqSelection.getString(1),
                    reqSelection.getString(2),
                    reqSelection.getString(3),
                    MedecinDao.rechercher(reqSelection.getString(5)),
                    VisiteurDao.rechercher(reqSelection.getString(4))
                );
            }
        } catch(Exception e) {
            System.out.println("erreur reqSelection.next() EXEC RechercherVisite @Reference ='" + reference + "'");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return visite;
    }

    /**
     * Retourne la liste des visiteurs de la base
     *
     * @return         	La liste des visiteurs
     */
    public static ArrayList<Visite> retournerCollection() {
        ArrayList<Visite> listeVisite = new ArrayList<Visite>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC LesVisites");

        try {
            while (reqSelection.next()) {
                Visite uneVisite = new Visite(
                    reqSelection.getString(1),
                    reqSelection.getString(2),
                    reqSelection.getString(3),
                    MedecinDao.rechercher(reqSelection.getString(5)),
                    VisiteurDao.rechercher(reqSelection.getString(4))
                );
                listeVisite.add(uneVisite);
            }
        }
        catch(Exception e) {
            System.out.println("erreur reqSelection.next() pour LesVisites");
            e.printStackTrace();
        }
        ConnexionMySql.fermerConnexionBd();
        return listeVisite;
    }

    /**
     * Ajoute une visite à la base
     *
     * @param  uneVisite  La visite à ajouter
     * @return            0 => Erreur, 1 => OK
     */
    public static int ajouter(Visite uneVisite) {

        int retour = ConnexionMySql.execReqMaj(
            "EXEC VisiteAjout @Reference = '" +
            uneVisite.getReference() +"', @Date = '" +
            uneVisite.getDate()+ "', @Commentaire ='" +
            uneVisite.getCommentaire() + "', @Matricule = '" +
            uneVisite.getUnVisiteur().getMatricule() + "', @CodeMed = '" +
            uneVisite.getUnMedecin().getCodeMed() +
            "';"
        );

        ConnexionMySql.fermerConnexionBd();
        return retour;
    }
      
    public static ArrayList<Visite> retournerLesVisites()
    {
        ArrayList<Visite> lesVisites = new ArrayList<Visite>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC LesVisitesReferences");
        try 
        {
             while (reqSelection.next()) 
            {
                String refVisite = reqSelection.getString(1);
                lesVisites.add(VisiteDao.rechercher(refVisite));
            }
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           System.out.println("Erreur : retournerLesVisites()");
        }
        
        ConnexionMySql.fermerConnexionBd();
        return lesVisites;
    }

    public static int supprimer(String reference) {
        int result;
        try {
            result = ConnexionMySql.execReqMaj("EXEC VisiteSupprimer @Reference = '" + reference + "'");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur : supprimer()");
            result = 0;
        }

        ConnexionMySql.fermerConnexionBd();
        return result;
    }
}
