package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JIFMedicamentFamille extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ArrayList<Medicament> lesMedicaments;


	protected JPanel p;
	protected JScrollPane scrollPane;
	protected JPanel pSaisie;
	protected JTextField JTcodeFamille;
	protected JButton JBafficherFiche;
	protected MenuPrincipal fenetreContainer;

	public JIFMedicamentFamille(MenuPrincipal uneFenetreContainer, String laFamille) {

		fenetreContainer = uneFenetreContainer;
		// récupération des données Medicament dans la collection
        if (laFamille == null){
		    lesMedicaments = MedicamentDao.retournerCollectionDesMedicaments();
        }
        else {
            lesMedicaments = MedicamentDao.retournerMedicamentsParFamille(laFamille);
        }

		int nbLignes = lesMedicaments.size();

		JTable table;


		p = new JPanel(); // panneau principal de la fenêtre

		int i=0;
		String[][] data = new String[nbLignes][5] ;
		for(Medicament unMedicament : lesMedicaments){
			data[i][0] = unMedicament.getDepotLegal();
			data[i][1] = unMedicament.getNomCommercial();
			data[i][2] = unMedicament.getComposition();
            data[i][3] = unMedicament.getEffets();
            data[i][4] = unMedicament.getContreIndication();
			i++;
			}
		String[] columnNames = {"Dépot Légal", "Nom","Composition","Effets","Contre indications",};
		table = new JTable(data, columnNames);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		p.add(scrollPane);
		
		pSaisie = new JPanel();
		JTcodeFamille = new JTextField(20);
		JTcodeFamille.setMaximumSize(JTcodeFamille.getPreferredSize());
		JBafficherFiche = new JButton("Afficher Fiche médicament");
		JBafficherFiche.addActionListener(this);
		pSaisie.add(JTcodeFamille);
		pSaisie.add(JBafficherFiche);
		p.add(pSaisie);
		
		// mise en forme de la fenêtre
		Container contentPane = getContentPane();
		contentPane.add(p);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
   		if (source == JBafficherFiche){
   			String uneFamille = JTcodeFamille.getText();
   			if (uneFamille!=null){
                fenetreContainer.ouvrirFenetre(new JIFMedicamentFamille(null, uneFamille));
   		    }	
	    }
    }
}   
