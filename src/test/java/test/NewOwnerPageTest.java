package test;

import framework.pages.FindOwnersPage;
import framework.pages.OwnerDetailsPage;
import framework.pages.OwnerInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewOwnerPageTest extends BaseTest {
    private final FindOwnersPage findOwnersPage = new FindOwnersPage();
    private final OwnerDetailsPage ownerDetailsPage = new OwnerDetailsPage();
    private final OwnerInfoPage ownerInfoPage = new OwnerInfoPage();

    @Test
    public void pageHeader(){
        findOwnersPage.open();
        findOwnersPage.newOwner();
        Assert.assertEquals(ownerDetailsPage.getPageHeaderText(), "New Owner");
    }

    @Test
    public void emptyFieldsValidation(){
        findOwnersPage.open();
        findOwnersPage.newOwner();
        ownerDetailsPage.save();
        Assert.assertFalse(ownerInfoPage.isDisplayed());
        Assert.assertTrue(ownerDetailsPage.firstNameValidationMessageIsDisplayed());
        Assert.assertTrue(ownerDetailsPage.lastNameValidationMessageIsDisplayed());
        Assert.assertTrue(ownerDetailsPage.addressValidationMessageIsDisplayed());
        Assert.assertTrue(ownerDetailsPage.cityValidationMessageIsDisplayed());
        Assert.assertTrue(ownerDetailsPage.phoneValidationMessageIsDisplayed());
    }

    @Test
    public void phoneNumberLengthValidation(){
        findOwnersPage.open();
        findOwnersPage.newOwner();
        ownerDetailsPage.setPhoneNumber("123456789012345");
        ownerDetailsPage.save();
        Assert.assertTrue(ownerDetailsPage.phoneValidationMessageIsDisplayed());
    }

    @Test
    public void phoneNumberContentValidation(){
        findOwnersPage.open();
        findOwnersPage.newOwner();
        ownerDetailsPage.setPhoneNumber("test string");
        ownerDetailsPage.save();
        Assert.assertTrue(ownerDetailsPage.phoneValidationMessageIsDisplayed());
    }


}
