package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PolicyPage;
import pages.QuotePage;
import utils.LoggerUtil;

/**
 * PolicyCreationTest - Test cases for Insurance Policy Creation
 * Covers: policy binding, policy number generation, status validation
 */
public class PolicyCreationTest extends BaseTest {

    // ── TC_POLICY_001 ──────────────────────────────────────────────
    @Test(groups = {"regression"},
          description = "Verify successful policy creation after quote generation")
    public void verifyPolicyCreationFromQuote() {
        LoggerUtil.info("TC_POLICY_001: Verify policy creation from quote");

        // Step 1: Login
        LoginPage loginPage = new LoginPage();
        loginPage.login("admin", "admin123");

        // Step 2: Generate quote
        QuotePage quotePage = new QuotePage();
        quotePage.fillQuoteForm(
                "2022", "Toyota", "Corolla",
                "JTDEPRAEXLJ085281", "01/10/2022",
                "20000", "Comprehensive", "500"
        );

        Assert.assertTrue(quotePage.isQuoteSummaryDisplayed(),
                "Quote must be generated before policy creation");

        // Step 3: Create policy
        PolicyPage policyPage = new PolicyPage();
        policyPage.createPolicy(
                "Vaishnav", "Ghugare", "01/01/1999",
                "123 Main Street", "Pune", "Maharashtra",
                "411001", "9876543210",
                "test@insurance.com", "Annual"
        );

        Assert.assertTrue(policyPage.isConfirmationDisplayed(),
                "Policy confirmation message should be displayed");

        String policyNumber = policyPage.getPolicyNumber();
        Assert.assertNotNull(policyNumber, "Policy number should be generated");
        Assert.assertFalse(policyNumber.isEmpty(), "Policy number should not be empty");

        LoggerUtil.info("Policy created successfully. Policy No: " + policyNumber);
        LoggerUtil.info("TC_POLICY_001: PASSED");
    }

    // ── TC_POLICY_002 ──────────────────────────────────────────────
    @Test(groups = {"regression"},
          description = "Verify policy status is Active after binding")
    public void verifyPolicyStatusAfterBinding() {
        LoggerUtil.info("TC_POLICY_002: Verify policy status is Active");

        LoginPage loginPage = new LoginPage();
        loginPage.login("admin", "admin123");

        QuotePage quotePage = new QuotePage();
        quotePage.fillQuoteForm(
                "2021", "Honda", "City",
                "1HGCR2F3XHA045678", "05/15/2021",
                "18000", "Liability", "1000"
        );

        PolicyPage policyPage = new PolicyPage();
        policyPage.createPolicy(
                "Test", "User", "06/15/1995",
                "456 Park Avenue", "Mumbai", "Maharashtra",
                "400001", "9988776655",
                "testuser@mail.com", "Monthly"
        );

        String status = policyPage.getPolicyStatus();
        Assert.assertEquals(status.toLowerCase(), "active",
                "Policy status should be 'Active' after successful binding");

        LoggerUtil.info("TC_POLICY_002: PASSED - Policy Status: " + status);
    }
}
