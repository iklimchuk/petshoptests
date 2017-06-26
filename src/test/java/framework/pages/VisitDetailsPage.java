package framework.pages;

import framework.interfaces.DetailsPage;
import org.openqa.selenium.By;

public class VisitDetailsPage extends BasePage implements DetailsPage {
    @Override
    public void save() {
        browser.click(By.xpath(".//button[@type='submit']"));
    }

    public void setDate(String date) {
        browser.type(By.id("date"), date);
        browser.click(By.tagName("body"));
    }

    public void setDescription(String description) {
        browser.type(By.id("description"), description);
    }
}
