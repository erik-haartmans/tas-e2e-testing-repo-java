package O01_uitests.O01_navigationtests;

import O01_uitests.lib.TasScenario;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePageTests extends TasScenario {

    // given a visitor is on the homepage
    // then the visitor should see the correct tab title
    @Test
    public void loginPageShouldHaveTheCorrectTabTitle() {
        // given a visitor is on the homepage
        // * already handled by the @BeforeEach method in the TasScenario class

        // then the visitor should see the correct tab title
        assertThat(page).hasTitle("Automating Tests with a Strategy");
    }

    // given a visitor is on the homepage
    // when the visitor wants to register from the homepage
    // then the visitor should be on the register page
    @Test
    public void itShouldBePossibleToOpenTheRegisterPage() {
        // given a visitor is on the homepage
        // * already handled by the @BeforeEach method in the TasScenario class

        // when the visitor wants to register from the homepage
        homePage.openRegisterPage();

        // then the visitor should be on the register page
        assertThat(registerPage.getPageContainer()).isVisible();
    }

    // given a visitor is on the homepage
    // when the visitor wants to login from the homepage
    // then the visitor should be on the login page
    @Test
    public void itShouldBePossibleToOpenTheLoginPage() {
        // given a visitor is on the homepage
        // * already handled by the @BeforeEach method in the TasScenario class

        // when the visitor wants to login from the homepage
        // todo: implement this step

        // then the visitor should be on the login page
        // todo: implement this step

        // remove next line after implementing the test
        throw new RuntimeException("Test not implemented yet");
    }


}
