package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
public class RetraiteDao 
{
    public static ArrayList<String> getListeMedecinsActifs()
    {
        ArrayList<String> laListe = new ArrayList<String>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC ListeMedecinsActifs");
        try 
        {
             if (reqSelection != null)
            {
                while (reqSelection.next())
                {
                    String uneLigne = reqSelection.getString(1) + " " + reqSelection.getString(2) + " " + reqSelection.getString(3);

                    laListe.add(uneLigne);
                }
            }
            else
            {
                System.out.println("il n'y a pas de médecins actifs.");
            }
        } 
        catch(Exception e)
        {
			System.out.println( e.getMessage());
		}
		return laListe;
    }
    public static void setRetraite(String codeMed)
    {
        ConnexionMySql.execReqMaj("EXEC MettreEnRetraite @codeMed = " + codeMed);
    }
    public static ArrayList<String> listeRetraites()
    {
        ArrayList<String> laListe = new ArrayList<String>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("EXEC ListeRetraites");
        try 
        {
            if (reqSelection != null)
            {
                while (reqSelection.next())
                {
                    String uneLigne = reqSelection.getString(1) + " " + reqSelection.getString(2) + " " + reqSelection.getString(3);

                    laListe.add(uneLigne);
                }
            }
            else
            {
                System.out.println("il n'y a pas de médecins en retraite non-archivé.");
            }
        } 
        catch(Exception e)
        {
			System.out.println( e.getMessage());
		}
		return laListe;
    }
    
    public static void purger()
    {
        ConnexionMySql.execReqMaj("EXEC PurgeRetraite");
    }
}
