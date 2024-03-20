package gsb.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import gsb.modele.Localite;
/*
 * Une vue permettant d'ajouter un nouveau médecin.
 * @author Caroline Jaffré
 */
public class JIFMedecinAjout extends JIFMedecin  implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JButton vider;
    private JButton ajouter;
    private JLabel labelErreur;
    private JPanel pErreur;

    // Ce label sert à la mise en page et s'assure que les erreurs soient bien en-dessous des boutons
    private JLabel labelBreak;

        
    public JIFMedecinAjout() {
        super();
        // On n'a pas besoin de ces champs pour le formulaire, donc on les rends invisibles.
        JTcode.setVisible(false);
        JLcode.setVisible(false);
        JLcp.setVisible(false);
        JTcp.setVisible(false);
        pTexte.remove(JTcode);
        pTexte.remove(JLcode);
        pTexte.remove(JTcp);
        pTexte.remove(JLcp);
        

        vider = new JButton("Vider les champs");
        pBoutons.add(vider);
        ajouter = new JButton("Ajouter");
        pBoutons.add(ajouter);

        pErreur = new JPanel();
        p.add(pErreur);

        labelBreak = new JLabel("<html><br/></html>");
        pErreur.add(labelBreak);
        labelErreur = new JLabel("");
        pErreur.add(labelErreur);

        // déclaration des sources d'évènements
        vider.addActionListener(this);
        ajouter.addActionListener(this);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Ajout d'un Medecin");
    
        
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
        if (source == vider)
        {
            viderText();
        }
        else if (source == ajouter)
        {
            // On commence par vérifier si les champs sont valides
            boolean verif = verifChamps();
            System.out.println("Verif = " + verif);

            // Si c'est bon, on teste la requête SQL
            if (verif == true)
            {
                int codeRequete = ajoutMedecinBDD();
                // La requête a échouée
                if (codeRequete == 0)
                {
                    // Si cette erreur arrive, c'est soit que le contenu des champs n'est pas bon, soit que la localité (code postal) n'existe pas en BDD.
                    Localite laLocalite = testLocalite();
                    if (laLocalite == null)
                    {
                        labelErreur.setText("<html>La ville indiquée n'existe pas dans la base de données.<br/>Veuillez l'ajouter avant de réessayer.</html>");
                    }
                    else
                    {
                        // On teste alors le contenu des champs :
                        String laString = testLongueur();
                        if (laString != null)
                        {
                            labelErreur.setText("Le contenu du champ " + laString + " est trop long !");
                        }
                        else
                        {
                            labelErreur.setText("Echec requête. Vérifiez le contenu de vos champs.");
                        }
                    }
                }
                // L'insertion SQL a réussi mais pas la récupération du médecin en local. Cela ne devrai normalement jamais arriver...
                else if (codeRequete == 1)
                {
                    labelErreur.setText("Insertion BDD réussie mais impossible de récupérer le médecin en local.");
                }
                // L'insertion s'est déroulée normalement.
                else if (codeRequete == 2)
                {
                    labelErreur.setText("Insertion réussie !");
                    viderText();
                }
            }
            // Sinon, on affiche un message d'erreur
            else
            {
                labelErreur.setText("Veuillez remplir tous les champs obligatoires (tous sauf potentiel)");
            }
        }
    }

}
