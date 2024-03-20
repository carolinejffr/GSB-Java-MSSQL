package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.service.MedecinService;
import gsb.service.VisiteService;
import gsb.service.VisiteurService;
import gsb.utils.ValidationUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import javax.swing.JTextArea;

/**
 * Vue Ajout Visite
 * 
 * @author Gouault Lucas
 */
public class JIFVisiteAjout extends JInternalFrame implements ActionListener 
{
	// Panels
	protected JPanel p, 
			   pChamps,
			  pBoutons,
			   pErreur;

	// Labels
	protected JLabel JLreference,
						  JLdate,
				   JLcommentaire,
					 JLMatricule,
					   JLMedecin;

	// Champs
	protected JTextField JTReference,
						      JTDate;
								
	protected JTextArea JTCommentaire;

	protected JComboBox<String> JCMatricule,
							  JCCodeMedecin;

	// Bouton
	protected JButton JBAjouter,
						JBVider;

	public JIFVisiteAjout()
	{
		// Panels
		p = new JPanel();  		// panneau principal de la fenêtre
		pChamps = new JPanel(new GridLayout(5, 2));	// panneau des champs
		pBoutons = new JPanel();	// panneau des boutons
		
		// Labels
		JLreference = new JLabel("Reference");
		JLdate = new JLabel("Date");
		JLcommentaire = new JLabel("Commentaire");
		JLMatricule = new JLabel("Matricule");
		JLMedecin = new JLabel("Code Medecin");

		// Champs
		JTReference = new JTextField(20);
		JTDate = new JTextField(20);
		JTCommentaire = new JTextArea();
		JTCommentaire.setLineWrap(true);
		JTCommentaire.setWrapStyleWord(true);

		JCMatricule = new JComboBox<String>();
		JCCodeMedecin = new JComboBox<String>();

		// Ajout des données dans les boîtes de sélection
		ArrayList<String> visiteurs = VisiteurService.getListeVisiteurs();
		ArrayList<Medecin> medecins = MedecinService.retournerCollectionDesMedecins();

		for(String visiteur : visiteurs) {
			JCMatricule.addItem(visiteur);
		}

		for(Medecin medecin : medecins) {
			JCCodeMedecin.addItem(medecin.getCodeMed());
		}

		// Boutons
		JBAjouter = new JButton("Ajouter");
		JBVider = new JButton("Vider");

		// Ajout des composants dans leurs panneaux respectifs
		pChamps.add(JLreference);
		pChamps.add(JTReference);

		pChamps.add(JLdate);
		pChamps.add(JTDate);

		// Pour pouvoir lire toute la zone de texte
		JScrollPane scroll = new JScrollPane(JTCommentaire);

		pChamps.add(JLcommentaire);
		pChamps.add(scroll);

		pChamps.add(JLMatricule);
		pChamps.add(JCMatricule);

		pChamps.add(JLMedecin);
		pChamps.add(JCCodeMedecin);

		pBoutons.add(JBAjouter);
		pBoutons.add(JBVider);

		// Ajout du panneau principal dans la fenêtre
		p.add(pChamps);
		p.add(pBoutons);

		Container contentPane = getContentPane();
        contentPane.add(p);

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Ajout Visite");

		// Ecouteurs d'évènements
		JBAjouter.addActionListener(this);
		JBVider.addActionListener(this);

		// Nécessaire pour que la fenêtre puisse être fermée
		addInternalFrameListener(new InternalFrameAdapter() {
			/**
			 * Exécute du code lorsque l'utilisateur ferme la fenêtre
			 * 
			 * @param  e InternalFrameEvent object representing the event
			 * @return   void
			 */
			public void internalFrameClosing(InternalFrameEvent e) {
				
			}
		});
	}
	/**
	 * Exécuté quand l'utilisateur fait quelque chose dans la fenêtre
	 *
	 * @param  evt	L'évènement
	 */
	public void actionPerformed(ActionEvent evt)
	{ 
		Object source = evt.getSource();

		if(source == JBAjouter) { // Bouton Ajouter cliqué
			// Création d'une visite  à partir des champs
			Visite uneVisite = new Visite(
				JTReference.getText(),
				JTDate.getText(),
				JTCommentaire.getText(),
				MedecinService.rechercherMedecin(JCCodeMedecin.getSelectedItem().toString()),
				VisiteurService.rechercher(JCMatricule.getSelectedItem().toString())
			);

			// null => aucune erreur, autre => erreur
			String err = visiteValide(uneVisite);

			// Vérification validité de la visite
			if(err == null) {
				// Ajout de la visite dans la base de données
				VisiteService.ajouter(uneVisite);

				// Affichage de la fenêtre de confirmation de l'ajout
				JOptionPane.showMessageDialog(
					null,
					"Visite ajoutée avec succès",
					"Visite ajoutée",
					JOptionPane.INFORMATION_MESSAGE
				);

				// Vidage des champs pour optimiser la productivité de l'utilisateur
				vider();
			} else {
				// Affichage de l'erreur
				JOptionPane.showMessageDialog(
				null,
				err,
				"Erreur dans la saisie",
				JOptionPane.ERROR_MESSAGE
				);
			}
			
			
		} else if(source == JBVider) { // Bouton Vider cliqué
			// Vidage des champs
			vider();
		}
	}

	/**
	 * Vérifie la validité d'une visite
	 *
	 * @param  visite	La visite à vérifier
	 * @return         	null => valide, autre => message d'erreur
	 */
	protected static String visiteValide(Visite visite) {

		// tests
		boolean JTReferenceValide = testerLongueur(visite.getReference(), 1, 5),
		JTDateValide = ValidationUtils.isDateValide(visite.getDate()),
		JTCommentaireValide = testerLongueur(visite.getCommentaire(), 0, 100),
		JTMatriculeValide = testerLongueur(visite.getUnVisiteur().getMatricule(), 1, 4),
		JTCodeMedecinValide = testerLongueur(visite.getUnMedecin().getCodeMed(), 1, 4);

		String err = null;

		/**
		 * Définition du message d'erreur
		 * PS: J'aurais pu afficher toutes les erreurs d'un coup avec un tableau mais,
		 * pour garantir une lisibilité de qualité, je ne l'ai pas fait.
		*/
		if(!JTReferenceValide) {
			err = "La référence doit avoir une longueur comprise entre 1 et 5 caractères";
		} else if(!JTDateValide) {
			err = "La date doit être au format JJ/MM/AAAA";
		} else if(!JTCommentaireValide) {
			err = "Le commentaire doit avoir une longueur comprise entre 0 et 100 caractères";
		} else if(!JTMatriculeValide) {
			err = "Le matricule doit avoir une longueur comprise entre 1 et 4 caractères";
		} else if(!JTCodeMedecinValide) {
			err = "Le code medecin doit avoir une longueur comprise entre 1 et 4 caractères";
		}

		return err;
	}

	/**
	 * Vide les champs de la fenêtre
	 */
	protected void vider() {
		JTReference.setText("");
		JTDate.setText("");
		JTCommentaire.setText("");
		JCMatricule.setSelectedIndex(0);
		JCCodeMedecin.setSelectedIndex(0);
	}

	/**
	 * Vérifie que la longueur d'une chaîne est comprise dans un intervalle
	 *
	 * @param  chaine  La chaine à vérifier
	 * @param  min     Le minimum de la longueur
	 * @param  max     Le maximum de la longueur
	 * @return         true => longueur valide, false => longueur invalide
	 */
	protected static boolean testerLongueur(String chaine, int min, int max) {
		return (min <= chaine.length() && chaine.length() <= max);
	}
}