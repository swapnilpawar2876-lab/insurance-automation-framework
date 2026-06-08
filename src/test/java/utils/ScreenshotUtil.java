package utils;

import config.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtil - Captures and saves screenshots on test failure
 */
public class ScreenshotUtil {

    private static final ConfigReader config = ConfigReader.getInstance();

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = config.getScreenshotPath();
        String screenshotPath = screenshotDir + testName + "_" + timestamp + ".png";

        try {
            new File(screenshotDir).mkdirs();
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
            LoggerUtil.info("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            LoggerUtil.error("Failed to capture screenshot: " + e.getMessage());
        }

        return screenshotPath;
    }
}
