/*
 * Créé le 2 mars 2015
 */
package gsb.vue;



import gsb.modele.Medecin;

/**
 * @author Isabelle
 * 2 mars 2015
 */
public class JIFMedecinFiche extends JIFMedecin {

	private static final long serialVersionUID = 1L;

	public JIFMedecinFiche(Medecin unMedecin) {
		super();
		this.remplirText(unMedecin);

	}
	

}
