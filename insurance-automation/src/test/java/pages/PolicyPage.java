package pages;

import org.openqa.selenium.By;

/**
 * PolicyPage - Page Object for Insurance Policy Creation
 * Covers: policyholder details, policy binding, policy confirmation
 */
public class PolicyPage extends BasePage {

    // ── Locators ──────────────────────────────────────────────────
    private final By firstNameField         = By.id("txt_first_name");
    private final By lastNameField          = By.id("txt_last_name");
    private final By dobField               = By.id("txt_dob");
    private final By addressField           = By.id("txt_address");
    private final By cityField              = By.id("txt_city");
    private final By stateDropdown          = By.id("lst_state");
    private final By zipCodeField           = By.id("txt_zip");
    private final By phoneField             = By.id("txt_phone");
    private final By emailField             = By.id("txt_email");
    private final By paymentMethodDropdown  = By.id("lst_payment");
    private final By bindPolicyButton       = By.id("btn_bind");
    private final By policyNumberLabel      = By.id("policy_number");
    private final By policyStatusLabel      = By.id("policy_status");
    private final By confirmationMessage    = By.cssSelector(".policy-confirm, .alert-success");

    // ── Actions ───────────────────────────────────────────────────
    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterDateOfBirth(String dob) {
        type(dobField, dob);
    }

    public void enterAddress(String address) {
        type(addressField, address);
    }

    public void enterCity(String city) {
        type(cityField, city);
    }

    public void selectState(String state) {
        selectByVisibleText(stateDropdown, state);
    }

    public void enterZipCode(String zip) {
        type(zipCodeField, zip);
    }

    public void enterPhone(String phone) {
        type(phoneField, phone);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void selectPaymentMethod(String paymentMethod) {
        selectByVisibleText(paymentMethodDropdown, paymentMethod);
    }

    public void clickBindPolicy() {
        click(bindPolicyButton);
    }

    /**
     * Fills complete policyholder form and binds the policy
     */
    public void createPolicy(String firstName, String lastName, String dob,
                              String address, String city, String state,
                              String zip, String phone, String email,
                              String paymentMethod) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterDateOfBirth(dob);
        enterAddress(address);
        enterCity(city);
        selectState(state);
        enterZipCode(zip);
        enterPhone(phone);
        enterEmail(email);
        selectPaymentMethod(paymentMethod);
        clickBindPolicy();
    }

    // ── Validations ───────────────────────────────────────────────
    public String getPolicyNumber() {
        return getText(policyNumberLabel);
    }

    public String getPolicyStatus() {
        return getText(policyStatusLabel);
    }

    public boolean isConfirmationDisplayed() {
        return isDisplayed(confirmationMessage);
    }

    public boolean isPolicyNumberGenerated() {
        String policyNo = getPolicyNumber();
        return policyNo != null && !policyNo.isEmpty();
    }
}
