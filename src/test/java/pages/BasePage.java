package pages;

import org.openqa.selenium.WebDriver;
import ui.webactions.ActionHandler;
import ui.webactions.BaseHandler;
import ui.webactions.ElementHandler;
import utils.ConfigReader;

public abstract class BasePage {
    protected WebDriver driver;
    protected BaseHandler baseHandler;
    protected ElementHandler elementHandler;
    protected ActionHandler actionHandler;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.baseHandler = new BaseHandler(driver);
        this.elementHandler = new ElementHandler(driver);
        this.actionHandler = new ActionHandler(driver);
    }

    public abstract boolean isPageLoaded();

    public void launchApplication(String url) {
        baseHandler.launchUrl(url);
    }
}
