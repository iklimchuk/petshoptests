package framework.pages;

import framework.interfaces.LandingPage;
import org.openqa.selenium.By;

public class ErrorPage extends BasePage implements LandingPage {

    public void open(){
        browser.click(By.linkText("Error"));
    }

    public Boolean isLogoVisible(){
        return browser.isVisible(By.xpath(".//img[contains(@src, 'pets.png')]"));
    }
}
