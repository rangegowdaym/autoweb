package helpers;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import pojo.RegistrationPojo;
import reports.BDDReportBuilder;
import ui.helpers.ScreenshotHelper;
import utils.ConfigReader;

import java.io.File;

public class TestContext {
    private PageObjectManager pageObjectManager;
    private Scenario scenario;
    private WebDriver driver;
    private ScreenshotHelper screenshotHelper;
    private BDDReportBuilder bddReportBuilder;
    private RegistrationPojo registrationDetails;

    public PageObjectManager getPageObjectManager() {
        if (pageObjectManager == null) {
            pageObjectManager = new PageObjectManager();
        }
        return pageObjectManager;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public ScreenshotHelper getScreenshotHelper() {
        return screenshotHelper;
    }

    public void setScreenshotHelper(WebDriver driver) {
        this.screenshotHelper = new ScreenshotHelper(driver);
    }

    public File getResourcePath(String filePath) {
        String testDataPath = ConfigReader.getString("testdata.path");
        if (testDataPath == null || testDataPath.isEmpty()) {
            testDataPath = "src/test/resources/";
        }
        return new File(System.getProperty("user.dir") + "/" + testDataPath + filePath);
    }

    public void setReportBuilder(WebDriver driver) {
        this.bddReportBuilder = new BDDReportBuilder(driver);
    }

    public void setRegistrationDetails(RegistrationPojo registrationDetails) {
        this.registrationDetails = registrationDetails;
    }

    public RegistrationPojo getRegistrationDetails() {
        return registrationDetails;
    }
}