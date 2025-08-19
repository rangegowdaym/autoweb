package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    WebDriver driver;
    private static final By EMAIL_INPUT = By.id("input-email");
    private static final By PASSWORD_INPUT = By.id("input-password");
    private static final By LOGIN_BUTTON = By.cssSelector("input[type='submit'][value='Login']");
    private static final By LOGIN_SUCCESS_MESSAGE = By.cssSelector(".alert.alert-success");
    private static final By LOGIN_ERROR_MESSAGE = By.cssSelector(".alert.alert-danger");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return elementHandler.isElementDisplayed(LOGIN_BUTTON);
    }

    public void enterCredentials(String username, String password) {
        elementHandler.enterText(EMAIL_INPUT, username);
        elementHandler.enterText(PASSWORD_INPUT, password);
    }

    public void clickLoginButton() {
        elementHandler.clickElement(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        return elementHandler.getElementText(LOGIN_ERROR_MESSAGE);
    }
}