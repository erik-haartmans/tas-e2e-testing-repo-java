package O01_uitests.lib.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class RegisterPage extends BasePage {

    // Locators
    @Getter private final Locator pageContainer = pageLocator.locator("[data-testid='register-page-container']");
    private final Locator registerForm = pageContainer.locator("[data-testid='register-form']");

    private final Locator name = registerForm.locator("[data-testid='name'] input");
    private final Locator email = registerForm.locator("[data-testid='email'] input");
    private final Locator password = registerForm.locator("[data-testid='password'] input");
    private final Locator passwordCheck = registerForm.locator("[data-testid='password-check'] input");
    private final Locator phoneNumber = registerForm.locator("[data-testid='phone-number'] input");
    private final Locator terms = registerForm.locator("[data-testid='terms'] input");

    @Getter private final Locator formErrorMessage = registerForm.locator("[data-testid='form-error-message']");
    @Getter private final Locator formErrorExplanation = registerForm.locator("[data-testid='form-error-explanation']");

    @Getter private final Locator registerButton = registerForm.locator("[data-testid='register-button']");


    // Constructor
    public RegisterPage(Page page) {
        super(page);
    }

    // Page actions
    public void open() {
        page.navigate("/register");
    }

    public void fillForm(
            String name,
            String email,
            String password,
            String passwordCheck,
            String phoneNumber,
            Boolean acceptTerms) {
        this.name.fill(name);
        this.email.fill(email);
        this.password.fill(password);
        this.passwordCheck.fill(passwordCheck);
        this.phoneNumber.fill(phoneNumber);
        if (acceptTerms) {
            this.terms.check();
        } else {
            this.terms.uncheck();
        }
    }

    public void fillForm(RegisterPageData rpd) {
        this.fillForm(
                rpd.getName(),
                rpd.getEmail(),
                rpd.getPassword(),
                rpd.getPasswordCheck(),
                rpd.getPhoneNumber(),
                rpd.isTerms());
    }

    public void registerNewAccount(
            String name,
            String email,
            String password,
            String passwordCheck,
            String phoneNumber,
            boolean acceptTerms) {
        this.fillForm(name, email, password, passwordCheck, phoneNumber, acceptTerms);

        this.registerButton.click();
    }

    public void registerNewAccount(RegisterPageData rpd) {
        this.registerNewAccount(
                rpd.getName(),
                rpd.getEmail(),
                rpd.getPassword(),
                rpd.getPasswordCheck(),
                rpd.getPhoneNumber(),
                rpd.isTerms());
    }

}
