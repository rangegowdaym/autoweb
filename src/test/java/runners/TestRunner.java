package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import reports.BDDReportBuilder;
import utils.ConfigReader;

import java.net.UnknownHostException;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        tags = "@Smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-reports.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
    static {
        try {
            String globalConfig = System.getProperty("user.dir") + "/src/test/resources/config/global.properties";
            String envConfig = System.getProperty("user.dir") + "/src/test/resources/config/" + System.getProperty("env") + ".properties";
            ConfigReader.loadAllProperties(globalConfig, envConfig);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration files", e);
        }
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    String testSuiteName;

    @BeforeTest
    public void getTestSuiteName(ITestContext context) {
        String suiteName = context.getCurrentXmlTest().getSuite().getName();
        System.out.println("Running Test Suite: " + suiteName);
        testSuiteName = suiteName;
    }

    @AfterSuite
    public void afterSuite() {
        BDDReportBuilder.generateCucumberReport(testSuiteName);
    }
}