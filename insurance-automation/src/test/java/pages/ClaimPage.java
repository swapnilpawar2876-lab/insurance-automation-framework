package pages;

import org.openqa.selenium.By;

/**
 * ClaimPage - Page Object for Insurance Claim Submission
 * Covers: claim filing, claim status tracking
 */
public class ClaimPage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────
    private final By policyNumberField      = By.id("txt_policy_no");
    private final By claimDateField         = By.id("txt_claim_date");
    private final By incidentDateField      = By.id("txt_incident_date");
    private final By claimTypeDropdown      = By.id("lst_claim_type");
    private final By damageAmountField      = By.id("txt_damage_amount");
    private final By descriptionField       = By.id("txt_description");
    private final By submitClaimButton      = By.id("btn_submit_claim");
    private final By claimNumberLabel       = By.id("claim_number");
    private final By claimStatusLabel       = By.id("claim_status");
    private final By successMessage         = By.cssSelector(".claim-success, .alert-success");
    private final By errorMessage           = By.cssSelector(".claim-error, .alert-danger");

    // ── Actions ───────────────────────────────────────────────────
    public void enterPolicyNumber(String policyNumber) {
        type(policyNumberField, policyNumber);
    }

    public void enterClaimDate(String claimDate) {
        type(claimDateField, claimDate);
    }

    public void enterIncidentDate(String incidentDate) {
        type(incidentDateField, incidentDate);
    }

    public void selectClaimType(String claimType) {
        selectByVisibleText(claimTypeDropdown, claimType);
    }

    public void enterDamageAmount(String amount) {
        type(damageAmountField, amount);
    }

    public void enterDescription(String description) {
        type(descriptionField, description);
    }

    public void clickSubmitClaim() {
        click(submitClaimButton);
    }

    /**
     * Submits a complete insurance claim
     */
    public void submitClaim(String policyNumber, String claimDate, String incidentDate,
                             String claimType, String damageAmount, String description) {
        enterPolicyNumber(policyNumber);
        enterClaimDate(claimDate);
        enterIncidentDate(incidentDate);
        selectClaimType(claimType);
        enterDamageAmount(damageAmount);
        enterDescription(description);
        clickSubmitClaim();
    }

    // ── Validations ───────────────────────────────────────────────
    public String getClaimNumber() {
        return getText(claimNumberLabel);
    }

    public String getClaimStatus() {
        return getText(claimStatusLabel);
    }

    public boolean isClaimSubmittedSuccessfully() {
        return isDisplayed(successMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
