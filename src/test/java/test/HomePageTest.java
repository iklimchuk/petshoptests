package test;

import framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    private final HomePage homePage = new HomePage();

    @Test
    public void pageLogo() {
        homePage.open();
        Assert.assertTrue(homePage.isLogoVisible());
    }

    @Test
    public void pageHeader() {
        homePage.open();
        Assert.assertEquals(homePage.getPageHeaderText(), "Welcome");
    }
}
