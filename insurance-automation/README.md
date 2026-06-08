# Insurance Web Automation Framework

A professional **Selenium + Java + TestNG** automation framework for testing insurance web application workflows including Quote Generation, Policy Creation, and Claim Submission.

---

## Tech Stack

| Tool | Purpose |
|---|---|
| Java 11 | Programming language |
| Selenium WebDriver 4.x | Browser automation |
| TestNG | Test execution & assertions |
| WebDriverManager | Auto browser driver management |
| Extent Reports | HTML test reporting |
| Apache POI | Excel data-driven testing |
| Log4j2 | Logging |
| Maven | Build & dependency management |

---

## Project Structure

```
insurance-automation/
├── src/test/java/
│   ├── config/
│   │   └── ConfigReader.java          # Reads config.properties
│   ├── pages/
│   │   ├── BasePage.java              # Reusable Selenium actions
│   │   ├── LoginPage.java             # Login page object
│   │   ├── QuotePage.java             # Quote generation page
│   │   ├── PolicyPage.java            # Policy creation page
│   │   └── ClaimPage.java             # Claim submission page
│   ├── tests/
│   │   ├── BaseTest.java              # Setup & teardown
│   │   ├── LoginTest.java             # Login test cases
│   │   ├── QuoteGenerationTest.java   # Quote test cases
│   │   ├── PolicyCreationTest.java    # Policy test cases
│   │   └── ClaimSubmissionTest.java   # Claim test cases
│   └── utils/
│       ├── DriverManager.java         # WebDriver lifecycle (ThreadLocal)
│       ├── ExtentReportListener.java  # Auto HTML report generation
│       ├── ExcelUtil.java             # Data-driven testing helper
│       ├── ScreenshotUtil.java        # Screenshot on failure
│       └── LoggerUtil.java            # Centralized logging
├── src/test/resources/
│   ├── config.properties              # Browser, URL, timeout settings
│   └── testng.xml                     # Test suite configuration
├── reports/                           # Generated HTML reports
└── pom.xml                            # Maven dependencies
```

---

## Design Patterns Used

- **Page Object Model (POM)** — separates test logic from UI interactions
- **Singleton Pattern** — ConfigReader & DriverManager
- **ThreadLocal** — supports parallel test execution
- **Data Driven Testing** — Excel-based test data via Apache POI

---

## How to Run

### Prerequisites
- Java 11+
- Maven 3.6+
- Chrome / Firefox / Edge browser

### Clone the Repository
```bash
git clone https://github.com/YOUR-USERNAME/insurance-automation.git
cd insurance-automation
```

### Run All Tests
```bash
mvn test
```

### Run Smoke Tests Only
```bash
mvn test -Dgroups=smoke
```

### Run with Different Browser
```bash
mvn test -Dbrowser=firefox
```

---

## Test Cases Covered

| Module | Test Case | Type |
|---|---|---|
| Login | Valid login | Smoke |
| Login | Invalid credentials | Regression |
| Login | Blank credentials | Regression |
| Login | Only username provided | Regression |
| Quote | Valid quote generation | Smoke |
| Quote | Quote number generation | Regression |
| Quote | Comprehensive vs Liability premium | Regression |
| Policy | Policy creation from quote | Regression |
| Policy | Policy status after binding | Regression |
| Claim | Valid claim submission | Regression |
| Claim | Invalid policy number | Regression |
| Claim | Unique claim numbers | Regression |

---

## Reports

After test execution, HTML report is generated at:
```
reports/ExtentReport.html
```
Screenshots of failed tests are saved at:
```
reports/screenshots/
```

---

## Author

**Vaishnav Ghugare**  
Automation & Quality Engineer  
[LinkedIn](https://linkedin.com/in/YOUR-PROFILE) | [GitHub](https://github.com/YOUR-USERNAME)
