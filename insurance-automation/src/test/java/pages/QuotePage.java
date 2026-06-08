package pages;

import org.openqa.selenium.By;

/**
 * QuotePage - Page Object for Insurance Quote Generation
 * Covers: vehicle details, coverage selection, quote summary
 */
public class QuotePage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────
    private final By vehicleYearDropdown    = By.id("lst_type_id");
    private final By makeDropdown           = By.id("lst_make");
    private final By modelDropdown          = By.id("lst_model");
    private final By vinField               = By.id("txt_vin");
    private final By purchaseDateField      = By.id("txt_date");
    private final By purchasePriceField     = By.id("txt_price");
    private final By coverageTypeDropdown   = By.id("lst_coverage");
    private final By deductibleDropdown     = By.id("lst_deductible");
    private final By nextButton             = By.id("btn_next");
    private final By quoteSummary           = By.id("quote_summary");
    private final By premiumAmount          = By.id("premium_amount");
    private final By quoteNumberLabel       = By.id("quote_no");
    private final By successMessage         = By.cssSelector(".success-msg, .alert-success");

    // ── Actions ───────────────────────────────────────────────────
    public void selectVehicleYear(String year) {
        selectByVisibleText(vehicleYearDropdown, year);
    }

    public void selectMake(String make) {
        selectByVisibleText(makeDropdown, make);
    }

    public void selectModel(String model) {
        selectByVisibleText(modelDropdown, model);
    }

    public void enterVIN(String vin) {
        type(vinField, vin);
    }

    public void enterPurchaseDate(String date) {
        type(purchaseDateField, date);
    }

    public void enterPurchasePrice(String price) {
        type(purchasePriceField, price);
    }

    public void selectCoverageType(String coverage) {
        selectByVisibleText(coverageTypeDropdown, coverage);
    }

    public void selectDeductible(String deductible) {
        selectByVisibleText(deductibleDropdown, deductible);
    }

    public void clickNext() {
        click(nextButton);
    }

    /**
     * Fills complete quote form with provided details
     */
    public void fillQuoteForm(String year, String make, String model, String vin,
                               String purchaseDate, String price,
                               String coverage, String deductible) {
        selectVehicleYear(year);
        selectMake(make);
        selectModel(model);
        enterVIN(vin);
        enterPurchaseDate(purchaseDate);
        enterPurchasePrice(price);
        selectCoverageType(coverage);
        selectDeductible(deductible);
        clickNext();
    }

    // ── Validations ───────────────────────────────────────────────
    public boolean isQuoteSummaryDisplayed() {
        return isDisplayed(quoteSummary);
    }

    public String getPremiumAmount() {
        return getText(premiumAmount);
    }

    public String getQuoteNumber() {
        return getText(quoteNumberLabel);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
}
