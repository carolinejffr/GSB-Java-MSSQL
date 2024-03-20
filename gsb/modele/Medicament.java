package gsb.modele;

public class Medicament 
{
    protected String depotLegal;
    protected String nomCommercial;
    protected String composition;
    protected String effets;
    protected String contreIndication;
    protected float prixEchantillon;
    protected String codeFamille;
    protected String libellefamille;

    public Medicament(String depotLegal, String nomCommercial, String composition, String effets,
            String contreIndication, float prixEchantillon, String codeFamille, String libellefamille) 
    {
        this.depotLegal = depotLegal;
        this.nomCommercial = nomCommercial;
        this.composition = composition;
        this.effets = effets;
        this.contreIndication = contreIndication;
        this.prixEchantillon = prixEchantillon;
        this.codeFamille = codeFamille;
        this.libellefamille = libellefamille;
    }
    public String getDepotLegal() 
    {
        return depotLegal;
    }
    public void setDepotLegal(String depotLegal) 
    {
        this.depotLegal = depotLegal;
    }
    public String getNomCommercial() 
    {
        return nomCommercial;
    }
    public void setNomCommercial(String nomCommercial) 
    {
        this.nomCommercial = nomCommercial;
    }
    public String getComposition() 
    {
        return composition;
    }
    public void setComposition(String composition) 
    {
        this.composition = composition;
    }
    public String getEffets() 
    {
        return effets;
    }
    public void setEffets(String effets) 
    {
        this.effets = effets;
    }
    public String getContreIndication() 
    {
        return contreIndication;
    }
    public void setContreIndication(String contreIndication) 
    {
        this.contreIndication = contreIndication;
    }
    public float getPrixEchantillon() 
    {
        return prixEchantillon;
    }
    public void setPrixEchantillon(float prixEchantillon) 
    {
        this.prixEchantillon = prixEchantillon;
    }
    public String getCodeFamille() 
    {
        return codeFamille;
    }
    public void setCodeFamille(String codeFamille) 
    {
        this.codeFamille = codeFamille;
    }
    public String getLibellefamille() 
    {
        return libellefamille;
    }
    public void setLibellefamille(String libellefamille) 
    {
        this.libellefamille = libellefamille;
    }
}
