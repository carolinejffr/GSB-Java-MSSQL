package gsb.tests.service;

import org.junit.Assert;
import org.junit.Before;

import gsb.modele.Stocker;
import gsb.modele.Visiteur;
import gsb.modele.Localite;
import gsb.modele.Medicament;
import gsb.service.StockService;
import junit.framework.*;


/**
 * @author Caroline Jaffré
 */
public class StockServiceTest extends TestCase
{
    Stocker stockTest;
    @Before
    public void beforeStock()
    {
        Localite localiteTest = new Localite("99999", "Test");
        Visiteur visiteurTest = new Visiteur("A999", "TEST", "test",
         "test", "test", "adresseTest", localiteTest, "9999999999", 
         "dateTest", 0, "999", "test");
        Medicament medicamentTest = new Medicament("ZZZ", "test", 
        "test", "test", "test", 1, 
        "test", "test");
        stockTest = new Stocker(1, visiteurTest, medicamentTest);
    }

    public void testRechercherStockReussi()
    {
        Assert.assertEquals(stockTest, StockService.rechercherStock("A999", "ZZZ"));
    }
    public void testRechercherStockEchec()
    {
        Assert.assertEquals(stockTest, StockService.rechercherStock("A001", "ABC"));
    }
}
