/*
 * Créé le 30 Novembre 2023
 *
 */
package gsb.vue;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author Tallec--Éven Léo
 * 23 févr. 2015 
 */
public class JIFVisiteurListDic extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	//private ArrayList<Visiteur> lesVisiteurs;
	private HashMap<String,Visiteur> diccoVisiteur;


	protected JPanel p;
	protected JScrollPane scrollPane;
	protected JPanel pSaisie;
	protected JTextField JTcodeVisiteur;
	protected JButton JBafficherFiche;
	protected MenuPrincipal fenetreContainer;
	protected JTable table;

	public JIFVisiteurListDic(MenuPrincipal uneFenetreContainer) {

		fenetreContainer = uneFenetreContainer;
		// récupération des données Visiteur dans la collection
		//lesVisiteurs = VisiteurDao.retournerCollectionDesVisiteurs();

		//int nbLignes = lesVisiteurs.size();
		diccoVisiteur = VisiteurDao.retournerDictionnaireDesMedecins();
		int nbLignes= diccoVisiteur.size();
		
		p = new JPanel(); // panneau principal de la fenêtre

		int i=0;
		String[][] data = new String[nbLignes][4] ;
		//for(Visiteur unVisiteur : lesVisiteurs){
		
		for (Map.Entry<String,Visiteur> uneEntree : diccoVisiteur.entrySet()){
			data[i][0] = uneEntree.getValue().getMatricule();
			data[i][1] = uneEntree.getValue().getNom();
			data[i][2] = uneEntree.getValue().getPrenom();
			data[i][3] = uneEntree.getValue().getUneLocalite().getVille() ;
			i++;
			}
		String[] columnNames = {"Matricule", "Nom","Prenom","Ville"};
		table = new JTable(data, columnNames);
		table.getSelectionModel().addListSelectionListener(table);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		p.add(scrollPane);
		
		pSaisie = new JPanel();
		JTcodeVisiteur = new JTextField(20);
		JTcodeVisiteur.setMaximumSize(JTcodeVisiteur.getPreferredSize());
		JBafficherFiche = new JButton("Afficher Fiche visiteur");
		JBafficherFiche.addActionListener(this); // source d'évenement
		pSaisie.add(JTcodeVisiteur);
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
   			if (diccoVisiteur.containsKey(JTcodeVisiteur.getText())){
   	   			Visiteur unVisiteur = diccoVisiteur.get(JTcodeVisiteur.getText());
   	   			fenetreContainer.ouvrirFenetre(new JIFVisiteurFiche(unVisiteur));
   			}
   		}
   		if(source == table){
   			JTcodeVisiteur.setText((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
   			
   		}
	}
}
