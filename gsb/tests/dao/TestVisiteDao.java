package gsb.tests.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;
import junit.framework.*;

public class TestVisiteDao extends TestCase {
    Medecin med1 = MedecinDao.retournerCollectionDesMedecins().get(0);
    Visiteur vis1 = VisiteurDao.retournerCollectionDesVisiteurs().get(0);
    Visite visite = new Visite("ref", "12/12/2015", "commentaire", med1, vis1);
    @Before
    public void setUp() {
        VisiteDao.ajouter(visite);
    }

    @After
    public void tearDown() {
        VisiteDao.supprimer(visite.getReference());
    }

    public void testRechercher() {
        // On recherche la visite de test
        Visite result = VisiteDao.rechercher(visite.getReference());

        Assert.assertEquals(result.getReference(), visite.getReference());
        Assert.assertEquals(result.getDate(), visite.getDate());
        Assert.assertEquals(result.getCommentaire(), visite.getCommentaire());
        Assert.assertEquals(result.getUnMedecin().getCodeMed(), visite.getUnMedecin().getCodeMed());
        Assert.assertEquals(result.getUnVisiteur().getMatricule(), visite.getUnVisiteur().getMatricule());

        // On essaie de trouver une visite qui n'existe pas
        result = VisiteDao.rechercher("aaa");

        Assert.assertNull(result);
    }
}
