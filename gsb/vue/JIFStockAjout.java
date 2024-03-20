package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import gsb.modele.Stocker;
import gsb.service.MedicamentService;
import gsb.service.StockService;
import gsb.service.VisiteurService;

/*
 * Vue Ajout Stock (dans Visiteur)
 * @author Caroline Jaffré
 */
public class JIFStockAjout extends JInternalFrame  implements ActionListener 
{
    // Panels
    protected JPanel p;  
	protected JPanel pTexte;
    protected JPanel pBoutons;
    protected JPanel pErreur;
    // Labels
    protected JLabel JLcodeVisiteur;
	protected JLabel JLdepotLegal;
	protected JLabel JLquantite;
    protected JLabel JLerreur;
    protected JLabel labelBreak;
    // Champs
	protected JComboBox<String> JCMedicament;
	protected JComboBox<String> JCVisiteur;
	protected JTextField JTquantite;
    protected int quantite;
    // Bouton
    protected JButton ajouter;

    public JIFStockAjout()
    {
        p = new JPanel(new GridLayout(5,1));  // panneau principal de la fenêtre
        pBoutons = new JPanel();    // panneau supportant les boutons
        pTexte = new JPanel(new GridLayout(3,2));

        JLcodeVisiteur = new JLabel("Code Visiteur");
	    JLdepotLegal = new JLabel("Dépot Legal");
	    JLquantite = new JLabel("Quantite");

        // Options visiteur
        JCVisiteur = new JComboBox<String>();
        ArrayList<String> matricules = VisiteurService.getListeVisiteurs();
        for (String matricule : matricules)
        {
            JCVisiteur.addItem(matricule);
        }
        // Options médicament
        JCMedicament = new JComboBox<String>();
        ArrayList<String> codesMedicaments = MedicamentService.getListeMedicaments();
        for (String codeMedicament : codesMedicaments)
        {
            JCMedicament.addItem(codeMedicament);
        }
        JTquantite = new JTextField(20);

        pTexte.add(JLcodeVisiteur);
        pTexte.add(JCVisiteur);
        pTexte.add(JLdepotLegal);
        pTexte.add(JCMedicament);
        pTexte.add(JLquantite);
        pTexte.add(JTquantite);
        p.add(pTexte);
        p.add(pBoutons);
        pErreur = new JPanel();
        p.add(pErreur);
        Container contentPane = getContentPane();
        contentPane.add(p);
        ajouter = new JButton("Ajouter");
        pBoutons.add(ajouter);

        pErreur = new JPanel();
        p.add(pErreur);

        labelBreak = new JLabel("<html><br/></html>");
        pErreur.add(labelBreak);
        JLerreur = new JLabel("");
        pErreur.add(JLerreur);
        // déclaration des sources d'évènements
        ajouter.addActionListener(this);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Ajout Stock");
        // ajout d'un écouteur d'évènement pour la fermeture de la fenêtre
        addInternalFrameListener(new InternalFrameAdapter(){
            public void  internalFrameClosing(InternalFrameEvent e) {
                          //le code que tu veux exécuter à la fermeture de la JInternalFrame
            }
        });
    }
    public void actionPerformed(ActionEvent evt) 
    { 
		Object source = evt.getSource();
        if (source == ajouter)
        {
            // On vérifie si la quantité est un chiffre
            boolean isInt = false;
            try 
            {
                quantite = Integer.parseInt(JTquantite.getText());
                isInt = true;
            } catch (Exception e) 
            {
                JLerreur.setText("Veuillez entrer un nombre entier dans le champ quantité.");
            }

            if (isInt)
            {
                // on vérifie que la quantité est supérieur à 0
                if (quantite > 0)
                {
                    String leVisiteur = (String)JCVisiteur.getSelectedItem();
                    String leMedicament = (String)JCMedicament.getSelectedItem();
                    String message = "Ajouter " + quantite + " de " + leMedicament + " a " + leVisiteur + " ?";
                    // Puisque cet ajout est assez rapide (avec les JComboBox) j'ai ajouté une popup pour être sûr que l'utilisateur ne s'est pas trompé dans son choix.
                    int choix = JOptionPane.showConfirmDialog(p, message, "Validation", JOptionPane.YES_NO_OPTION);
                    if (choix == JOptionPane.YES_OPTION)
                    {
                        int codeRequete = 0;
                        // On vérifie si ce visiteur a déjà ce type de médicament :
                        Stocker stockExistant = StockService.rechercherStock(leVisiteur, leMedicament);
                        if (stockExistant == null)
                        {
                            // S'il n'en a pas, on l'ajoute.
                            codeRequete = StockService.ajoutStock(quantite, leVisiteur, leMedicament);
                        }
                        else
                        {
                            // Sinon, on ajoute la nouvelle quantite à l'ancienne quantite.
                            stockExistant.setQteStock(stockExistant.getQteStock() + quantite);
                            codeRequete = StockService.updateStock(stockExistant);
                        }
                        if (codeRequete == 0)
                        {
                            JLerreur.setText("La requête SQL a échoué. Vérifiez le contenu des champs.");
                        }
                        else if (codeRequete == 1)
                        {
                            JLerreur.setText("Insertion BDD réussie mais impossible de récupérer la localité en local.");
                        }
                        else if (codeRequete == 2)
                        {
                            JLerreur.setText("Insertion réussie !");
                            JTquantite.setText("");
                        }
                    }
                }
                else
                {
                    JLerreur.setText("La quantité doit être supérieure à 0.");
                }
            }
        }
    }
    
}
