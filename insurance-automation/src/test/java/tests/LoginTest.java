package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.LoggerUtil;

/**
 * LoginTest - Test cases for Insurance Application Login
 * Covers: valid login, invalid login, blank credentials
 */
public class LoginTest extends BaseTest {

    // ── TC_LOGIN_001 ──────────────────────────────────────────────
    @Test(groups = {"smoke", "regression"},
          description = "Verify successful login with valid credentials")
    public void verifyValidLogin() {
        LoggerUtil.info("TC_LOGIN_001: Verify valid login");

        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page should be displayed");

        loginPage.login("admin", "admin123");

        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("login"),
                "User should be redirected away from login page after successful login");

        LoggerUtil.info("TC_LOGIN_001: PASSED");
    }

    // ── TC_LOGIN_002 ──────────────────────────────────────────────
    @Test(groups = {"smoke", "regression"},
          description = "Verify error message on invalid credentials")
    public void verifyInvalidLogin() {
        LoggerUtil.info("TC_LOGIN_002: Verify invalid login");

        LoginPage loginPage = new LoginPage();
        loginPage.login("invalidUser", "wrongPass");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for invalid credentials");

        LoggerUtil.info("TC_LOGIN_002: PASSED");
    }

    // ── TC_LOGIN_003 ──────────────────────────────────────────────
    @Test(groups = {"regression"},
          description = "Verify error message when credentials are blank")
    public void verifyBlankCredentialsLogin() {
        LoggerUtil.info("TC_LOGIN_003: Verify blank credentials");

        LoginPage loginPage = new LoginPage();
        loginPage.login("", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for blank credentials");

        LoggerUtil.info("TC_LOGIN_003: PASSED");
    }

    // ── TC_LOGIN_004 ──────────────────────────────────────────────
    @Test(groups = {"regression"},
          description = "Verify error message when only username is provided")
    public void verifyLoginWithOnlyUsername() {
        LoggerUtil.info("TC_LOGIN_004: Verify login with only username");

        LoginPage loginPage = new LoginPage();
        loginPage.login("admin", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed when password is blank");

        LoggerUtil.info("TC_LOGIN_004: PASSED");
    }
}
