/*
 * Créé le 30 novembre 2023
 *
 */
package gsb.vue;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * @author Tallec--Éven Léo
 * 30 nov. 2023
 */
public class JIFVisiteurCons extends JIFVisiteur  implements ActionListener {
	
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	private JButton premier;
    private JButton suivant;
    private JButton precedent; 
    private JButton dernier; 
    private ArrayList<Visiteur> lesVisiteurs;
	private int indiceEnCours;
        
    public JIFVisiteurCons() {
        super();
        premier = new JButton("Premier");
        pBoutons.add(premier);
        suivant = new JButton("Suivant");
        pBoutons.add(suivant);
        precedent = new JButton("Precedent");
        pBoutons.add(precedent);
        dernier = new JButton("Dernier");
        pBoutons.add(dernier);
        // déclaration des sources d'évènements
        premier.addActionListener(this);
        suivant.addActionListener(this);
        precedent.addActionListener(this);
        dernier.addActionListener(this);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Consultation données Visiteur");
        
        // récupération des données Visiteur dans la collection
        lesVisiteurs = VisiteurDao.retournerCollectionDesVisiteurs();
        indiceEnCours = 0;
        
        if (lesVisiteurs.size()!=0){ // si collection non vide, affichage des données du premier Visiteur
        	Visiteur leVisiteur = lesVisiteurs.get(0);
	    	remplirText(leVisiteur);
	    	}
        
        // ajout d'un écouteur d'évènement pour la fermeture de la fenêtre
        addInternalFrameListener(new InternalFrameAdapter(){
            public void  internalFrameClosing(InternalFrameEvent e) {
                          //le code que tu veux exécuter à la fermeture de la JInternalFrame
            }
        });
    }
	
    public void actionPerformed(ActionEvent evt) { 
		Object source = evt.getSource();
   		if (source == premier){
				indiceEnCours = 0;
				Visiteur leVisiteur = lesVisiteurs.get(indiceEnCours);
		    	remplirText(leVisiteur);				}
		 else if (source == dernier){
				indiceEnCours = lesVisiteurs.size() - 1;
				Visiteur leVisiteur = lesVisiteurs.get(indiceEnCours);
		    	remplirText(leVisiteur);
				}
		 else if (source == precedent){
				if (indiceEnCours > 0) indiceEnCours = indiceEnCours - 1;
				Visiteur leVisiteur = lesVisiteurs.get(indiceEnCours);
		    	remplirText(leVisiteur);				}
		 else if (source == suivant){
				if (indiceEnCours < (lesVisiteurs.size() - 1)) indiceEnCours = indiceEnCours + 1;
				Visiteur leVisiteur = lesVisiteurs.get(indiceEnCours);
		    	remplirText(leVisiteur);		    	}
 } // fin actionPerformed

}
