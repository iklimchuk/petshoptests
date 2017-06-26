package framework.pages;

import framework.utils.Browser;
import org.openqa.selenium.By;

public abstract class BasePage {
    static Browser browser;

    public static Browser browser() {
        return browser;
    }

    public static void setBrowser(Browser browser) {
        BasePage.browser = browser;
    }

    public String getPageHeaderText() {
        return browser.getElementTitle(By.xpath(".//h2"));
    }

}
