package gsb.utils;

import java.sql.*;
import java.util.ArrayList;

public class sgdb 
{

    public static void utiliserSgdb(String laRequete, boolean doitAfficherResultat) 
    {
        String url = "jdbc:mysql://127.0.0.1:8889/";
        String user = "root";
        String password = "password";
        Connection con = null;
        Statement requete = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url,user,password);

            requete = con.createStatement();

            if (doitAfficherResultat == true)
            {
                ResultSet resultat = requete.executeQuery(laRequete);
                while (resultat.next()) 
                {
                    System.out.println(resultat.getString(1)+" "+resultat.getString(2));
                };// fin while
            }
            else
            {
                requete.executeUpdate(laRequete);
            }
            

        }
        catch(Exception e) 
        {

        System.out.println("Echec SGDB");

        e.printStackTrace();

        }
        finally
        {
            try
            {
                con.close();
                requete.close();
            }
            catch(Exception e) 
            {
                e.printStackTrace();
            }
        }
    }

    public static String retournerString(String laRequete)
    {
        String url = "jdbc:mysql://127.0.0.1:8889/";
        String user = "root";
        String password = "password";
        Connection con = null;
        Statement requete = null;
        String laDonnee = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url,user,password);

            requete = con.createStatement();

            ResultSet resultat = requete.executeQuery(laRequete);
            while (resultat.next()) 
            {
                laDonnee = resultat.getString(1);
            };// fin while
            
        }
        catch(Exception e) 
        {

        System.out.println("Echec SGDB");

        e.printStackTrace();

        }
        finally
        {
            try
            {
                con.close();
                requete.close();
            }
            catch(Exception e) 
            {
                e.printStackTrace();
            }
        }

        if (laDonnee == null)
        {
            System.out.println("ERREUR : votre requête ne retourne pas de donnée.");
        }
        return laDonnee;
    }

    public static ArrayList<String> RequeteListString(String laRequete) 
    {
        String url = "jdbc:mysql://127.0.0.1:8889/";
        String user = "root";
        String password = "password";
        Connection con = null;
        Statement requete = null;
        ArrayList<String> resultatDelaRequete = new ArrayList<String>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url,user,password);

            requete = con.createStatement();

            ResultSet resultat = requete.executeQuery(laRequete);
            while (resultat.next()) 
            {
                resultatDelaRequete.add(resultat.getString(1)+" "+resultat.getString(2));
            };// fin while
            

        }
        catch(Exception e) 
        {

        System.out.println("Echec SGDB");

        e.printStackTrace();

        }
        finally
        {
            try
            {
                con.close();
                requete.close();
            }
            catch(Exception e) 
            {
                e.printStackTrace();
            }
        }
        return resultatDelaRequete;
    }
}