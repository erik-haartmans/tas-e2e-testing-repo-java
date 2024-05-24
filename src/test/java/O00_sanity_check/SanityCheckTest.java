package O00_sanity_check;

import O01_uitests.lib.browserfactory.BrowserFactory;
import O01_uitests.lib.browserfactory.BrowserName;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SanityCheckTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    protected Page page;

    @BeforeAll
    public void BeforeAllTests() {
        playwright = Playwright.create();
        browser = BrowserFactory.createBrowser(playwright, BrowserName.CHROMIUM);
    }

    @BeforeEach
    public void BeforeEachTest() {
        // context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080)); // also possible
        context = browser.newContext();
        // Start tracing before creating / navigating a page.
        page = context.newPage();
        // set viewport & open site
        page.setViewportSize(1024, 768);
    }

    // given a visitor is on the google homepage
    // then the visitor should see the correct tab title
    @Test
    public void itShouldBePossibleToOpenGoogle() {
        // given a visitor is on the google homepage
        page.navigate("https://www.google.com");
        // then the visitor should see the correct tab title
        Assertions.assertEquals("Google", page.title());
    }

    @AfterEach
    public void AfterEachTest() {
        context.close();
    }

    @AfterAll
    public void AfterAll() {
        playwright.close();
    }

}
