package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ClaimPage;
import pages.LoginPage;
import utils.LoggerUtil;

/**
 * ClaimSubmissionTest - Test cases for Insurance Claim Submission
 * Covers: valid claim submission, invalid policy number, mandatory field validation
 */
public class ClaimSubmissionTest extends BaseTest {

    // ── TC_CLAIM_001 ──────────────────────────────────────────────
    @Test(groups = {"regression"},
          description = "Verify successful claim submission with valid policy number")
    public void verifyClaimSubmissionWithValidPolicy() {
        LoggerUtil.info("TC_CLAIM_001: Verify claim submission with valid policy");

        LoginPage loginPage = new LoginPage();
        loginPage.login("admin", "admin123");

        ClaimPage claimPage = new ClaimPage();
        claimPage.submitClaim(
                "POL-2024-001234",
                "05/01/2026",
                "04/30/2026",
                "Collision",
                "15000",
                "Vehicle collided with another car at intersection. Front bumper and hood damaged."
        );

        Assert.assertTrue(claimPage.isClaimSubmittedSuccessfully(),
                "Claim submission success message should be displayed");

        String claimNumber = claimPage.getClaimNumber();
        Assert.assertNotNull(claimNumber, "Claim number should be generated");
        Assert.assertFalse(claimNumber.isEmpty(), "Claim number should not be empty");

        LoggerUtil.info("Claim submitted successfully. Claim No: " + claimNumber);
        LoggerUtil.info("TC_CLAIM_001: PASSED");
    }

    // ── TC_CLAIM_002 ──────────────────────────────────────────────
    @Test(groups = {"regression"},
          description = "Verify error on claim submission with invalid policy number")
    public void verifyClaimSubmissionWithInvalidPolicy() {
        LoggerUtil.info("TC_CLAIM_002: Verify claim with invalid policy number");

        LoginPage loginPage = new LoginPage();
        loginPage.login("admin", "admin123");

        ClaimPage claimPage = new ClaimPage();
        claimPage.submitClaim(
                "INVALID-POLICY-9999",
                "05/01/2026",
                "04/30/2026",
                "Theft",
                "5000",
                "Vehicle was stolen from parking lot."
        );

        Assert.assertTrue(claimPage.isErrorDisplayed(),
                "Error message should be displayed for invalid policy number");

        LoggerUtil.info("TC_CLAIM_002: PASSED - Error correctly shown for invalid policy");
    }

    // ── TC_CLAIM_003 ──────────────────────────────────────────────
    @Test(groups = {"regression"},
          description = "Verify claim number is unique for each submission")
    public void verifyUniqueClaimNumberPerSubmission() {
        LoggerUtil.info("TC_CLAIM_003: Verify unique claim numbers");

        LoginPage loginPage = new LoginPage();
        loginPage.login("admin", "admin123");

        ClaimPage claimPage = new ClaimPage();

        claimPage.submitClaim("POL-2024-001234", "05/01/2026", "04/28/2026",
                "Weather", "8000", "Hail damage to vehicle roof and windshield.");
        String claimNumber1 = claimPage.getClaimNumber();

        // Navigate back and submit another claim
        utils.DriverManager.getDriver().navigate().back();

        claimPage.submitClaim("POL-2024-001235", "05/02/2026", "04/29/2026",
                "Collision", "12000", "Rear-end collision damage.");
        String claimNumber2 = claimPage.getClaimNumber();

        Assert.assertNotEquals(claimNumber1, claimNumber2,
                "Each claim submission should generate a unique claim number");

        LoggerUtil.info("TC_CLAIM_003: PASSED - Claim1: " + claimNumber1 + ", Claim2: " + claimNumber2);
    }
}
