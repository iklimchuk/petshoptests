package framework.pages;

import framework.interfaces.LandingPage;
import org.openqa.selenium.By;

public class HomePage extends BasePage implements LandingPage {

    public void open(){
        browser.click(By.linkText("Home"));
    }

    public Boolean isLogoVisible(){
        return browser.isVisible(By.xpath(".//img[contains(@src, 'pets.png')]"));
    }
}
