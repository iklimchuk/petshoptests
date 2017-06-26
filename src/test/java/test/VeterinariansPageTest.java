package test;

import framework.entities.Veterinarian;
import framework.pages.VeterinariansPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VeterinariansPageTest extends BaseTest {
    private final VeterinariansPage veterinariansPage = new VeterinariansPage();

    @Test
    public void vetsListIsDisplayed() {
        veterinariansPage.open();
        Assert.assertTrue(veterinariansPage.resultsTableIsDisplayed());
    }

    @Test
    public void pageHeader() {
        veterinariansPage.open();
        Assert.assertEquals(veterinariansPage.getPageHeaderText(), "Veterinarians");
    }

    @Test
    public void compareVetsListWithXml(){
        List<Veterinarian> veterinarianList = veterinariansPage.getAllVets();
        veterinariansPage.openXmlView();
        Assert.assertTrue(veterinariansPage.xmlContentMatchesUiVetList(veterinarianList));
    }

}
