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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import gsb.service.MedecinService;
import gsb.service.MedicamentService;
import gsb.service.VisiteurService;

/*
 * JIFVisiteModif - fenêtre de modification d'une visite
 * @author Caroline Jaffré
 */
public class JIFVisiteModif extends JInternalFrame implements ActionListener
{
    // Panels
    protected JPanel p;  
	protected JPanel pTexte;
    protected JPanel pTableau;
    protected JPanel pBoutons;
    protected JPanel pErreur;
    // Labels
    protected JLabel JLReference;
    protected JLabel JLDate;
    protected JLabel JLMatricule;
    protected JLabel JLCodeMedecin;
    protected JLabel JLCommentaire;

    // Champs
	protected JComboBox<String> JCReference;
    protected JTextField JTdateVisite;
    protected JComboBox<String>  JCMatricule;
    protected JComboBox<String> JCCodeMedecin;
    protected JTextField JTCommentaire;

    // Bouton
    protected JButton modifier;

    public JIFVisiteModif()
    {
        p = new JPanel(new GridLayout(3, 1));
        pTexte = new JPanel(new GridLayout(5, 5));
        pTableau = new JPanel();
        pBoutons = new JPanel();
        pErreur = new JPanel();
        

        JLReference = new JLabel("Reference");
        JLDate = new JLabel("Date");
        JLMatricule = new JLabel("Matricule");
        JLCodeMedecin = new JLabel("Code Medecin");
        JLCommentaire = new JLabel("Commentaire");

        JTCommentaire = new JTextField(20);
        JTdateVisite = new JTextField(20);

        JCReference = new JComboBox<String>();
        JCMatricule = new JComboBox<String>();
        JCCodeMedecin = new JComboBox<String>();

        modifier = new JButton("Modifier");


        ArrayList<String> references = MedicamentService.getListeMedicaments();
        for (String reference : references)
        {
            JCReference.addItem(reference);
        }
        ArrayList<String> matricules = VisiteurService.listeMatricules();
        for (String matricule : matricules)
        {
            JCMatricule.addItem(matricule);
        }
        ArrayList<String> codeMedecins = MedecinService.listeCodesMedecin();
        for (String codeMedecin : codeMedecins)
        {
            JCCodeMedecin.addItem(codeMedecin);
        }

        pTexte.add(JLReference);
        pTexte.add(JCReference);
        pTexte.add(JLDate);
        pTexte.add(JTdateVisite);
        pTexte.add(JLMatricule);
        pTexte.add(JCMatricule);
        pTexte.add(JLCodeMedecin);
        pTexte.add(JCCodeMedecin);
        pTexte.add(JLCommentaire);
        pTexte.add(JTCommentaire);

        pBoutons.add(modifier);

        p.add(pTexte);
        p.add(pTableau);
        p.add(pBoutons);
        p.add(pErreur);

        Container contentPane = getContentPane();
        contentPane.add(p);
        // déclaration des sources d'évènements
        modifier.addActionListener(this);
        JCReference.addActionListener(this);
        JCCodeMedecin.addActionListener(this);
        JCMatricule.addActionListener(this);


        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Mise a jour Visite");
        // ajout d'un écouteur d'évènement pour la fermeture de la fenêtre
        addInternalFrameListener(new InternalFrameAdapter()
        {
            public void  internalFrameClosing(InternalFrameEvent e) 
            {
                // le code que tu veux exécuter à la fermeture de la JInternalFrame
            }
        });
    }
    public void actionPerformed(ActionEvent evt) 
    {
         
    }
}
