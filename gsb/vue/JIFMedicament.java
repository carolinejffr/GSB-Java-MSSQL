package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.ConnexionMySql;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JIFMedicament extends JInternalFrame  {
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;


	protected JLabel JLdepot;
	protected JLabel JLnomCom;
	protected JLabel JLcompo;
	protected JLabel JLeffets;
	protected JLabel JLcontre;
    protected JLabel JLprix;
    protected JLabel JLcodeFam;
    protected JLabel JLlibFam;
    

	protected JTextField JTdepot;
	protected JTextField JTnomCom;
	protected JTextField JTcompo;
	protected JTextField JTeffets;
	protected JTextField JTcontre;
    protected JTextField JTprix;
    protected JTextField JTcodeFam;
    protected JTextField JTlibFam;

    // Ajout d'une liste pour faciliter la verifChamps
    protected ArrayList<JTextField> champs;
	
    public JIFMedicament() {
    	p = new JPanel(new GridLayout(1,2));  // panneau principal de la fenêtre
        pBoutons = new JPanel();    // panneau supportant les boutons
        pTexte = new JPanel(new GridLayout(8,2));
    	
         JLdepot = new JLabel("Dépôt légal");
         JLnomCom = new JLabel("Nom commercial");
         JLcompo = new JLabel("Composition");
         JLeffets = new JLabel("Effets");
         JLcontre = new JLabel("Contre indications");
         JLprix = new JLabel("Prix");
         JLcodeFam = new JLabel("Code famille");
         JLlibFam = new JLabel("Libellé famille");
         
         JTdepot = new JTextField(20);
         JTdepot.setMaximumSize(JTdepot.getPreferredSize());
         JTnomCom = new JTextField();
         JTcompo = new JTextField();
         JTeffets = new JTextField();    
         JTcontre = new JTextField();
         JTprix = new JTextField();
         JTcodeFam = new JTextField();
         JTlibFam = new JTextField();

         // Instanciation de l'ArrayList et ajouts de tous les champs utilisés pour l'ajout d'un médicament.
         champs = new ArrayList<JTextField>();
         champs.add(JTnomCom);
         champs.add(JTcompo);
         champs.add(JTeffets);
         champs.add(JTcontre);
         champs.add(JTprix);
         champs.add(JTcodeFam);
         champs.add(JTlibFam);
         
         pTexte.add(JLdepot);
         pTexte.add(JTdepot);
         pTexte.add(JLnomCom);
         pTexte.add(JTnomCom);
         pTexte.add(JLcompo);
         pTexte.add(JTcompo);
         pTexte.add(JLeffets);
         pTexte.add(JTeffets);
         pTexte.add(JLcontre);
         pTexte.add(JTcontre);
         pTexte.add(JLprix);
         pTexte.add(JTprix);
         pTexte.add(JLcodeFam);
         pTexte.add(JTcodeFam);
         pTexte.add(JLlibFam);
         pTexte.add(JTlibFam);
		
        // mise en forme de la fenêtre

         p.add(pTexte);
         p.add(pBoutons);
         add(p);

	}
    
    public void remplirTextMedi(Medicament unMedicament) 	
    {  // méthode qui permet de remplir les zones de texte à partir des valeurs passées en paramètres
        JTdepot.setText(unMedicament.getDepotLegal());     
        JTnomCom.setText(unMedicament.getNomCommercial());
        JTcompo.setText(unMedicament.getComposition());
        JTeffets.setText(unMedicament.getEffets());    
        JTcontre.setText(unMedicament.getContreIndication());
        JTprix.setText(Float.toString(unMedicament.getPrixEchantillon()));
        JTcodeFam.setText(unMedicament.getCodeFamille());
        JTlibFam.setText(unMedicament.getLibellefamille());
     }

      public void viderText() 	
    {  // méthode qui permet de vider les zones de texte 
        JTdepot.setText("");        
        JTnomCom.setText("");
        JTcompo.setText("");
        JTeffets.setText("");    
        JTcontre.setText("");
        JTprix.setText("");
        JTcodeFam.setText("");
        JTlibFam.setText("");
     }

    /**
    * Vérifie si tous les champs ont été correctement rempli
    * Elle permet aussi de mettre en forme les champs
    * @return true si tous les champs sont bons, false s'il y a un champ vide.
    */
    public boolean verifChamps()
    {
        boolean verif = true;
        // On vérifie qu'aucun champ n'est null
        for (int i = 0; i < champs.size(); i++)
        {
            // A l'exception du champ potentiel car visiblement dans la base de données c'est vide par défaut.
            if (champs.get(i).getText().isEmpty() == true && champs.get(i) != JTprix)
            {
                // S'il est null, return false et print index
                verif = false;
                System.out.println("Le champ à l'index numéro " + i + " est vide !");
                System.out.println(i + " : " + champs.get(i).getText());
            }
        }
        return verif;
    }

    public boolean verifFloat(String champ)
    {
        boolean verif = true;

        try
        {
            Float.parseFloat(champ);
        }
        catch(NumberFormatException e)
        {
            verif = false;
        } 

        return verif;
    }

    /**
    * Cette méthode est appellée si VerifChamp est correct, dans JIFMedicamentAjout.
    * Elle récupère toutes les valeurs des champs et envoie la requête SQL d'insertion du médicament
    */
    public int ajoutMedicamentBDD()
    {
        int codeRequete = 0;
        // On commence par récupérer toutes les valeurs
        ArrayList<String> StringChamps = new ArrayList<String>();

        for (int i = 0; i < champs.size(); i++)
        {
            StringChamps.add(champs.get(i).getText());
            // Ceci permet d'éviter un bug SQL si l'un des champs contient une apostrophe
            if (StringChamps.get(i).contains("'"))
            {
                StringChamps.set(i, StringChamps.get(i).replace("'", "\\'"));
            }
        }
        
        // On a besoin de générer le CodeMed.
        ArrayList<Medicament> lesMedicaments = MedicamentDao.retournerCollectionDesMedicaments();
        int numCode = lesMedicaments.size() + 1;
        String codeMed = ("m" + String.format("%03d", numCode));
        System.out.println(codeMed);

        // On créé la requête SQL. J'utilise String.format car je trouve ça plus lisible quand il y a autant de variables.
        String laRequete = String.format("INSERT INTO `medicament` (`MED_DEPOTLEGAL`, `MED_NOMCOMMERCIAL`, `MED_COMPOSITION`, `MED_EFFETS`, `MED_CONTREINDIC`, `MED_PRIXECHANTILLON`, `FAM_CODE`, `FAM_LIBELLE`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
         codeMed, StringChamps.get(0), StringChamps.get(1), StringChamps.get(2), StringChamps.get(3), StringChamps.get(4), StringChamps.get(5), StringChamps.get(6));
        System.out.println(laRequete);
        int reqMaj = ConnexionMySql.execReqMaj(laRequete);
        ConnexionMySql.fermerConnexionBd();

        // Si la requête a aboutie, on ajoute le médicament en local.
        if (reqMaj == 1)
        {
            codeRequete = 1;
            Medicament leMedicament = MedicamentDao.rechercher(codeMed);
            if (leMedicament != null)
            {
                codeRequete = 2;
            }
        }
        
        return codeRequete;
    }

    /**
     * Cette fonction vérifié si le contenu des champs est de la bonne longueur, pour respecter ce qui est en BDD.
     * @author Thomas Calloch
     */
    public String testLongueur()
    {
        String laString = null;

        if (JTdepot.getText().length() > 10)
        {
            laString = "Dépôt légal";
        }
        else if (JLnomCom.getText().length() > 25)
        {
            laString = "Nom commercial";
        }
        else if (JTcompo.getText().length() > 255)
        {
            laString = "Composition";
        }
        else if (JTeffets.getText().length() > 255)
        {
            laString = "Effets";
        }
        else if (JLcontre.getText().length() > 255)
        {
            laString = "Contre indications";
        }
        else if (JTcodeFam.getText().length() > 3)
        {
            laString = "Code famille";
        }
        else if (JLlibFam.getText().length() > 80)
        {
            laString = "Libellé famille";
        }


        return laString;
    }

    
}
