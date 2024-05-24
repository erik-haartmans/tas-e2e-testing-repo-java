package O01_uitests.lib.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class LoginPage extends BasePage {

    // Locators
    @Getter private final Locator pageContainer = pageLocator.locator("[data-testid='login-container']");
    private final Locator loginForm = pageContainer.locator("[data-testid='login-form']");

    private final Locator emailComponent = loginForm.locator("[data-testid='email']");
    private final Locator emailField = emailComponent.locator("input");
    @Getter private final Locator emailMessage = emailComponent.locator("div.v-messages__message");

    private final Locator passwordComponent = loginForm.locator("[data-testid='password']");
    private final Locator passwordField = passwordComponent.locator("input");
    @Getter private final Locator passwordMessage = passwordComponent.locator("div.v-messages__message");

    @Getter private final Locator formErrorMessageField = loginForm.locator("[data-testid='form-error-message']");
    @Getter private final Locator loginButton = loginForm.locator("[data-testid='login-button']");

    // Constructor
    public LoginPage(Page page) {
        super(page);
    }

    // Page actions
    public void open() {
        page.navigate("/login");
    }

    public void enterEmail(String email) {
        emailField.fill(email);
        emailField.blur();
    }

    public void enterPassword(String password) {
        passwordField.fill(password);
        passwordField.blur();
    }

    public void clickSignIn() {
        loginButton.click();
    }

    public void signIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }
}
