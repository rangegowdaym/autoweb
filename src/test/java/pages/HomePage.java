package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By logo = By.cssSelector("a[title='Poco Electro']");
    private By searchBox = By.cssSelector("#main-header input[name='search']");
    private By searchButton = By.cssSelector(".widget-search div.search-button button.type-text");
    private By myAccountLink = By.cssSelector("li.dropdown-hoverable a[href*='/account']");
    private By registrationLink = By.cssSelector("li.dropdown-hoverable a[href*='/register']");
    private By loginLink = By.cssSelector("li.dropdown-hoverable a[href*='/login']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return elementHandler.isElementDisplayed(logo);
    }

    public void searchProduct(String product) {
        //driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchButton).click();
    }

    public void navigateToLoginPage() {
        actionHandler.hoverOverElement(myAccountLink);
        elementHandler.isElementDisplayed(loginLink);
        elementHandler.clickElement(loginLink);
    }

    public void navigateToRegistrationPage() {
        actionHandler.hoverOverElement(myAccountLink);
        elementHandler.isElementDisplayed(registrationLink);
        elementHandler.clickElement(registrationLink);
    }
}