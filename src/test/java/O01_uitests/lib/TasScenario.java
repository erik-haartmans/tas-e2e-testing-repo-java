package O01_uitests.lib;

import O01_uitests.lib.pages.*;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import O01_uitests.lib.browserfactory.BrowserFactory;
import O01_uitests.lib.browserfactory.BrowserName;

import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TasScenario {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    protected Page page;

    protected MenuComponent menu;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected RegisterSuccessPage registerSuccessPage;
    protected LoginSuccessPage loginSuccessPage;

    @BeforeAll
    public void BeforeAllTests() {
        playwright = Playwright.create();
    }

    @BeforeEach
    public void BeforeEachTest() {
        browser = BrowserFactory.createBrowser(playwright, BrowserName.CHROMIUM);
        // context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080)); // also possible
        context = browser.newContext();
        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();
        // pages could also be instantiated in the before each method
        menu = new MenuComponent(page);
        homePage = new HomePage(page);
        loginPage = new LoginPage(page);
        registerPage = new RegisterPage(page);
        registerSuccessPage = new RegisterSuccessPage(page);
        loginSuccessPage = new LoginSuccessPage(page);
        // set viewport & open site
        page.setViewportSize(1024, 768);
        page.navigate("https://tas.frontend.staging.polteq-testing.com");
    }

    @AfterEach
    public void AfterEachTest(TestInfo testInfo) {
        // Stop tracing and export it into a zip archive.
        String currentTestName = testInfo.getTestClass().get().getName()
                + "."
                + testInfo.getDisplayName();
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target/traces/" + currentTestName + ".zip")));
        context.close();

        browser.close();
    }

    @AfterAll
    public void AfterAll() {
        playwright.close();
    }
}
