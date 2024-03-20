package gsb.modele;
/*
 * @author Caroline Jaffré
 */
public class Stocker 
{
    protected int qteStock;
    public Visiteur unVisiteur;
    public Medicament unMedicament;

    public Stocker(int qteStock, Visiteur unVisiteur, Medicament unMedicament) 
    {
        this.qteStock = qteStock;
        this.unVisiteur = unVisiteur;
        this.unMedicament = unMedicament;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    public Visiteur getUnVisiteur() {
        return unVisiteur;
    }

    public void setUnVisiteur(Visiteur unVisiteur) {
        this.unVisiteur = unVisiteur;
    }

    public Medicament getUnMedicament() {
        return unMedicament;
    }

    public void setUnMedicament(Medicament unMedicament) {
        this.unMedicament = unMedicament;
    }
}
