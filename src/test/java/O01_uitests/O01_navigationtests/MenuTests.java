package O01_uitests.O01_navigationtests;

import O01_uitests.lib.TasScenario;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MenuTests extends TasScenario {

    // given a visitor is on the homepage
    // when the visitor selects the register menu item
    // then the visitor should see the register page
    @Test
    public void itShouldBePossibleToOpenTheRegisterPage() {
        // given a visitor is on the homepage
        // * already handled by the @BeforeEach method in the TasScenario class

        // when the visitor selects the register menu item
        menu.openRegisterPage();

        // then the visitor should see the register page
        assertThat(registerPage.getPageContainer()).isVisible();
    }

    // given a visitor is on the homepage
    // when the visitor selects the login menu item
    // then the visitor should see the login page
    @Test
    public void itShouldBePossibleToOpenTheLoginPage() {
        // given a visitor is on the homepage
        // * already handled by the @BeforeEach method in the TasScenario class

        // when the visitor selects the login menu item
        menu.openLoginPage();

        // then the visitor should see the login page
        assertThat(loginPage.getPageContainer()).isVisible();
    }

    // given a visitor is not on the homepage
    // when the visitor selects the home menu item
    // then the visitor should see the home page
    @Test
    public void itShouldBePossibleToOpenTheHomePage() {
        // given a visitor is not on the homepage
        // todo: implement this step

        // when the visitor selects the home menu item
        // todo: implement this step

        // then the visitor should see the home page
        // todo: implement this step

        // remove next line after implementing the test
        throw new RuntimeException("Test not implemented yet");
    }

}
