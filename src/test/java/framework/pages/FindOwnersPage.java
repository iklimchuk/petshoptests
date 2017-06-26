package framework.pages;

import framework.interfaces.LandingPage;
import org.openqa.selenium.By;

public class FindOwnersPage extends BasePage implements LandingPage {
    public int getOwnersCount() {
        return browser.itemsCount(By.xpath(".//td[descendant::a[contains(@href, '/petclinic/owners/')]]"));
    }

    public void newOwner() {
        browser.click(By.linkText("Add Owner"));
    }

    public boolean notFoundMessageIsDisplayed() {
        return browser.isVisible(By.xpath(".//span[text()='has not been found']"));
    }

    public boolean ownerPresentInTheList(String firstName, String lastName) {
        return browser.isVisible(By.linkText(firstName + " " + lastName));
    }

    public void search(String lastName) {
        open();
        setSearchLastName(lastName);
        submitSearch();
    }

    @Override
    public void open() {
        browser.click(By.linkText("Find owners"));
    }

    private void setSearchLastName(String lastName) {
        if (lastName != null) {
            browser.type(By.xpath(".//input[@id='lastName']"), lastName);
        }
    }

    private void submitSearch() {
        browser.click(By.xpath(".//button[text()='Find Owner']"));
    }

    public boolean searchResultsTableIsDisplayed() {
        return browser.isVisible(By.xpath(".//h2[text()='Owners']/following-sibling::table[@class!='footer']"));
    }

    public void showOwnerDetails(String firstName, String lastName) {
        browser.click(By.linkText(firstName + " " + lastName));
    }
}
