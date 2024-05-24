package O01_uitests.O02_registrationtests;

import O01_uitests.lib.pages.RegisterPageData;
import O01_uitests.helpers.UiTestHelper;
import O01_uitests.lib.TasScenario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InvalidRegistrationTests extends TasScenario {

    private RegisterPageData registerPageData;

    @BeforeEach
    public void beforeEach() {
        registerPageData = UiTestHelper.createValidRegisterPageData();

        // given a user wants to register
        menu.openRegisterPage();
    }

    // given a user wants to register
    // when the user enters registration data with an invalid email
    // then the user should receive an error message on the register page
    @Test
    public void anErrorShouldBeShownWhenNoEmailIsEntered() {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user enters registration data with an invalid email
        registerPageData.setEmail(""); // make sure the email is empty
        registerPage.registerNewAccount(registerPageData);

        // then the user should receive an error message on the register page
        assertThat(registerPage.getFormErrorMessage()).hasText("Failed to register!");
        assertThat(registerPage.getFormErrorExplanation()).containsText("Email is required");
    }

    // given a user wants to register
    // when the user enters registration data with an invalid email
    // then the user should receive an error message on the register page
    @ParameterizedTest
    @ValueSource(strings = {
            "test",
            "test@",
            "test@.",
            "test@.com",
            "test@com",
            "test.com",
            "test@com.",
    })
    public void anErrorShouldBeShownWhenAnInvalidEmailIsUsed(String email) {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user enters registration data with an invalid email
        registerPageData.setEmail(email); // set the email to the invalid email
        registerPage.registerNewAccount(registerPageData);

        // then the user should receive an error message on the register page
        assertThat(registerPage.getFormErrorMessage()).hasText("Failed to register!");
        assertThat(registerPage.getFormErrorExplanation()).containsText("Email is not valid");
    }


    // given a user wants to register
    // when the user enters registration data with an empty password
    // then the user should receive an error message on the register page
    @Test
    public void anErrorShouldBeShownWhenNoPasswordIsEntered() {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user enters registration data with an empty password
        registerPageData.setPassword("");
        registerPageData.setPasswordCheck("");
        registerPage.registerNewAccount(registerPageData);

        // then the user should receive an error message on the register page
        assertThat(registerPage.getFormErrorMessage()).hasText("Failed to register!");
        assertThat(registerPage.getFormErrorExplanation()).hasText("Password is required");
    }

    // given a user wants to register
    // when the user enters registration data with an empty name
    // then the user should receive an error message on the register page
    @Test
    public void anErrorShouldBeShownWhenNoNameIsEntered() {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user enters registration data with an empty name
        registerPageData.setName("");
        registerPage.registerNewAccount(registerPageData);

        // then the user should receive an error message on the register page
        assertThat(registerPage.getFormErrorMessage()).hasText("Failed to register!");
        assertThat(registerPage.getFormErrorExplanation()).hasText("Name is required");
    }

    // given a user wants to register
    // when the user registers with an email that is already in use
    // then the user should receive an error message on the register page
    @Test
    public void itShouldNotBePossibleToRegisterAnEmailTwice() {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user registers with an email that is already in use
        registerPage.registerNewAccount(registerPageData); // register for the first time
        assertThat(registerSuccessPage.getPageContainer()).isVisible(); // check if the registration was successful
        menu.openRegisterPage();
        registerPage.registerNewAccount(registerPageData); // register again with the same email

        // then the user should receive an error message on the register page
        assertThat(registerPage.getFormErrorMessage()).hasText("Failed to register!");
        assertThat(registerPage.getFormErrorExplanation()).hasText("User " + registerPageData.getEmail() + " already exists");
    }
}
