/*
 * Créé le 22 févr. 2015
 *
 */
package gsb.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author Isabelle 
 * 22 févr. 2015 
 */
public class MenuPrincipal extends JFrame implements ActionListener {

	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2591453837113855452L;

	protected JInternalFrame myJInternalFrame;
	protected JDesktopPane desktopPane;
	protected JMenuBar mbar;
	protected JMenu mMedecins;
	protected JMenu mMedicaments;
	protected JMenu mVisiteurs;
	protected JMenu mLocalites;
	protected JMenu mStock;
	protected JMenu mVisites;

	/**
	 * 
	 */
	public MenuPrincipal() {

		myJInternalFrame = new JInternalFrame(); // pour affichage d'une seule
													// JInternalFrame à la fois
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(120, 196, 134));
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);

		setTitle("GSB");
		setSize(500, 400);

		// Ajout d'une barre de menus à la fenêtre
		mbar = new JMenuBar();
		mMedecins = new JMenu("Medecins");
		JMenuItem mC1 = new JMenuItem("Consultation Medecin");
		mC1.addActionListener(this); // installation d'un écouteur d'action
		mMedecins.add(mC1);
		JMenuItem mC2 = new JMenuItem("Liste Medecins");
		mC2.addActionListener(this);
		mMedecins.add(mC2);
		JMenuItem mC3 = new JMenuItem("Ajout Medecin");
		mC3.addActionListener(this);
		mMedecins.add(mC3);

		mMedicaments = new JMenu("Medicaments");
		JMenuItem mE1 = new JMenuItem("Consultation Medicament");
		mE1.addActionListener(this); // installation d'un écouteur d'action
		mMedicaments.add(mE1);
		JMenuItem mE2 = new JMenuItem("Ajout Medicaments");
		mE2.addActionListener(this);
		mMedicaments.add(mE2);
		JMenuItem mE3 = new JMenuItem("Liste Medicament");
		mE3.addActionListener(this); // installation d'un écouteur d'action
		mMedicaments.add(mE3);
		JMenuItem mE4 = new JMenuItem("Famille Medicament");
		mE4.addActionListener(this); // installation d'un écouteur d'action
		mMedicaments.add(mE4);

		mVisites = new JMenu("Visites");
		JMenuItem mA1 = new JMenuItem("Consultation Visite");
		mA1.addActionListener(this); // installation d'un écouteur d'action
		mVisites.add(mA1);
		JMenuItem mA2 = new JMenuItem("Ajout Visite");
		mA2.addActionListener(this);
		mVisites.add(mA2);
		// JMenuItem mA3 = new JMenuItem("Mise a jour Visite");
		// mA3.addActionListener(this);
		// mVisites.add(mA3);

		mVisiteurs = new JMenu("Visiteurs");
		JMenuItem mV1 = new JMenuItem("Liste Visiteurs");
		mV1.addActionListener(this); // installation d'un écouteur d'action
		mVisiteurs.add(mV1);
		JMenuItem mV2 = new JMenuItem("Fiche Visiteur");
		mV2.addActionListener(this);
		mVisiteurs.add(mV2);
		JMenuItem mV3 = new JMenuItem("Ajout Visiteur");
		mV3.addActionListener(this);
		mVisiteurs.add(mV3);
		
		mStock = new JMenu("Stock");
		JMenuItem mS1 = new JMenuItem("Stock Echantillons Visiteur");
		mS1.addActionListener(this);
		mStock.add(mS1);
		JMenuItem mS2 = new JMenuItem("Ajout Echantillons Visiteur");
		mS2.addActionListener(this);
		mStock.add(mS2);
		
		mLocalites = new JMenu("Localites");
		JMenuItem mL1 = new JMenuItem("Ajout Localite");
		mL1.addActionListener(this); // installation d'un écouteur d'action
		mLocalites.add(mL1);

		mbar.add(mMedecins);
		mbar.add(mMedicaments);
		mbar.add(mVisites);
		mbar.add(mVisiteurs);
		mbar.add(mStock);
		mbar.add(mLocalites);
		setJMenuBar(mbar);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() instanceof JMenuItem) {
			String ChoixOption = evt.getActionCommand();

			if (ChoixOption.equals("Consultation Medecin")) 
			{
				// Creation d'une sous-fenêtre
				ouvrirFenetre(new JIFMedecinCons());

			} 
			else if (ChoixOption.equals("Liste Medecins")) 
			{
				ouvrirFenetre(new JIFMedecinListeDic(this));
			}

			// Ajouts perso -Caroline 
			else if (ChoixOption.equals("Ajout Medecin")) 
			{
				ouvrirFenetre(new JIFMedecinAjout());
			}
			else if (ChoixOption.equals("Ajout Localite")) 
			{
				ouvrirFenetre(new JIFLocaliteAjout());
			}
			else if (ChoixOption.equals("Ajout Echantillons Visiteur")) 
			{
				ouvrirFenetre(new JIFStockAjout());
			}
			else if (ChoixOption.equals("Stock Echantillons Visiteur")) 
			{
				ouvrirFenetre(new JIFStockVisiteur());
			}
      
			else if (ChoixOption.equals("Ajout Visiteur")) 
			{
				//Léo
				ouvrirFenetre(new JIFVisiteurAjout());
			}
			else if (ChoixOption.equals("Liste Visiteurs"))
			{
				//Léo
				ouvrirFenetre(new JIFVisiteurListDic(this));
			}
			else if (ChoixOption.equals("Fiche Visiteur"))
			{
				//Léo
				ouvrirFenetre(new JIFVisiteurCons());
			}
      
			// Ajouts perso -Thomas
			else if (ChoixOption.equals("Consultation Medicament")) 
			{
				ouvrirFenetre(new JIFMedicamentConsultation(null));
			}
			else if (ChoixOption.equals("Ajout Medicaments")) 
			{
				ouvrirFenetre(new JIFMedicamentAjout());
			}
			else if (ChoixOption.equals("Liste Medicament")) 
			{
				ouvrirFenetre(new JIFMedicamentListe(this));
			}
			else if (ChoixOption.equals("Famille Medicament")) 
			{
				ouvrirFenetre(new JIFMedicamentFamille(this, null));
			}
			//  else if (ChoixOption.equals("Mise a jour Visite")) 
			//		{
			//			ouvrirFenetre(new JIFVisiteModif());
			//		}
      
			// Ajouts perso -Lucas
			else if(ChoixOption.equals("Ajout Visite"))
			{
				ouvrirFenetre(new JIFVisiteAjout());
			}
			else if(ChoixOption.equals("Consultation Visite"))
			{
				ouvrirFenetre(new JIFVisiteConsultation());
      			}
		}

	}

	public void ouvrirFenetre(JInternalFrame uneFenetre) {
		myJInternalFrame.dispose(); // si une fenêtre était dejà affichée, elle
									// est libérée
		myJInternalFrame = uneFenetre;
		myJInternalFrame.setVisible(true);
		myJInternalFrame.setResizable(true);
		myJInternalFrame.setMaximizable(true);
		myJInternalFrame.setClosable(true);
		myJInternalFrame.setSize(485, 340);
		desktopPane.add(myJInternalFrame);
	}

}
