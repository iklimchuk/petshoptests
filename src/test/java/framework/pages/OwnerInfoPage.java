package framework.pages;

import org.openqa.selenium.By;

public class OwnerInfoPage extends BasePage {
    public void addNewPet() {
        browser.click(By.linkText("Add New Pet"));
    }

    public void editPet(String name) {
        browser.click(By.xpath(String.format(".//dd[text()='%s']/ancestor::td/following-sibling::td//a[text()='Edit Pet']", name)));
    }

    public boolean isDisplayed(){
        return browser.isVisible(By.xpath(".//h2[text()='Owner Information']"));
    }

    public String ownerName() {
        return browser.getElementTitle(By.xpath(".//th[text()='Name']/following-sibling::td/b"));
    }

    public void editOwner() {
        browser.click(By.linkText("Edit Owner"));
    }

}
