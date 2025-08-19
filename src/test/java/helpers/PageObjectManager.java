package helpers;

import org.openqa.selenium.WebDriver;
import pages.*;
import ui.driverfactory.DriverManager;

public class PageObjectManager {
    private WebDriver driver;

    public PageObjectManager() {
        this.driver = DriverManager.getInstance().getDriver();
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public RegistrationPage getRegistrationPage() {
        return new RegistrationPage(driver);
    }

    public CartPage getCartPage() {
        return new CartPage(driver);
    }

    public LoginPage getLoginPage() {
        return new LoginPage(driver);
    }

    public ProductListPage getProductListPage() {
        return new ProductListPage(driver);
    }

    public MyAccountPage getMyAccountPage() {
        return new MyAccountPage(driver);
    }
}
