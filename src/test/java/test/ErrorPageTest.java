package test;

import framework.pages.ErrorPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorPageTest extends BaseTest {
    private final ErrorPage errorPage = new ErrorPage();


    @Test
    public void pageLogo() {
        errorPage.open();
        Assert.assertTrue(errorPage.isLogoVisible());
    }

    @Test
    public void pageHeader() {
        errorPage.open();
        Assert.assertEquals(errorPage.getPageHeaderText(), "Something happened...");
    }


}
