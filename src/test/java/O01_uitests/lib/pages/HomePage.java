package O01_uitests.lib.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class HomePage extends BasePage {

    // Locators
    @Getter private final Locator pageContainer = pageLocator.locator("[data-testid='home-page-container']");
    private final Locator loginButton = pageContainer.locator("[data-testid='btn-login']");
    private final Locator registerButton = pageContainer.locator("[data-testid='btn-register']");

    // Constructor
    public HomePage(Page page) {
        super(page);
    }

    // Page actions
    public void openLoginPage() {
        loginButton.click();
    }

    public void openRegisterPage() {
        registerButton.click();
    }
}
