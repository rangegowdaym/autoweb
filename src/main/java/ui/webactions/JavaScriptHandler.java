package ui.webactions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import reports.LoggerUtils;

public class JavaScriptHandler extends BaseHandler {
    private final Logger logger = LoggerUtils.getLogger(this.getClass());
    private final JavascriptExecutor jsExecutor;

    public JavaScriptHandler(WebDriver driver) {
        super(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public void scrollToElement(Object locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void javaScriptClick(Object locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
    }

    public void scrollToElementAndClick(Object locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", getElement(locator));
    }

    public void mouseHoverJScript(Object locator) {
        String script = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');" +
                "evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} " +
                "else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
        jsExecutor.executeScript(script, getElement(locator));
    }

    public void highlightElement(Object locator) {
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", getElement(locator),
                "color: red; border: 10px solid yellow;");
    }

    public void sendKeysWithJS(Object locator, String text) {
        jsExecutor.executeScript("arguments[0].value = arguments[1];", getElement(locator), text);
    }

    public void scrollToPageBottom() {
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToPageTop() {
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    public String getElementInnerText(Object locator) {
        return (String) jsExecutor.executeScript("return arguments[0].innerText;", getElement(locator));
    }

    public String getElementAttribute(Object locator, String attribute) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute(arguments[1]);", getElement(locator), attribute);
    }

    public void clearElement(Object locator) {
        jsExecutor.executeScript("arguments[0].value = '';", getElement(locator));
    }

    public void zoomPage(String scale) {
        jsExecutor.executeScript("document.body.style.zoom = arguments[0];", scale);
    }

    public void scrollToElementAndHighlight(Object locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true); arguments[0].setAttribute('style', 'color: red; border: 10px solid yellow;');", getElement(locator));
    }

    public void scrollToElementAndSendKeys(Object locator, String keys) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true); arguments[0].value = arguments[1];", getElement(locator), keys);
    }


}