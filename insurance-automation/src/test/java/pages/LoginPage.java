package pages;

import org.openqa.selenium.By;

/**
 * LoginPage - Page Object for Insurance Application Login
 * Follows Page Object Model (POM) design pattern
 */
public class LoginPage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────
    private final By usernameField   = By.id("user_name");
    private final By passwordField   = By.id("password");
    private final By loginButton     = By.name("submit");
    private final By errorMessage    = By.cssSelector(".error-msg, .alert-danger, #error");
    private final By pageHeader      = By.cssSelector("h1, .page-title");

    // ── Actions ───────────────────────────────────────────────────
    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // ── Validations ───────────────────────────────────────────────
    public boolean isLoginPageDisplayed() {
        return isDisplayed(loginButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }
}
