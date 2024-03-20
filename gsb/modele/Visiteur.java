package gsb.modele;

public class Visiteur 
{
    
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String login;
    protected String mdp;
    protected String adresse;
    protected Localite uneLocalite;
    protected String telephone;
    protected String dateEntree;
    protected int prime;
    protected String codeUnite;
    protected String nomUnite;
    

    public Visiteur(String matricule, String nom, String prenom, String login, String mdp, String adresse,
            Localite uneLocalite, String telephone, String dateEntree, int prime, String codeUnite, String nomUnite) 
    {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.adresse = adresse;
        this.uneLocalite = uneLocalite;
        this.telephone = telephone;
        this.dateEntree = dateEntree;
        this.prime = prime;
        this.codeUnite = codeUnite;
        this.nomUnite = nomUnite;
    }

    public String getMatricule() 
    {
        return matricule;
    }
    public void setMatricule(String matricule) 
    {
        this.matricule = matricule;
    }
    public String getNom() 
    {
        return nom;
    }
    public void setNom(String nom) 
    {
        this.nom = nom;
    }
    public String getPrenom() 
    {
        return prenom;
    }
    public void setPrenom(String prenom) 
    {
        this.prenom = prenom;
    }
    public String getLogin() 
    {
        return login;
    }
    public void setLogin(String login) 
    {
        this.login = login;
    }
    public String getMdp() 
    {
        return mdp;
    }
    public void setMdp(String mdp) 
    {
        this.mdp = mdp;
    }
    public String getAdresse() 
    {
        return adresse;
    }
    public void setAdresse(String adresse) 
    {
        this.adresse = adresse;
    }
    public Localite getUneLocalite() 
    {
        return uneLocalite;
    }
    public void setUneLocalite(Localite uneLocalite) 
    {
        this.uneLocalite = uneLocalite;
    }
    public String getTelephone() 
    {
        return telephone;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }
    public String getDateEntree() 
    {
        return dateEntree;
    }
    public void setDateEntree(String dateEntree) 
    {
        this.dateEntree = dateEntree;
    }
    public int getPrime() 
    {
        return prime;
    }
    public void setPrime(int prime) 
    {
        this.prime = prime;
    }
    public String getCodeUnite() 
    {
        return codeUnite;
    }
    public void setCodeUnite(String codeUnite) 
    {
        this.codeUnite = codeUnite;
    }
    public String getNomUnite() 
    {
        return nomUnite;
    }
    public void setNomUnite(String nomUnite) 
    {
        this.nomUnite = nomUnite;
    }
}
