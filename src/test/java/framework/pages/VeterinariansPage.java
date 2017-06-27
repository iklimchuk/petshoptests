package framework.pages;

import framework.entities.Veterinarian;
import framework.interfaces.LandingPage;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.openqa.selenium.By;
import test.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class VeterinariansPage extends BasePage implements LandingPage {
    public List<Veterinarian> getAllVets() {
        open();
        int vetsCount = browser.itemsCount(By.xpath(".//h2/following-sibling::table[contains(@class, 'table-stripped')]//tbody/tr"));
        List<Veterinarian> veterinarianList = new ArrayList<>();
        for (int rowNumber = 1; rowNumber <= vetsCount; rowNumber++) {
            veterinarianList.add(getVetFromTableRow(rowNumber));
        }
        return veterinarianList;
    }

    public void open() {
        browser.click(By.linkText("Veterinarians"));
    }

    private static Veterinarian getVetFromTableRow(int rowNumber) {
        String rowLocator = String.format(".//h2/following-sibling::table[contains(@class, 'table-stripped')]//tbody/tr[%d]", rowNumber);
        String firstAndLastName = browser.getElementTitle(By.xpath(rowLocator + "/td[1]"));
        String specialties = browser.getElementTitle(By.xpath(rowLocator + "/td[2]"));
        String firstName = firstAndLastName.substring(0, firstAndLastName.indexOf(" "));
        String lastName = firstAndLastName.substring(firstAndLastName.indexOf(" ") + 1);
        return new Veterinarian(firstName, lastName, specialties);
    }

    public void openXmlView() {
        browser.click(By.linkText("View as XML"));
    }

    public boolean resultsTableIsDisplayed() {
        return browser.isVisible(By.xpath(".//h2/following-sibling::table[@class!='footer']"));
    }

    public boolean xmlContentMatchesUiVetList(List<Veterinarian> veterinarianList) {
        String pageSourceCodeHTML = browser.getPageSourceCode();
        List<TagNode> nodesList = getXmlNodesFromHtml(pageSourceCodeHTML);
        int matchesCount = 0;
        for (Veterinarian vet : veterinarianList) {
            for (TagNode node : nodesList) {
                if (vet.equals(getVetFromXmlNode(node))) {
                    matchesCount++;
                    break;
                }
            }
        }
        browser.navigate(BaseTest.loginURL);
        return matchesCount == veterinarianList.size() && matchesCount == nodesList.size();
    }

    private List<TagNode> getXmlNodesFromHtml(String htmlSource) {
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode node = cleaner.clean(htmlSource);
        List<TagNode> nodesList = node.getAllElementsList(true);
        nodesList.removeIf(listItem -> !listItem.getName().equals("vetlist"));
        return nodesList;
    }

    private Veterinarian getVetFromXmlNode(TagNode node) {
        String firstName = node.findElementByName("firstname", false).getAllChildren().get(0).toString();
        String lastName = node.findElementByName("lastname", false).getAllChildren().get(0).toString();
        StringBuilder specialties = new StringBuilder();
        List<TagNode> specialtiesNode = node.getElementListByName("name", true);
        if (specialtiesNode.size() == 0) {
            specialties = new StringBuilder("none");
        } else if (specialtiesNode.size() == 1) {
            specialties = new StringBuilder(specialtiesNode.get(0).getAllChildren().get(0).toString());
        } else {
            int itemsCount = 0;
            for (TagNode spec : specialtiesNode) {
                if (++itemsCount > 1) {
                    specialties.append(" ");
                }
                specialties.append(spec.getAllChildren().get(0).toString());
            }
        }
        return new Veterinarian(firstName, lastName, specialties.toString());
    }
}
