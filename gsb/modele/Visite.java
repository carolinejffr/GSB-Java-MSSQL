package gsb.modele;

public class Visite 
{
    protected String reference;
    protected String date;
    protected String commentaire;
    protected Medecin unMedecin;
    protected Visiteur unVisiteur;

    public Visite(String reference, String date, String commentaire, Medecin unMedecin, Visiteur unVisiteur) 
    {
        this.reference = reference;
        this.date = date;
        this.commentaire = commentaire;
        this.unMedecin = unMedecin;
        this.unVisiteur = unVisiteur;
    }

    public String getReference() 
    {
        return reference;
    }

    public void setReference(String reference) 
    {
        this.reference = reference;
    }

    public String getDate() 
    {
        return date;
    }

    public void setDate(String date) 
    {
        this.date = date;
    }

    public String getCommentaire() 
    {
        return commentaire;
    }

    public void setCommentaire(String commentaire) 
    {
        this.commentaire = commentaire;
    }

    public Medecin getUnMedecin() 
    {
        return unMedecin;
    }

    public void setUnMedecin(Medecin unMedecin) 
    {
        this.unMedecin = unMedecin;
    }

    public Visiteur getUnVisiteur() 
    {
        return unVisiteur;
    }

    public void setUnVisiteur(Visiteur unVisiteur) 
    {
        this.unVisiteur = unVisiteur;
    }
}
