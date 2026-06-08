package tests;

import config.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverManager;
import utils.LoggerUtil;

/**
 * BaseTest - Parent class for all Test classes
 * Handles browser setup and teardown via TestNG annotations
 */
public class BaseTest {

    protected ConfigReader config = ConfigReader.getInstance();

    @BeforeMethod
    public void setUp() {
        LoggerUtil.info("========== TEST SETUP STARTED ==========");
        DriverManager.initDriver();
        DriverManager.getDriver().get(config.getBaseUrl());
        LoggerUtil.info("Navigated to: " + config.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        LoggerUtil.info("========== TEST TEARDOWN STARTED ==========");
        DriverManager.quitDriver();
    }
}
