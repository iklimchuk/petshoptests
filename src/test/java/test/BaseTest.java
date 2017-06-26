package test;

import framework.utils.Helper;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.collections.Lists;

import java.util.List;
import java.util.Random;

public abstract class BaseTest {
    public static final String TEST_ID = String.valueOf(new Random().nextInt(100));
    public static String loginURL;

    public static void main(String[] args) {
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add(args[0]);
        testng.setTestSuites(suites);
        testng.run();
    }

    @BeforeTest()
    public static void setUpTest() {
        Helper.initializeBrowser();
    }

    @AfterTest()
    public static void tearDownTest() {
        Helper.shutDownBrowser();
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters("loginURL")
    public void setUp(String loginURL) {
        BaseTest.loginURL = loginURL;
    }
}