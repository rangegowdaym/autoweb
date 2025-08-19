package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pojo.RegistrationPojo;

public class RegistrationPage extends BasePage {
    private static final By REGISTRATION_HEADER = By.cssSelector("#account-register ol li[aria-current='page']");
    private static final By FIRST_NAME_INPUT = By.id("input-firstname");
    private static final By LAST_NAME_INPUT = By.id("input-lastname");
    private static final By EMAIL_INPUT = By.id("input-email");
    private static final By TELEPHONE_INPUT = By.id("input-telephone");
    private static final By PASSWORD_INPUT = By.id("input-password");
    private static final By CONFIRM_PASSWORD_INPUT = By.id("input-confirm");
    private static final By NEWSLETTER_NO_RADIO = By.cssSelector("label[for='input-newsletter-no']");
    private static final By NEWSLETTER_YES_RADIO = By.cssSelector("label[for='input-newsletter-yes']");
    private static final By PRIVACY_POLICY_CHECKBOX = By.cssSelector("label[for='input-agree']");
    private static final By CONTINUE_BUTTON = By.cssSelector("input[value='Continue']");
    private static final By WARNING_MESSAGE = By.cssSelector(".alert.alert-danger");
    private static final By FIELD_ERROR_MESSAGE = By.cssSelector("#account .text-danger");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return elementHandler.isElementDisplayed(REGISTRATION_HEADER);
    }

    public void fillRegistrationForm(RegistrationPojo registrationData) {
        elementHandler.enterText(FIRST_NAME_INPUT, registrationData.getFirstName());
        elementHandler.enterText(LAST_NAME_INPUT, registrationData.getLastName());
        elementHandler.enterText(EMAIL_INPUT, registrationData.getEmail());
        elementHandler.enterText(TELEPHONE_INPUT, registrationData.getTelephone());
        elementHandler.enterText(PASSWORD_INPUT, registrationData.getPassword());
        elementHandler.enterText(CONFIRM_PASSWORD_INPUT, registrationData.getConfirmPassword());
    }

    public void selectNewsLetterSubscription(boolean subscribe) {
        elementHandler.clickElement(subscribe ? NEWSLETTER_YES_RADIO : NEWSLETTER_NO_RADIO);
    }

    public void agreeToPrivacyPolicy() {
        elementHandler.clickElement(PRIVACY_POLICY_CHECKBOX);
    }

    public void clickContinue() {
        elementHandler.clickElement(CONTINUE_BUTTON);
    }

    public RegistrationPojo updateRegistrationDetails(String fieldName, String value, RegistrationPojo registrationDetails) {
        switch (fieldName.toLowerCase()) {
            case "firstname" -> registrationDetails.setFirstName(value);
            case "lastname" -> registrationDetails.setLastName(value);
            case "email" -> registrationDetails.setEmail(value);
            case "telephone" -> registrationDetails.setTelephone(value);
            case "password" -> registrationDetails.setPassword(value);
            case "confirm password" -> registrationDetails.setConfirmPassword(value);
            default -> throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
        return registrationDetails;
    }

    public String getErrorMessage(String fieldName) {
        if(fieldName.equalsIgnoreCase("email")) {
            return elementHandler.getElementText(By.cssSelector("#input-email + .text-danger"));
        } else if(fieldName.equalsIgnoreCase("telephone")) {
            return elementHandler.getElementText(By.cssSelector("#input-telephone + .text-danger"));
        } else if(fieldName.equalsIgnoreCase("password")) {
            return elementHandler.getElementText(By.cssSelector("#input-password + .text-danger"));
        } else if(fieldName.equalsIgnoreCase("confirm password")) {
            return elementHandler.getElementText(By.cssSelector("#input-confirm + .text-danger"));
        }
        return elementHandler.getElementText(FIELD_ERROR_MESSAGE);
    }

    public String getWarningMessage() {
        return elementHandler.getElementText(WARNING_MESSAGE);
    }
}