package ui.webactions;

import com.google.common.collect.Iterables;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import reports.LoggerUtils;

import java.util.Set;

public class WindowHandler extends BaseHandler {
    private final Logger logger = LoggerUtils.getLogger(this.getClass());
    private WebDriver currentDriver;

    public WindowHandler(WebDriver driver) {
        super(driver);
        this.currentDriver = driver;
    }

    public String getMainWindowHandle() {
        return driver.getWindowHandle();
    }

    public WebDriver switchToLatestWindow() {
        currentDriver = driver.switchTo().window(Iterables.getLast(driver.getWindowHandles()));
        logger.info("Switched to window: " + driver.getTitle());
        return driver;
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public WebDriver switchToWindow(String name) {
        currentDriver = driver.switchTo().window(name);
        logger.info("Switched to window: " + driver.getTitle());
        return driver;
    }

    public void closeWindow(String windowHandle) {
        switchToWindow(windowHandle).close();
    }

    public void cleanUp(WebDriver webDriver) {
        for (String handle : webDriver.getWindowHandles()) {
            switchToWindow(handle).close();
        }
    }

    public WebDriver getCurrentWindow() {
        return currentDriver;
    }

    public String getParentWindow() {
        return driver.getWindowHandle();
    }

    public void switchToParentWindow(String parentWindow) {
        switchToWindow(parentWindow);
    }

    public WebElement switchToModalDialog() {
        return driver.switchTo().activeElement();
    }

    public WebDriver switchToFrame(int index) {
        return driver.switchTo().frame(index);
    }

    public WebDriver switchToMainFrame() {
        return driver.switchTo().defaultContent();
    }

    public WebDriver switchToFrame(WebElement element) {
        return driver.switchTo().frame(element);
    }

    public WebDriver switchToFrame(String idOrName) {
        return driver.switchTo().frame(idOrName);
    }

    public WebDriver switchToParentFrame() {
        return driver.switchTo().parentFrame();
    }

    public WebDriver waitForFrameAndSwitchIt(int index) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        return driver;
    }

    public WebDriver waitForFrameAndSwitchIt(String idOrName) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
        return driver;
    }

    public WebDriver waitForFrameAndSwitchIt(WebElement element) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        return driver;
    }
}