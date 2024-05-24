package O01_uitests.lib.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class RegisterSuccessPage extends BasePage {

    // Locators
    @Getter private final Locator pageContainer = pageLocator.locator("[data-testid='register-success-page-container']");

    // Constructor
    public RegisterSuccessPage(Page page) {
        super(page);
    }

    // Page actions

}
