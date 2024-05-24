package O01_uitests.O03_somelogintests;

import org.junit.jupiter.api.Test;
import O01_uitests.lib.TasScenario;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginFormValidationTests extends TasScenario {

    @Test
    public void anErrorShouldBeShownWhenNoEmailIsEntered() {
        menu.openLoginPage();
        loginPage.enterEmail("");
        assertThat(loginPage.getEmailMessage()).hasText("Email is required");
    }

    @Test
    public void anErrorShouldBeShownWhenNoPasswordIsEntered() {
        menu.openLoginPage();
        loginPage.enterPassword("");
        assertThat(loginPage.getPasswordMessage()).hasText("Password is required.");
    }

    @Test
    public void loginButtonShouldBeDisabledWhenNoDataIsEntered() {
        menu.openLoginPage();
        assertThat(loginPage.getLoginButton()).isDisabled();
    }


}
