package gsb.service;

import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

public class MedicamentService 
{
    public static Medicament rechercherMedicament(String unCodeMedicament)
    {
		Medicament unMedicament = null;
		try{
		if (unCodeMedicament==null) {
            throw new Exception("Donnée obligatoire : code");
        }
		unMedicament = MedicamentDao.rechercher(unCodeMedicament);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return unMedicament;
	}

    public static ArrayList<String> getListeMedicaments()
    {
        ArrayList<String> laListe = new ArrayList<String>();
		try
        {
		    ArrayList<Medicament> medicaments = MedicamentDao.retournerCollectionDesMedicaments();
            for (Medicament medicament : medicaments)
            {
                laListe.add(medicament.getDepotLegal());
            }
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return laListe;
	}

}
