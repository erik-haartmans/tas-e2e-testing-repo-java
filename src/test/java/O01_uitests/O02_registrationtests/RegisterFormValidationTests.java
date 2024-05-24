package O01_uitests.O02_registrationtests;

import O01_uitests.helpers.UiTestHelper;
import O01_uitests.lib.TasScenario;
import O01_uitests.lib.pages.RegisterPageData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegisterFormValidationTests extends TasScenario {

    RegisterPageData registerPageData;

    @BeforeEach
    public void beforeEachTest() {
        // create a new RegistrationData object with valid data
        registerPageData = UiTestHelper.createValidRegisterPageData();

        // given a user wants to register
        menu.openRegisterPage();
    }

    @Test
    public void whenPasswordsAreNotTheSameTheUserCannotBeRegistered() {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user enters registration data but the passwords do not match
        registerPageData.setPasswordCheck("");
        registerPage.fillForm(registerPageData); // this doesn't actually submit the form

        // then the user should not be able to register
        assertThat(registerPage.getRegisterButton()).isDisabled();
    }

    @Test
    public void whenPasswordIsEmptyTheUserCannotBeRegistered() {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user enters registration data but the passwords do not match
        registerPageData.setPasswordCheck("");
        registerPageData.setPasswordCheck("");
        registerPage.fillForm(registerPageData); // this doesn't actually submit the form

        // then the user should not be able to register
        assertThat(registerPage.getRegisterButton()).isDisabled();
    }


    @Test
    public void whenTheTermsAreNotAcceptedTheUserCannotBeRegistered() {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user enters valid registration data but does not accept the terms
        registerPageData.setTerms(false);
        registerPage.fillForm(registerPageData); // this doesn't actually submit the form

        // then the user should not be able to register
        assertThat(registerPage.getRegisterButton()).isDisabled();
    }

}
