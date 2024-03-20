/*
 * Créé le 23 févr. 2015
 *
 */
package gsb.tests;

import gsb.modele.dao.ConnexionMySql;

import java.sql.ResultSet;

/**
 * @author Isabelle
 * 23 févr. 2015
 */
public class ConnexionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResultSet resultat = ConnexionMySql
				.execReqSelection("select * from LOCALITE");
		try {
			while (resultat.next()) {
				System.out.println(resultat.getString(1) + "  "
						+ resultat.getString(2));
			}
			;
			System.out.println("fin");
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}

}


