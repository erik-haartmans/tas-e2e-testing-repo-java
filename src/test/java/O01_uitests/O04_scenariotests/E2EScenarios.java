package O01_uitests.O04_scenariotests;

import O01_uitests.helpers.UiTestHelper;
import O01_uitests.lib.TasScenario;
import O01_uitests.lib.pages.RegisterPageData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class E2EScenarios extends TasScenario {

    RegisterPageData registerPageData;

    @BeforeEach
    public void beforeEachTest() {
        // create a new RegistrationData object with valid data
        registerPageData = UiTestHelper.createValidRegisterPageData();
    }

    // given a user is registered
    // when the user logs in with valid credentials
    // then the user should be logged in
    @Test
    public void aNewUserShouldBeAbleToLogin() {
        // given a user is registered
        menu.openRegisterPage();
        registerPage.registerNewAccount(registerPageData);
        assertThat(registerSuccessPage.getPageContainer()).isVisible();        // - already handled by the @BeforeEach method

        // when the user logs in with the just created credentials
        menu.openLoginPage();
        loginPage.signIn(registerPageData.getEmail(), registerPageData.getPassword());

        // then the user should be logged in
        assertThat(loginSuccessPage.getPageContainer()).isVisible();
    }

    // given a user is registered
    // when the user logs in with an invalid email
    // then the user should not be logged in
    @Test
    public void anExistingUserShouldNotBeAbleToLoginWhenAnIncorrectEmailIsProvided() {
        // given a user is registered
        menu.openRegisterPage();
        registerPage.registerNewAccount(registerPageData);
        assertThat(registerSuccessPage.getPageContainer()).isVisible();        // - already handled by the @BeforeEach method

        // when the user logs in with an invalid email
        menu.openLoginPage();
        loginPage.signIn("wrong" + registerPageData.getEmail(), registerPageData.getPassword());

        // then the user should not be logged in
        assertThat(loginPage.getFormErrorMessageField()).hasText("Could not login with these credentials");
    }

    // given a user is registered
    // when the user logs in with an invalid password
    // then the user should not be logged in
    @Test
    public void anExistingUserShouldNotBeAbleToLoginWhenAnIncorrectPasswordIsProvided() {
        // given a user is registered
        menu.openRegisterPage();
        registerPage.registerNewAccount(registerPageData);
        assertThat(registerSuccessPage.getPageContainer()).isVisible();        // - already handled by the @BeforeEach method

        // when the user logs in with an invalid password
        menu.openLoginPage();
        loginPage.signIn(registerPageData.getEmail(), "wrong" + registerPageData.getPassword());

        // then the user should not be logged in
        assertThat(loginPage.getFormErrorMessageField()).hasText("Could not login with these credentials");
    }

}
