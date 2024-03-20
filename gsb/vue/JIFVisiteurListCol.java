/*
 * Créé le 30 novembre 2023
 *
 */
package gsb.vue;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;

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

/**
 * @author Tallec--Éven Léo
 * 30 novembre 2023
 */
public class JIFVisiteurListCol extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ArrayList<Visiteur> lesVisiteurs;


	protected JPanel p;
	protected JScrollPane scrollPane;
	protected JPanel pSaisie;
	protected JTextField JTcodeVisiteur;
	protected JButton JBafficherFiche;
	protected MenuPrincipal fenetreContainer;

	public JIFVisiteurListCol(MenuPrincipal uneFenetreContainer) {

		fenetreContainer = uneFenetreContainer;
		// récupération des données Visiteur dans la collection
		lesVisiteurs = VisiteurDao.retournerCollectionDesVisiteurs();

		int nbLignes = lesVisiteurs.size();

		JTable table;
		
		

		p = new JPanel(); // panneau principal de la fenêtre

		int i=0;
		String[][] data = new String[nbLignes][4] ;
		for(Visiteur unVisiteur : lesVisiteurs){
			data[i][0] = unVisiteur.getMatricule();
			data[i][1] = unVisiteur.getNom();
			data[i][2] = unVisiteur.getPrenom();
			data[i][3] = unVisiteur.getUneLocalite().getVille() ;
			i++;
			}
		String[] columnNames = {"Code", "Nom","Prenom","Ville"};
		table = new JTable(data, columnNames);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		p.add(scrollPane);
		
		pSaisie = new JPanel();
		JTcodeVisiteur = new JTextField(20);
		JTcodeVisiteur.setMaximumSize(JTcodeVisiteur.getPreferredSize());
		JBafficherFiche = new JButton("Afficher Fiche médecin");
		JBafficherFiche.addActionListener(this);
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
   			Visiteur unVisiteur = VisiteurDao.rechercher(JTcodeVisiteur.getText());
   			if (unVisiteur!=null){
   	   			fenetreContainer.ouvrirFenetre(new JIFVisiteurFiche(unVisiteur));
   			}
   		}	
	}
}
