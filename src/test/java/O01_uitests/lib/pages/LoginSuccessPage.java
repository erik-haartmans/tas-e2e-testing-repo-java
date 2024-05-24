package O01_uitests.lib.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class LoginSuccessPage extends BasePage {

    // Locators
    @Getter private final Locator pageContainer = pageLocator.locator("[data-testid='login-success-page-container']");

    // Constructor
    public LoginSuccessPage(Page page) {
        super(page);
    }

    // Page actions
    // -- none yet
}
