package helpers;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;
import ui.driverfactory.DriverManager;

public class Hooks {
    private final TestContext testContext;
    private WebDriver driver;
    private HomePage homePage;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
        testContext.setDriver(DriverManager.getInstance().getDriver());
        this.driver = testContext.getDriver();
        this.homePage = testContext.getPageObjectManager().getHomePage();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        testContext.setScenario(scenario);
        System.out.println("Starting scenario: " + testContext.getScenario().getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario != null && scenario.isFailed()) {
            testContext.getScenario().attach(testContext.getScreenshotHelper().getScreenshotAsByteArray(), "image/png", "screenshot");
        }
        System.out.println("Finished scenario: " + testContext.getScenario().getName());
    }
}