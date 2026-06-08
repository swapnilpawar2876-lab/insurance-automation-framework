package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.ConfigReader;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * ExtentReportListener - TestNG listener that auto-generates Extent HTML reports
 */
public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final ConfigReader config = ConfigReader.getInstance();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(config.getReportPath());
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Insurance Automation Report");
        sparkReporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Project", "Insurance Automation Framework");
        extent.setSystemInfo("Tester", "Vaishnav Ghugare");
        extent.setSystemInfo("Browser", config.getBrowser());
        extent.setSystemInfo("Environment", "QA");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.set(test);
        LoggerUtil.info("TEST STARTED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
        LoggerUtil.info("TEST PASSED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());

        // Capture screenshot on failure
        String screenshotPath = ScreenshotUtil.captureScreenshot(
                DriverManager.getDriver(),
                result.getMethod().getMethodName()
        );
        extentTest.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        LoggerUtil.error("TEST FAILED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable().getMessage());
        LoggerUtil.warn("TEST SKIPPED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        LoggerUtil.info("Extent Report generated at: " + config.getReportPath());
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
}
