package ui.helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {
    private final TakesScreenshot screenshotTaker;
    private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + File.separator +
            ConfigReader.getString("screenshot.path");

    public ScreenshotHelper(WebDriver driver) {
        this.screenshotTaker = (TakesScreenshot) driver;
    }

    public byte[] getScreenshotAsByteArray() {
        return screenshotTaker.getScreenshotAs(OutputType.BYTES);
    }

    public BufferedImage getScreenshotAsBufferedImage() {
        try {
            File screenshotFile = takeScreenShots("screenshot");
            return ImageIO.read(screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot as BufferedImage", e);
        }
    }

    public File takeScreenShots(String picture) {
        try {
            File temp = screenshotTaker.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(temp, new File(SCREENSHOT_PATH + File.separator + picture));
            return temp;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }
    }
}