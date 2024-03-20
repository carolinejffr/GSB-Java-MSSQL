package gsb.vue;

import gsb.modele.Visiteur;

/**
 * @author Tallec--Éven Léo
 * 2 mars 2015
 */
public class JIFVisiteurFiche extends JIFVisiteur {

	private static final long serialVersionUID = 1L;

	public JIFVisiteurFiche(Visiteur unVisiteur) {
		super();
		this.remplirText(unVisiteur);
	}
}