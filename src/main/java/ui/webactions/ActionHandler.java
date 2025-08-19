package ui.webactions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import reports.LoggerUtils;

public class ActionHandler extends BaseHandler {
    private final Logger logger = LoggerUtils.getLogger(this.getClass());
    private final Actions actions;

    public ActionHandler(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }

    public void click(Object locator) {
        performAction(locator, element -> actions.click(element).build().perform(),
                "Unable to click on the element: ");
    }

    public void doubleClick(Object locator) {
        performAction(locator, element -> actions.doubleClick(element).build().perform(),
                "Unable to double click on the element: ");
    }

    public void moveToElementAndClick(Object locator) {
        performAction(locator, element -> {
            actions.moveToElement(element).build().perform();
            element.click();
        }, "Unable to move to element and click on the element: ");
    }

    public void dragAndDrop(Object sourceLocator, Object targetLocator) {
        performAction(sourceLocator, sourceElement -> {
            WebElement targetElement = getElement(targetLocator);
            actions.dragAndDrop(sourceElement, targetElement).build().perform();
        }, "Unable to drag and drop from: " + sourceLocator + " to: " + targetLocator);
    }

    public void hoverOverElement(Object locator) {
        performAction(locator, element -> actions.moveToElement(element).build().perform(),
                "Unable to move to element: ");
    }

    public void rightClick(Object locator) {
        performAction(locator, element -> actions.contextClick(element).build().perform(),
                "Unable to right click on the element: ");
    }

    public void moveToElementAndSendKeys(Object locator, String keys) {
        performAction(locator, element -> actions.moveToElement(element).click().sendKeys(keys).build().perform(),
                "Unable to move to element and send keys: " + keys);
    }
}