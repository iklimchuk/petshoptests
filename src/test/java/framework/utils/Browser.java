package framework.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static final String osName = System.getProperty("os.name").toLowerCase();
    private final ChromeDriver driver;

    Browser() {
        String chromeDriverFileName = "chromedriver";
        if (isWindows()) {
            chromeDriverFileName = "chromedriver.exe";
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverFileName);
        driver = new ChromeDriver(setChromeOptions());
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    private static boolean isWindows() {
        return (osName.contains("win"));
    }

    private static ChromeOptions setChromeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--no-sandbox");
        return options;
    }

    public void click(By locator) {
        for (int i = 1; i <= 10; i++) {
            try {
                buildWebElement(locator).click();
                break;
            } catch (InvalidArgumentException ex) {
                System.out.println("[EX] InvalidArgumentException on click");
            } catch (StaleElementReferenceException ex) {
                System.out.println("[EX] StaleElementReferenceException on click");
            } catch (WebDriverException ex) {
                System.out.println("[EX] Element is not clickable at point");
            }
        }
    }

    private WebElement buildWebElement(By locator) {
        waitFor(locator);
        return driver.findElement(locator);
    }

    private void waitFor(By locator) {
        Wait fluentWait = new FluentWait(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    void closeBrowser() {
        driver.quit();
    }

    public String getElementTitle(By locator) {
        String text = "";
        for (int i = 1; i <= 10; i++) {
            try {
                text = buildWebElement(locator).getText();
                break;
            } catch (StaleElementReferenceException ex) {
                System.out.println("[EX] StaleElementReferenceException on getTitle");
            }
        }
        return text;
    }

    public List<WebElement> getElementsList(By locator) {
        return driver.findElements(locator);
    }

    public String getPageSourceCode() {
        return driver.getPageSource();
    }

    public Boolean isVisible(By locator) {
        return isPresent(locator) && buildWebElement(locator).isDisplayed();
    }

    public Boolean isPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public int itemsCount(By locator) {
        return driver.findElements(locator).size();
    }

    void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void navigate(String URL) {
        driver.get(URL);
    }

    public void type(By locator, String text) {
        for (int i = 1; i <= 10; i++) {
            try {
                WebElement element = buildWebElement(locator);
                element.clear();
                element.sendKeys(text);
                break;
            } catch (StaleElementReferenceException ex) {
                System.out.println("[EX] StaleElementReferenceException on typing");
            } catch (ElementNotInteractableException ex) {
                System.out.println("[EX] ElementNotInteractableException on typing");
            } catch (InvalidElementStateException ex) {
                System.out.println("[EX] InvalidElementStateException on typing");
            }
        }
    }

}
