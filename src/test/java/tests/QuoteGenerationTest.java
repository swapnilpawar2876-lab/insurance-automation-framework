package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.QuotePage;
import utils.LoggerUtil;

/**
 * QuoteGenerationTest - Test cases for Insurance Quote Generation
 * Covers: valid quote, mandatory field validation, premium calculation
 */
public class QuoteGenerationTest extends BaseTest {

    // ── TC_QUOTE_001 ──────────────────────────────────────────────
    @Test(groups = {"smoke", "regression"},
          description = "Verify successful quote generation with valid vehicle details")
    public void verifyQuoteGenerationWithValidData() {
        LoggerUtil.info("TC_QUOTE_001: Verify quote generation with valid data");

        // Login first
        LoginPage loginPage = new LoginPage();
        loginPage.login("admin", "admin123");

        // Generate quote
        QuotePage quotePage = new QuotePage();
        quotePage.fillQuoteForm(
                "2020",
                "Toyota",
                "Camry",
                "1HGBH41JXMN109186",
                "01/15/2020",
                "25000",
                "Comprehensive",
                "500"
        );

        Assert.assertTrue(quotePage.isQuoteSummaryDisplayed(),
                "Quote summary should be displayed after form submission");

        String premium = quotePage.getPremiumAmount();
        Assert.assertNotNull(premium, "Premium amount should not be null");
        Assert.assertFalse(premium.isEmpty(), "Premium amount should not be empty");

        LoggerUtil.info("Quote generated successfully. Premium: " + premium);
        LoggerUtil.info("TC_QUOTE_001: PASSED");
    }

    // ── TC_QUOTE_002 ──────────────────────────────────────────────
    @Test(groups = {"regression"},
          description = "Verify quote number is generated after successful quote creation")
    public void verifyQuoteNumberGeneration() {
        LoggerUtil.info("TC_QUOTE_002: Verify quote number is generated");

        LoginPage loginPage = new LoginPage();
        loginPage.login("admin", "admin123");

        QuotePage quotePage = new QuotePage();
        quotePage.fillQuoteForm(
                "2019",
                "Honda",
                "Accord",
                "2HGFA16529H502839",
                "03/20/2019",
                "22000",
                "Liability",
                "1000"
        );

        String quoteNumber = quotePage.getQuoteNumber();
        Assert.assertNotNull(quoteNumber, "Quote number should be generated");
        Assert.assertFalse(quoteNumber.isEmpty(), "Quote number should not be empty");

        LoggerUtil.info("Quote Number generated: " + quoteNumber);
        LoggerUtil.info("TC_QUOTE_002: PASSED");
    }

    // ── TC_QUOTE_003 ──────────────────────────────────────────────
    @Test(groups = {"regression"},
          description = "Verify comprehensive coverage generates higher premium than liability")
    public void verifyComprehensivePremiumHigherThanLiability() {
        LoggerUtil.info("TC_QUOTE_003: Verify comprehensive premium > liability premium");

        LoginPage loginPage = new LoginPage();
        loginPage.login("admin", "admin123");

        QuotePage quotePage = new QuotePage();

        // Get Comprehensive premium
        quotePage.fillQuoteForm("2021", "Ford", "Mustang",
                "1FA6P8TH2M5100001", "06/01/2021", "35000", "Comprehensive", "500");
        String comprehensivePremium = quotePage.getPremiumAmount()
                .replaceAll("[^0-9.]", "");

        // Navigate back and get Liability premium
        DriverManager_back();
        quotePage.fillQuoteForm("2021", "Ford", "Mustang",
                "1FA6P8TH2M5100001", "06/01/2021", "35000", "Liability", "500");
        String liabilityPremium = quotePage.getPremiumAmount()
                .replaceAll("[^0-9.]", "");

        double comprehensive = Double.parseDouble(comprehensivePremium);
        double liability = Double.parseDouble(liabilityPremium);

        Assert.assertTrue(comprehensive > liability,
                "Comprehensive premium should be higher than liability premium");

        LoggerUtil.info("TC_QUOTE_003: PASSED");
    }

    // Helper to navigate back
    private void DriverManager_back() {
        utils.DriverManager.getDriver().navigate().back();
    }
}
