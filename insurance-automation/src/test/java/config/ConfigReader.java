package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Reads configuration from config.properties
 * Singleton pattern ensures single instance throughout test execution
 */
public class ConfigReader {

    private static Properties properties;
    private static ConfigReader instance;

    private ConfigReader() {
        loadProperties();
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public String getBrowser()           { return properties.getProperty("browser", "chrome"); }
    public boolean isHeadless()          { return Boolean.parseBoolean(properties.getProperty("headless", "false")); }
    public String getBaseUrl()           { return properties.getProperty("base.url"); }
    public int getImplicitWait()         { return Integer.parseInt(properties.getProperty("implicit.wait", "10")); }
    public int getExplicitWait()         { return Integer.parseInt(properties.getProperty("explicit.wait", "20")); }
    public int getPageLoadTimeout()      { return Integer.parseInt(properties.getProperty("page.load.timeout", "30")); }
    public String getTestDataPath()      { return properties.getProperty("test.data.path"); }
    public String getReportPath()        { return properties.getProperty("report.path"); }
    public String getScreenshotPath()    { return properties.getProperty("screenshot.path"); }
}
