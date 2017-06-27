package framework.pages;

import framework.entities.Pet;
import framework.entities.Visit;
import framework.interfaces.DetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OwnerDetailsPage extends BasePage implements DetailsPage {
    public void addVisitForPet(String name) {
        browser.click(By.xpath(String.format(".//dd[text()='%s']/ancestor::td/following-sibling::td//a[text()='Add Visit']", name)));
    }

    public boolean addressValidationMessageIsDisplayed() {
        return browser.isVisible(By.xpath(".//input[@id='address']/following-sibling::span"));
    }

    public boolean cityValidationMessageIsDisplayed() {
        return browser.isVisible(By.xpath(".//input[@id='city']/following-sibling::span"));
    }

    public boolean petIsPresentInTheList(Pet pet) {
        boolean listContainsPet = false;
        List<WebElement> petsTableItemsList = browser.getElementsList(By.xpath(".//table[@class='table']"));
        for (WebElement tableItem : petsTableItemsList) {
            String tableItemText = tableItem.getText();
            String petName = tableItemText.substring(5, tableItemText.indexOf("Birth") - 1);
            if (petName.equals(pet.name())) {
                listContainsPet = true;
                break;
            }
        }
        return listContainsPet;
    }

    public boolean findVisitInTheList(Visit visit, Pet pet) {
        boolean listContainsVisit = false;
        List<WebElement> visitsTableItemsList = browser.getElementsList(By.xpath
                (String.format(".//dd[text()='%s']/ancestor::td/following-sibling::td//td[2]", pet.name())));
        for (WebElement tableItem : visitsTableItemsList) {
            String visitDescription = tableItem.getText();
            if (visitDescription.equals(visit.description())) {
                listContainsVisit = true;
                break;
            }
        }
        return listContainsVisit;
    }

    public boolean firstNameValidationMessageIsDisplayed() {
        return browser.isVisible(By.xpath(".//input[@id='firstName']/following-sibling::span"));
    }

    public boolean lastNameValidationMessageIsDisplayed() {
        return browser.isVisible(By.xpath(".//input[@id='lastName']/following-sibling::span"));
    }

    public boolean phoneValidationMessageIsDisplayed() {
        return browser.isVisible(By.xpath(".//input[@id='telephone']/following-sibling::span"));
    }

    public void setAddress(String address) {
        browser.type(By.id("address"), address);
    }

    public void setCity(String city) {
        browser.type(By.id("city"), city);
    }

    public void setFirstName(String firstName) {
        browser.type(By.id("firstName"), firstName);
    }

    public void setLastName(String lastName) {
        browser.type(By.id("lastName"), lastName);

    }

    public void setPhoneNumber(String phoneNumber) {
        browser.type(By.id("telephone"), phoneNumber);
    }

    public void save() {
        browser.click(By.xpath(".//button[@type='submit']"));
    }

}
