package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class JIFMedicamentConsultation extends JIFMedicament  implements ActionListener {
	
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	private JButton premier;
    private JButton suivant;
    private JButton precedent; 
    private JButton dernier; 
    private ArrayList<Medicament> lesMedicaments;
	private int indiceEnCours;
        
    public JIFMedicamentConsultation(Medicament MedicEnCours) {
        super();
        JPanel consultation = new JPanel();
        premier = new JButton("Premier");
        consultation.add(premier);
        suivant = new JButton("Suivant");
        consultation.add(suivant);
        precedent = new JButton("Precedent");
        consultation.add(precedent);
        dernier = new JButton("Dernier");
        consultation.add(dernier);
        // déclaration des sources d'évènements
        premier.addActionListener(this);
        suivant.addActionListener(this);
        precedent.addActionListener(this);
        dernier.addActionListener(this);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Consultation données Medicament");
        
        // récupération des données Medicament dans la collection
        lesMedicaments = MedicamentDao.retournerCollectionDesMedicaments();
        if (MedicEnCours == null){
        	indiceEnCours = 0;
        }
        else {
            for (int i = 0; i < lesMedicaments.size(); i++) {
                if (lesMedicaments.get(i).getDepotLegal().equals(MedicEnCours.getDepotLegal())){
                    indiceEnCours = i;
                }
            }
        }
        
        if (lesMedicaments.size()!=0){ // si collection non vide, affichage des données du premier Medicament
        	Medicament leMedicament = lesMedicaments.get(indiceEnCours);
	    	remplirTextMedi(leMedicament);
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
				Medicament leMedicament = lesMedicaments.get(indiceEnCours);
		    	remplirTextMedi(leMedicament);				}
		 else if (source == dernier){
				indiceEnCours = lesMedicaments.size() - 1;
				Medicament leMedicament = lesMedicaments.get(indiceEnCours);
		    	remplirTextMedi(leMedicament);
				}
		 else if (source == precedent){
				if (indiceEnCours > 0) indiceEnCours = indiceEnCours - 1;
				Medicament leMedicament = lesMedicaments.get(indiceEnCours);
		    	remplirTextMedi(leMedicament);				}
		 else if (source == suivant){
				if (indiceEnCours < (lesMedicaments.size() - 1)) indiceEnCours = indiceEnCours + 1;
				Medicament leMedicament = lesMedicaments.get(indiceEnCours);
		    	remplirTextMedi(leMedicament);		    	}
 } // fin actionPerformed

}
