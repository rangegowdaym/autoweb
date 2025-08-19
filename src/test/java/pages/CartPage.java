package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    By cartTotal = By.id("cart-total");
    By checkoutBtn = By.cssSelector("a[href*='checkout']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return false;
    }

    public String getCartTotal() {
        return driver.findElement(cartTotal).getText();
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutBtn).click();
    }
}