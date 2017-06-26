package framework.utils;

import framework.pages.BasePage;
import test.BaseTest;

public class Helper {
    public static void initializeBrowser() {
        Browser browser = new Browser();
        BasePage.setBrowser(browser);
        browser.maximizeWindow();
        browser.navigate(BaseTest.loginURL);
    }

    public static void shutDownBrowser() {
        BasePage.browser().closeBrowser();
    }

}
