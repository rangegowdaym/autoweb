package stepdefinitions;

import helpers.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import ui.driverfactory.DriverManager;
import utils.ConfigReader;

public class CommonSteps {
    private final TestContext testContext;
    private WebDriver driver;
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    public CommonSteps(TestContext testContext) {
        this.testContext = testContext;
        testContext.setDriver(DriverManager.getInstance().getDriver());
        this.driver = testContext.getDriver();
        this.homePage = testContext.getPageObjectManager().getHomePage();
        this.loginPage = testContext.getPageObjectManager().getLoginPage();
        this.registrationPage = testContext.getPageObjectManager().getRegistrationPage();
        testContext.setReportBuilder(this.driver);
        testContext.setScreenshotHelper(testContext.getDriver());
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
        if (driver != null) {
            DriverManager.getInstance().quitDriver();
        }
    }

    @Given("I launch the LambdaTest eCommerce Playground")
    public void iLaunchTheLambdaTestECommercePlayground() {
        String pageUrl = ConfigReader.getString("application.url");
        homePage.launchApplication(pageUrl);
        testContext.getScenario().log("Launched application: " + pageUrl);
    }

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        homePage.navigateToRegistrationPage();
        Assert.assertTrue(registrationPage.isPageLoaded(), "Registration page is not loaded");
    }

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        homePage.navigateToLoginPage();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        testContext.getScenario().attach(testContext.getScreenshotHelper().getScreenshotAsByteArray(), "image/png", "login_page_screenshot");
        testContext.getScenario().log("Opened the login page");
    }

    @When("I enter username as {string} and password as {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        loginPage.enterCredentials(username, password);
        testContext.getScenario().log("Entered username: " + username + " and password: " + password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
        testContext.getScenario().log("Clicked the login button");
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String errorMessage) {
        String actualMessage = loginPage.getErrorMessage();
        testContext.getScenario().log("Actual error message: " + actualMessage);
        Assert.assertEquals(actualMessage, errorMessage, "Error message does not match");
        testContext.getScenario().log("Error message is displayed as expected: " + errorMessage);
        testContext.getScenario().attach(testContext.getScreenshotHelper().getScreenshotAsByteArray(), "image/png", "screenshot");
    }

}