package framework.pages;

import framework.enums.PetType;
import framework.interfaces.DetailsPage;
import org.openqa.selenium.By;

public class PetDetailsPage extends BasePage implements DetailsPage {
    public void setName(String name) {
        browser.type(By.id("name"), name);
    }

    public void setBirthDate(String birthDate) {
        browser.type(By.id("birthDate"), birthDate);
        browser.click(By.tagName("body"));
    }

    public void setType(PetType petType) {
        browser.click(By.xpath(String.format(".//option[text()='%s']", petType.petTypeName())));
    }

    public void save() {
        browser.click(By.xpath(".//button[@type='submit']"));
    }

}
