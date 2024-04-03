package gsb.modele.dao;
import gsb.modele.Podium;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * @author Caroline Jaffré
 */
public class PodiumDao 
{
    public static ArrayList<Podium> getPodium()
    {
        // On veut un podium de taille max 10 pour que ça rentre dans l'UI
        ArrayList<Podium> lePodium = new ArrayList<Podium>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC GetPodium @taillePodium = 10");
        try
        {
            int rang = 0;
            if (reqSelection != null)
            {
                while (reqSelection.next())
                {
                    // le visiteur n'est affiché que s'il a fait au moins une visite ce mois-ci
                    if (reqSelection.getInt(5) > 0)
                    {
                        rang++;
                        Podium lignePodium = new Podium(String.valueOf(rang), reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getInt(4), reqSelection.getInt(5));
                        lePodium.add(lignePodium);
                    }
                }
            }
            else
            {
                System.out.println("il n'y a pas encore eu de visite ce mois-ci.");
            }
        }
        catch(Exception e) 
        {
			System.out.println("erreur reqSelection.next() pour EXEC GetPodium @taillePodium = 10");
			e.printStackTrace();
		}
        ConnexionMySql.fermerConnexionBd();
        return lePodium;
    }
}
