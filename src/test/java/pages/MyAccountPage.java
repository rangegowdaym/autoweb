package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {
    private static final By ACCOUNT_CREATION_SUCCESS_MESSAGE = By.cssSelector("#common-success #content h1.page-title");
    private static final By LOGOUT_LINK = By.cssSelector("a.list-group-item[href*='route=account/logout']");


    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return elementHandler.isElementDisplayed(ACCOUNT_CREATION_SUCCESS_MESSAGE);
    }

    public String getSuccessMessage() {
        return elementHandler.getElementText(ACCOUNT_CREATION_SUCCESS_MESSAGE);
    }

    public boolean isUserLoggedIn() {
        return elementHandler.isElementDisplayed(LOGOUT_LINK);
    }
}
