package ui.webactions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reports.LoggerUtils;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BaseHandler {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerUtils.getLogger(BaseHandler.class);
    private final int GLOBAL_TIMEOUT = ConfigReader.getInt("global.timeout");

    public BaseHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(GLOBAL_TIMEOUT));
    }

    public WebElement getElement(Object locator) {
        try {
            WebElement element = (locator instanceof By)
                    ? wait.until(ExpectedConditions.presenceOfElementLocated((By) locator))
                    : wait.until(ExpectedConditions.visibilityOf((WebElement) locator));
            return element;
        } catch (WebDriverException e) {
            handleException(e, "Element not found: " + locator);
            return null;
        }
    }

    public List<WebElement> getElements(Object locator) {
        try {
            List<WebElement> elements = (locator instanceof By)
                    ? wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) locator))
                    : wait.until(ExpectedConditions.visibilityOfAllElements((WebElement) locator));
            if (elements.isEmpty()) {
                logger.error("Elements not found: {}", locator);
                throw new WebDriverException("Elements not found: " + locator);
            }
            return elements;
        } catch (WebDriverException e) {
            handleException(e, "Elements not found: " + locator);
            return null;
        }
    }

    public void launchUrl(String url) {
        logger.info("Launching URL: {}", url);
        try {
            driver.get(url);
            logger.info("URL launched successfully: {}", url);
        } catch (WebDriverException e) {
            handleException(e, "Failed to launch URL: " + url);
        }
    }

    public void refreshPage() {
        logger.info("Refreshing the page");
        try {
            driver.navigate().refresh();
            logger.info("Page refreshed successfully");
        } catch (WebDriverException e) {
            handleException(e, "Failed to refresh the page");
        }
    }

    public boolean waitForElementInvisible(Object locator) {
        return performFunctionAction(locator, element -> wait.until(ExpectedConditions.invisibilityOf(element)),
                "Element not invisible: " + locator);
    }

    public boolean waitForTextToBePresentInElement(Object locator, String text) {
        return performFunctionAction(locator, element -> wait.until(ExpectedConditions.textToBePresentInElement(element, text)),
                "Text not found in element: " + locator + " with text: " + text);
    }

    public WebElement waitForElementToBeClickable(Object locator) {
        return performFunctionAction(locator, element -> {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element is clickable: {}", element.toString());
            return element;
        }, "Element not clickable: " + locator);
    }

    protected void performAction(Object locator, Action action, String errorMessage) {
        performFunctionAction(locator, element -> {
            action.apply(element);
            return null;
        }, errorMessage);
    }

    protected <R> R performFunctionAction(Object locator, Function<WebElement, R> action, String errorMessage) {
        try {
            WebElement element = getElement(locator);
            return action.apply(element);
        } catch (WebDriverException e) {
            handleException(e, errorMessage);
            return null;
        }
    }

    protected void performConsumerAction(Object locator, Consumer<WebElement> action, String errorMessage) {
        try {
            WebElement element = getElement(locator);
            action.accept(element);
        } catch (WebDriverException e) {
            logger.error(errorMessage + locator + "\n" + e.getMessage());
            throw new WebDriverException(errorMessage + locator + "\n" + e);
        }
    }

    protected void handleException(WebDriverException e, String message) {
        logger.error(message + e);
        throw new WebDriverException(message + e);
    }

    @FunctionalInterface
    protected interface Action {
        void apply(WebElement element);
    }
}