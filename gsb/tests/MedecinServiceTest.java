/*
 * Créé le 23 févr. 2015
 *
 */
package gsb.tests;

import gsb.modele.Medecin;
import gsb.service.MedecinService;

/**
 * @author Isabelle
 * 23 févr. 2015
 */
public class MedecinServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Medecin unMedecin = MedecinService.rechercherMedecin("m002");
		System.out.println(unMedecin.getNom());
		System.out.println(unMedecin.getPrenom());
		System.out.println(unMedecin.getAdresse());
		System.out.println(unMedecin.getLaLocalite().getCodePostal());
		System.out.println(unMedecin.getLaLocalite().getVille());
		System.out.println(unMedecin.getTelephone());
		System.out.println(unMedecin.getPotentiel());
		System.out.println(unMedecin.getSpecialite());
		
		

	}

}
