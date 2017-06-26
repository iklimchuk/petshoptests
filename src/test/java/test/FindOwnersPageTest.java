package test;

import framework.entities.Owner;
import framework.pages.FindOwnersPage;
import framework.pages.OwnerInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindOwnersPageTest extends BaseTest {
    private final FindOwnersPage findOwnersPage = new FindOwnersPage();
    private final OwnerInfoPage ownerInfoPage = new OwnerInfoPage();

    @Test
    public void pageHeader() {
        findOwnersPage.open();
        Assert.assertEquals(findOwnersPage.getPageHeaderText(), "Find Owners");
    }

    @Test
    public void findAllOwners() {
        findOwnersPage.search(null);
        Assert.assertFalse(findOwnersPage.notFoundMessageIsDisplayed());
        Assert.assertTrue(findOwnersPage.searchResultsTableIsDisplayed());
        Assert.assertTrue(findOwnersPage.getOwnersCount() >= 10);
    }

    @Test
    public void findNonExistingOwner() {
        findOwnersPage.search("nonExistingLastName");
        Assert.assertTrue(findOwnersPage.notFoundMessageIsDisplayed());
    }

    @Test
    public void findOwnerByLastNamePartInTheMiddle() {
        Owner owner = new Owner("LongSurName");
        owner.create();
        findOwnersPage.search("Sur");
        Assert.assertTrue(findOwnersPage.notFoundMessageIsDisplayed());
    }

    @Test
    public void findOwnerByLastNameWithWrongCapitalization() {
        Owner owner = new Owner("LASTNAME");
        owner.create();
        findOwnersPage.search("lastname");
        Assert.assertTrue(findOwnersPage.notFoundMessageIsDisplayed());
    }

    @Test
    public void findOwnerByPartiallyMatchingLastName() {
        Owner ownerWithLongLastName = new Owner("longLastName");
        ownerWithLongLastName.create();
        findOwnersPage.search("long");
        Assert.assertFalse(findOwnersPage.notFoundMessageIsDisplayed());
    }

    @Test
    public void findOwnersWithSameLastName() {
        findOwnersPage.search("Davis");
        Assert.assertTrue(findOwnersPage.searchResultsTableIsDisplayed());
        Assert.assertTrue(findOwnersPage.getOwnersCount() == 2);
    }

    @Test
    public void findParticularOwner() {
        Owner owner = new Owner();
        owner.create();
        findOwnersPage.search(owner.lastName());
        Assert.assertTrue(ownerInfoPage.isDisplayed());
    }

}
