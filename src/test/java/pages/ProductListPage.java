package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductListPage extends BasePage {
    WebDriver driver;
    By firstProduct = By.cssSelector(".product-layout .product-thumb h4 a");
    By addToCartBtn = By.cssSelector(".product-layout .product-thumb button[onclick*='cart.add']");

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return false;
    }

    public void addFirstProductToCart() {
        driver.findElement(addToCartBtn).click();
    }

    public void openFirstProduct() {
        driver.findElement(firstProduct).click();
    }
}