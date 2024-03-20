/*
* Créé le 22 mars 2012
* Fenêtre - Préférences - Java - Style de code - Modèles de code
*/
package gsb.vue;

import gsb.modele.Localite;
import gsb.modele.dao.VisiteurDao;
import gsb.modele.Visiteur;
import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.LocaliteDao;
import gsb.utils.ValidationUtils;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JIFVisiteur extends JInternalFrame {
    /**
     * Commentaire pour <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    protected JPanel p;
    protected JPanel pTexte;
    protected JPanel pBoutons;

    protected JLabel JLmatricule;
    protected JLabel JLnom;
    protected JLabel JLprenom;
    protected JLabel JLlogin;
    protected JLabel JLmdp;
    protected JLabel JLadresse;
    protected JLabel JLcp;
    protected JLabel JLdateEntree;

    protected JTextField JTmatricule;
    protected JTextField JTnom;
    protected JTextField JTprenom;
    protected JTextField JTlogin;
    protected JTextField JTmdp;
    protected JTextField JTadresse;
    protected JTextField JTcp;
    protected JTextField JTdateEntree;
    protected ArrayList<JTextField> champs;
    protected JComboBox<String> JCCodeUnite;
    

    public JIFVisiteur() {
        p = new JPanel(); // panneau principal de la fenêtre
        pBoutons = new JPanel(); // panneau supportant les boutons
        pTexte = new JPanel(new GridLayout(12, 2));

        JLmatricule = new JLabel("Matricule");
        JLnom = new JLabel("Nom");
        JLprenom = new JLabel("Prénom");
        JLlogin = new JLabel("Login");
        JLmdp = new JLabel("Mot De Passe");
        JLadresse = new JLabel("Adresse rue");
        JLcp = new JLabel("Code postal");
        JLadresse = new JLabel("Adresse");
        JLcp = new JLabel("Code Postal");
        JLdateEntree = new JLabel("Date Entrée au Format 0000-00-00 00:00:00");

        JTmatricule = new JTextField(20);
        JTmatricule.setMaximumSize(JTmatricule.getPreferredSize());
        JTnom = new JTextField();
        JTprenom = new JTextField();
        JTlogin = new JTextField();
        JTmdp = new JTextField();
        JTadresse = new JTextField();
        JTcp = new JTextField();
        JTdateEntree = new JTextField();
        JCCodeUnite = new JComboBox<String>();


        pTexte.add(JLmatricule);
        pTexte.add(JTmatricule);
        pTexte.add(JLnom);
        pTexte.add(JTnom);
        pTexte.add(JLprenom);
        pTexte.add(JTprenom);
        pTexte.add(JLlogin);
        pTexte.add(JTlogin);
        pTexte.add(JLmdp);
        pTexte.add(JTmdp);

        pTexte.add(JLadresse);
        pTexte.add(JTadresse);
        pTexte.add(JLcp);
        pTexte.add(JTcp);
        pTexte.add(JLdateEntree);
        pTexte.add(JTdateEntree);

        // mise en forme de la fenêtre

        p.add(pTexte);
        p.add(pBoutons);
        Container contentPane = getContentPane();
        contentPane.add(p);
        // Ajout d'une liste pour faciliter la verifChamps
        champs = new ArrayList<JTextField>();
        champs.add(JTmatricule);
        champs.add(JTnom);
        champs.add(JTprenom);
        champs.add(JTlogin);
        champs.add(JTmdp);
        // champs.add(JTtelephone);
        champs.add(JTadresse);
        champs.add(JTcp);
        champs.add(JTdateEntree);
        // champs.add(JTprime);
        //champs.add(JTnomUnite);

        pTexte.add(JLmatricule);
        pTexte.add(JTmatricule);
        pTexte.add(JLnom);
        pTexte.add(JTnom);
        pTexte.add(JLprenom);
        pTexte.add(JTprenom);
        pTexte.add(JLlogin);
        pTexte.add(JTlogin);
        pTexte.add(JLmdp);
        pTexte.add(JTmdp);
        pTexte.add(JLadresse);
        pTexte.add(JTadresse);
        pTexte.add(JLcp);
        pTexte.add(JTcp);
        pTexte.add(JLdateEntree);
        pTexte.add(JTdateEntree);
        pTexte.add(JCCodeUnite);
        JCCodeUnite.addItem("BO");
        JCCodeUnite.addItem("SW");
    }

    public void viderText() { // méthode qui permet de vider les zones de texte
        JTmatricule.setText("");
        JTnom.setText("");
        JTprenom.setText("");
        JTlogin.setText("");
        JTmdp.setText("");
        JTadresse.setText("");
        JTcp.setText("");
        JTdateEntree.setText("");
    }

    public void remplirText(Visiteur unVisiteur) { // méthode qui permet de vider les zones de texte
        JTmatricule.setText(unVisiteur.getMatricule());
        JTnom.setText(unVisiteur.getNom());
        JTprenom.setText(unVisiteur.getPrenom());
        JTlogin.setText(unVisiteur.getLogin());
        JTmdp.setText(unVisiteur.getMdp());
        JTadresse.setText(unVisiteur.getAdresse());
        JTcp.setText(unVisiteur.getUneLocalite().getCodePostal());
        JTdateEntree.setText(unVisiteur.getDateEntree());
        JCCodeUnite.setSelectedItem(unVisiteur.getCodeUnite());
    }

    public Localite testLocalite() {
        Localite laLocalite = null;
        try {
            if (ValidationUtils.isCodePostalValide(JTcp.getText())) {
                laLocalite = LocaliteDao.rechercher(JTcp.getText());
            } else {
                laLocalite = null;
            }
        }

        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Avant envoie " + laLocalite);
        return laLocalite;
    }

    /**
     * Renvoie true si le Matricule existe déja en base et false sinon
     * 
     * @author Léo Tallec--Éven
     *
     * @return boolean
     */
    public boolean verifMatricule() {
        boolean sortie = false;
        String laRequete = "SELECT `MATRICULE` FROM gsbv2.`VISITEUR` WHERE `MATRICULE` LIKE '" + (String)(JTmatricule.getText()) + "'";

        try {
            String SQL = (ConnexionMySql.execReqSelection(laRequete)).toString();
            if(SQL ==JTmatricule.getText()){
                sortie = true;
            }
            else{
                sortie = false;
            }
        } catch (Exception e) {
            System.out.println(e);
            sortie = false;
        }
        ConnexionMySql.fermerConnexionBd();
        return sortie;

    }
    public String testLongueur()
    {
        String laString = null;

        if (JTnom.getText().length() > 50)
        {
            laString = " Nom ";
        }
        else if (JTprenom.getText().length() > 50)
        {
            laString = " Prenom ";
        }
        else if (JTadresse.getText().length() > 50)
        {
            laString = " Adresse ";
        }
        else if (JTmatricule.getText().length() > 50)
        {
            laString = " Matricule ";
        }
        else if (JTlogin.getText().length() > 50)
        {
            laString = " Login ";
        }
        else if (JTmdp.getText().length() > 20)
        {
            laString = " Mot de Passe ";
        }
        else if (JTcp.getText().length() > 5)
        {
            laString = " Code Postal ";
        }
        return laString;
    }
    /**
     * La fonction vérifie que l'heure est valide grace a ValidationUtils.isDateHourValide(date)
     * True si la date est invalide
     * False si elle est valide
     * 
     * @author Tallec--Éven Léo
     * @return boolean
     */
    public boolean verifDateHeure() {
        if(ValidationUtils.isDateHourValide(JTdateEntree.getText())){
            return false;
        }
        else{
            return true;
        }
    }   
    public int ajoutVisiteurBDD() {

        int codeRequete = 0;
        // On commence par récupérer toutes les valeurs
        ArrayList<String> StringChamps = new ArrayList<String>();
        for (int i = 0; i < champs.size(); i++) {
            System.out.println(champs.get(i).getText() + "A l'index : " + i);
            StringChamps.add(champs.get(i).getText());
            // Ceci permet d'éviter un bug SQL si l'un des champs contient une apostrophe
            if (StringChamps.get(i).contains("'")) {
                StringChamps.set(i, StringChamps.get(i).replace("'", "\\'"));
            }
        }

        // On créé la requête SQL. J'utilise String.format car je trouve ça plus lisible
        // quand il y a autant de variables.
        String NomUnit;
        if (JCCodeUnite.getSelectedItem() == "BO") {
                NomUnit = "Bourdin";
        }
        else{
            NomUnit = "SWISS";
        }
        String laRequete = String.format(
                "INSERT INTO `VISITEUR` (`MATRICULE`, `NOM`, `PRENOM`,`LOGIN`,`MDP`, `ADRESSE`, `CODEPOSTAL`, `DATEENTREE`, `CODEUNIT`,`NOMUNIT`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s','%s','%s','%s');",

                StringChamps.get(0), // Matricule
                StringChamps.get(1), // Nom
                StringChamps.get(2), // Prenom
                StringChamps.get(3), // Login
                StringChamps.get(4), // Mdp
                StringChamps.get(5), // Adresse
                StringChamps.get(6), // Code Postal
                StringChamps.get(7), // Date Entrée
                JCCodeUnite.getSelectedItem(),//
                NomUnit 
                );

        System.out.println(laRequete);
        int reqMaj = ConnexionMySql.execReqMaj(laRequete);
        ConnexionMySql.fermerConnexionBd();

        // Si la requête a aboutie, on ajoute le visiteur en local.
        if (reqMaj == 1) {
            codeRequete = 1;
            Visiteur leVisiteur = VisiteurDao.rechercher(StringChamps.get(0));
            if (leVisiteur != null) {
                codeRequete = 2;
            }
        }

        return codeRequete;
    }
}
