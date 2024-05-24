package O01_uitests.lib.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MenuComponent extends BasePage {

    // Locators
    private final Locator menuLocator = pageLocator.locator("[data-testid='tas-menu']");
    private final Locator homeButton = menuLocator.locator("[data-testid='btn-home']");
    private final Locator loginButton = menuLocator.locator("[data-testid='btn-login']");
    private final Locator registerButton = menuLocator.locator("[data-testid='btn-register']");

    // Constructor
    public MenuComponent(Page page) {
        super(page);
    }

    // Page actions
    public void openHomePage() {
        homeButton.click();
    }

    public void openLoginPage() {
        loginButton.click();
    }

    public void openRegisterPage() {
        registerButton.click();
    }
}
