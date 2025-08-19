package stepdefinitions;

import com.github.javafaker.Faker;
import helpers.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.RegistrationPage;
import pojo.RegistrationPojo;
import utils.JsonUtils;

public class RegistrationSteps {
    private final TestContext testContext;
    private final RegistrationPage registrationPage;
    private static final String REGISTRATION_DETAILS_FILE = "registration.json";

    public RegistrationSteps(TestContext testContext) {
        this.testContext = testContext;
        this.registrationPage = testContext.getPageObjectManager().getRegistrationPage();
    }

    private void log(String message, Object details) {
        String logMessage = details != null ? message + JsonUtils.toJson(details) : message;
        testContext.getScenario().log(logMessage);
    }

    private void log(String message) {
        testContext.getScenario().log(message);
    }


    @Given("I have the registration details")
    public void iHaveTheRegistrationDetails() {
        testContext.setRegistrationDetails(JsonUtils.fromJson(
                testContext.getResourcePath(REGISTRATION_DETAILS_FILE),
                RegistrationPojo.class));
    }

    @When("I fill in valid registration details")
    public void iFillInValidRegistrationDetails() {
        log("Registration details: ", testContext.getRegistrationDetails());
        registrationPage.fillRegistrationForm(testContext.getRegistrationDetails());
    }

    @And("I select a newsletter subscription option as {string}")
    public void iSelectANewsletterSubscriptionOptionAs(String subscriptionOption) {
        registrationPage.selectNewsLetterSubscription(Boolean.parseBoolean(subscriptionOption));
        log("Newsletter subscription option selected: ", subscriptionOption);
    }

    @And("I agree to the Privacy Policy")
    public void iAgreeToThePrivacyPolicy() {
        registrationPage.agreeToPrivacyPolicy();
        log("Agreed to the Privacy Policy");
    }

    @And("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        registrationPage.clickContinue();
        log("Submitted the registration form");
    }

    @When("I fill in registration details with {string} as {string}")
    public void iFillInRegistrationDetailsWithAs(String fieldName, String value) {
        RegistrationPojo registrationDetails = registrationPage.updateRegistrationDetails(
                fieldName, value, JsonUtils.fromJson(
                        testContext.getResourcePath(REGISTRATION_DETAILS_FILE),
                        RegistrationPojo.class
                )
        );
        log("Updated registration details: ", registrationDetails);
        registrationPage.fillRegistrationForm(registrationDetails);
    }

    @Then("I should see an error message {string} for the field {string}")
    public void iShouldSeeAnErrorMessage(String errorMessage, String fieldName) {
        String actualErrorMessage = registrationPage.getErrorMessage(fieldName);
        log("Actual error message: ", actualErrorMessage);
        Assert.assertEquals(actualErrorMessage, errorMessage, "Error message does not match");
        log("Error message verified: ", errorMessage);
    }

    @Then("I should see warning message {string}")
    public void iShouldSeeWarningMessage(String warningMessage) {
        String actualWarningMessage = registrationPage.getWarningMessage();
        log("Actual warning message: ", actualWarningMessage);
        Assert.assertEquals(actualWarningMessage, warningMessage, "Warning message does not match");
        log("Warning message verified: ", warningMessage);
    }

    @And("I update the email address to {string}")
    public void iUpdateTheEmailAddressTo(String email) {
        RegistrationPojo registrationDetails = testContext.getRegistrationDetails();
        Faker faker = new Faker();
        registrationDetails.setEmail(faker.internet().emailAddress());
        testContext.setRegistrationDetails(registrationDetails);
        log("Updated email address to: ", registrationDetails.getEmail());
    }
}