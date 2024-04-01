package gsb.modele;

public class Podium 
{
    protected String rang;
    protected String matricule;
    protected String nom;
    protected String prenom;
    
    public Podium(String rang, String matricule, String nom, String prenom, int hebdo, int mensuel) {
        this.rang = rang;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.hebdo = hebdo;
        this.mensuel = mensuel;
    }
    public String getRang() {
        return rang;
    }
    public void setRang(String rang) {
        this.rang = rang;
    }
    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getHebdo() {
        return hebdo;
    }
    public void setHebdo(int hebdo) {
        this.hebdo = hebdo;
    }
    public int getMensuel() {
        return mensuel;
    }
    public void setMensuel(int mensuel) {
        this.mensuel = mensuel;
    }
    protected int hebdo;
    protected int mensuel;
}
