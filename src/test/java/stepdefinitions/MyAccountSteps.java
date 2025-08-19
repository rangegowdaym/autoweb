package stepdefinitions;

import helpers.TestContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.MyAccountPage;

public class MyAccountSteps {
    private final TestContext testContext;
    private final MyAccountPage myAccountPage;
    private final Scenario scenario;

    public MyAccountSteps(TestContext testContext) {
        this.testContext = testContext;
        this.myAccountPage = testContext.getPageObjectManager().getMyAccountPage();
        this.scenario = testContext.getScenario();
    }

    @Then("I should see {string}")
    public void iShouldSee(String successMessage) {
        Assert.assertTrue(myAccountPage.isPageLoaded(), "My Account page is not loaded");
        String actualMessage = myAccountPage.getSuccessMessage();
        scenario.log("Actual success message: " + actualMessage);
        Assert.assertEquals(actualMessage, successMessage, "Success message does not match");
        scenario.log("Account is created successfully with message: " + successMessage);
        scenario.attach(testContext.getScreenshotHelper().getScreenshotAsByteArray(), "image/png", "screenshot");
    }

    @Then("I should see the logout button")
    public void iShouldSeeTheLogoutButton() {
        Assert.assertTrue(myAccountPage.isUserLoggedIn(), "User is not logged in, logout button is not displayed");
        scenario.log("Logout button is displayed, user is logged in");
        scenario.attach(testContext.getScreenshotHelper().getScreenshotAsByteArray(), "image/png", "screenshot");
    }
}