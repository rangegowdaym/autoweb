package ui.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class DriverManager {
    private static DriverManager instance;
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static synchronized DriverManager getInstance() {
        if (instance == null) instance = new DriverManager();
        return instance;
    }

    public WebDriver getDriver() {
        if (driver.get() == null) driver.set(createDriver());
        return driver.get();
    }

    public void quitDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
        }
    }

    private WebDriver createDriver() {
        String env = System.getProperty("platform", "local").toUpperCase();
        String browser = System.getProperty("browser", "chrome").toUpperCase();
        BrowserType browserType = BrowserType.valueOf(browser);

        return switch (EnvironmentType.valueOf(env)) {
            case SAUCE_LABS, BROWSER_STACK -> createRemoteDriver(env.toLowerCase());
            default -> createLocalDriver(browserType);
        };
    }

    private WebDriver createLocalDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME -> {
                // options = new ChromeOptions();
                //options.addArguments("--user-data-dir=/tmp/chrome-profile-" + UUID.randomUUID(), "--start-maximized");
                return new ChromeDriver();
            }
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--start-maximized");
                return new FirefoxDriver(options);
            }
            case SAFARI -> {
                return new SafariDriver(new SafariOptions());
            }
            case EDGE -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                return new EdgeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    private WebDriver createRemoteDriver(String provider) {
        try {
            ChromeOptions options = new ChromeOptions();
            String url = buildRemoteUrl(provider, options);
            options.setCapability("name", ConfigReader.getString(provider + ".testName"));
            return new RemoteWebDriver(new URL(url), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid remote URL", e);
        }
    }

    private String buildRemoteUrl(String provider, ChromeOptions options) {
        String username = ConfigReader.getString(provider + ".username");
        String accessKey = ConfigReader.getString(provider + ".accessKey");
        String urlBase = provider.equals("sauce") ? "ondemand.saucelabs.com" : "hub-cloud.browserstack.com";

        options.setBrowserVersion(ConfigReader.getString(provider + ".browserVersion"));
        options.setPlatformName(ConfigReader.getString(provider + ".platform"));
        return "https://" + username + ":" + accessKey + "@" + urlBase + "/wd/hub";
    }
}