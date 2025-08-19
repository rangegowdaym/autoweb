package reports;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.presentation.PresentationMode;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigReader;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BDDReportBuilder {
    private static WebDriver webDriver;

    public BDDReportBuilder(WebDriver driver) {
        webDriver = driver;
    }

    public static void generateCucumberReport(String suiteName) {
        String filePath = System.getProperty("user.dir") + ConfigReader.getString("jsonfile.path");

        List<String> jsonFiles = new ArrayList<>();
        File[] fileList = getFileList(filePath);

        for (File file : fileList) {
            jsonFiles.add(file.getPath());
        }

        DateFormat dateFormat = new SimpleDateFormat("YYYY_MM_dd");
        Calendar cal = Calendar.getInstance();

        String reportsPath = System.getProperty("user.dir") + ConfigReader.getString("reports.path");
        String outputDirPath = reportsPath + File.separator + suiteName + "_" + dateFormat.format(cal.getTime());

        File reportOutputDirectory = new File(outputDirPath);
        String buildNumber = ConfigReader.getString("report.project.buildnumber");
        String projectName = ConfigReader.getString("report.project.name");
        boolean parallelTesting = false;
        Capabilities capabilities = ((RemoteWebDriver) webDriver).getCapabilities();
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);

        configuration.setBuildNumber(buildNumber);
        //configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
        configuration.addClassifications("Browser", capabilities.getBrowserName().toUpperCase());
        configuration.addClassifications("Browser Version", capabilities.getBrowserVersion());
        configuration.addClassifications("Java Version", System.getProperty("java.version").toUpperCase());
        configuration.addClassifications("Platform", System.getProperty("os.name").toUpperCase());
        //configuration.addClassifications("Host Name", InetAddress.getLocalHost().getHostName());

        new ReportBuilder(jsonFiles, configuration).generateReports();
    }

    private static File[] getFileList(String dirPath) {
        File dir = new File(dirPath);
        return dir.listFiles((dir1, name) -> name.endsWith(".json"));
    }
}