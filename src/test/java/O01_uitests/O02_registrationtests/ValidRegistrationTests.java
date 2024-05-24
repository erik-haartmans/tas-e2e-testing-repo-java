package O01_uitests.O02_registrationtests;

import O01_uitests.lib.pages.RegisterPageData;
import O01_uitests.helpers.UiTestHelper;
import O01_uitests.lib.TasScenario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ValidRegistrationTests extends TasScenario {

    RegisterPageData registerPageData;

    @BeforeEach
    public void beforeEachTest() {
        // create a new RegistrationData object with valid data
        registerPageData = UiTestHelper.createValidRegisterPageData();

        // given a user wants to register
        menu.openRegisterPage();
    }

    @Test
    public void itShouldBePossibleToRegisterWithAllData() {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user enters valid registration data
        registerPage.registerNewAccount(registerPageData);

        // then the user should be registered (register success page should be visible)
        assertThat(registerSuccessPage.getPageContainer()).isVisible();
    }

    /**
     * This method creates a set of unique and valid emails which is used for the next ParameterizedTest
     * @return stream of valid emails
     */
    private static Stream<Arguments> provideValidEmails() {
        String uniqueEmailPart = UUID.randomUUID().toString();
        return Stream.of(
                Arguments.of(uniqueEmailPart.toLowerCase() + "-lc@testingemails.com")
                , Arguments.of(uniqueEmailPart.toUpperCase() + "-UC@testingemails.com")
                , Arguments.of(uniqueEmailPart + "-tlduc@testingemails.COM")
                , Arguments.of(uniqueEmailPart + "-duc@TESTINGEMAILS.com")
                , Arguments.of(uniqueEmailPart + "@TESTINGEMAILS.co")
                , Arguments.of(uniqueEmailPart + "@testingemails.abcdef")
        );
    }

    // given a user wants to register
    // when the user enters valid registration data (including a valid email)
    // then the user should be registered
    @ParameterizedTest(name="{index} It should be possible to register with valid email {0}")
    @MethodSource("provideValidEmails")
    public void itShouldBePossibleToRegisterWithValidEmail(String validEmail) {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user enters valid registration data (including a valid email)
        registerPageData.setEmail(validEmail);
        registerPage.registerNewAccount(registerPageData);

        // then the user should be registered
        assertThat(registerSuccessPage.getPageContainer()).isVisible();
    }

    // given a user wants to register
    // when the user only enters the minimal data required
    // then the user should be registered
    @Test
    public void itShouldBePossibleToRegisterWithMinimalData() {
        // given a user wants to register
        // - already handled by the @BeforeEach method

        // when the user only enters the minimal data required
        registerPageData.setPhoneNumber("");
        registerPage.registerNewAccount(registerPageData);

        // then the user should be registered
        assertThat(registerSuccessPage.getPageContainer()).isVisible();
    }

}
