package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import gsb.service.LocaliteService;

public class JIFLocaliteAjout extends JInternalFrame implements ActionListener
{
    protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
    protected JPanel pErreur;

    protected JLabel JLcode;
	protected JLabel JLnom;
    protected JLabel JLerreur;
    protected JLabel labelBreak;

    protected JTextField JTcode;
	protected JTextField JTnom;

    protected JButton vider;
    protected JButton ajouter;

    public JIFLocaliteAjout()
    {
        p = new JPanel();  // panneau principal de la fenêtre
        pBoutons = new JPanel();    // panneau supportant les boutons
        pTexte = new JPanel(new GridLayout(2,2));
    	
        JLnom = new JLabel("Nom de la ville");
        JLcode = new JLabel("Code postal");

        JTnom = new JTextField(20);
        JTcode = new JTextField(20);

        pTexte.add(JLcode);
        pTexte.add(JTcode);
        pTexte.add(JLnom);
        pTexte.add(JTnom);

        p.add(pTexte);
        p.add(pBoutons);
        pErreur = new JPanel();
        p.add(pErreur);
        Container contentPane = getContentPane();
        contentPane.add(p);

        vider = new JButton("Vider les champs");
        pBoutons.add(vider);
        ajouter = new JButton("Ajouter");
        pBoutons.add(ajouter);

        pErreur = new JPanel();
        p.add(pErreur);

        labelBreak = new JLabel("<html><br/></html>");
        pErreur.add(labelBreak);
        JLerreur = new JLabel("");
        pErreur.add(JLerreur);

        // déclaration des sources d'évènements
        vider.addActionListener(this);
        ajouter.addActionListener(this);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Ajout d'une Localite");
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
            JTcode.setText("");
            JTnom.setText("");
        }
        else if (source == ajouter)
        {
            
            // on vérifie que les champs ne soient pas vides
            if (JTnom.getText().isEmpty() == false || JTcode.getText().isEmpty() == false)
            {
                // apostrophe
                JTnom.setText(JTnom.getText().replace("'", "\\'"));
                JTcode.setText(JTcode.getText().replace("'", "\\'"));
                // Majuscule au début du nom de la ville
                JTnom.setText(JTnom.getText().substring(0 , 1).toUpperCase() + JTnom.getText().substring(1));
                // On vérifie si la ville existe déjà dans la base de données.
                if (LocaliteService.getCodePostal(JTnom.getText()) != null)
                {
                    JLerreur.setText("<html>Il y a déjà une ville avec ce nom.<br/>Si vous souhaitez ajouter un arrondissement ou un département,<br/>écrivez par exemple : \"Paris 13\".</html>");
                }
                else
                {
                    int codeRequete = LocaliteService.ajoutLocaliteBDD(JTnom.getText(), JTcode.getText());
                    if (codeRequete == 0)
                    {
                        // on teste le contenu des champs
                        // Le code postal est un VARCHAR(5)
                        if (JTcode.getText().length() > 5)
                        {
                            JLerreur.setText("Le contenu du champ Code postal est trop long !");
                        }
                        // Le nom de la ville est un VARCHAR(50)
                        else if (JTnom.getText().length() > 50)
                        {
                            JLerreur.setText("Le contenu du champ ville est trop long !");
                        }
                        else
                        {
                            JLerreur.setText("Echec requête. Vérifiez le contenu de vos champs.");
                        }
                    }
                    // L'insertion SQL a réussi mais pas la récupération du médecin en local. Cela ne devrai normalement jamais arriver...
                    else if (codeRequete == 1)
                    {
                        JLerreur.setText("Insertion BDD réussie mais impossible de récupérer la localité en local.");
                    }
                    // L'insertion s'est déroulée normalement.
                    else if (codeRequete == 2)
                    {
                        JLerreur.setText("Insertion réussie !");
                        JTcode.setText("");
                        JTnom.setText("");
                    }
                }
            }
            else
            {
                JLerreur.setText("Veuillez remplir tous les champs.");
            }
        }
    }
}
