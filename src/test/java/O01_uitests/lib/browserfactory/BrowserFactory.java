package O01_uitests.lib.browserfactory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    private static final boolean headless = System.getProperty("CI") != null;
    private static final double slowMo = 0;

    public static Browser createBrowser(Playwright playwright, BrowserName browserName) {

        return switch (browserName) {
            case CHROMIUM -> createChromiumBrowser(playwright);
            case FIREFOX -> createFirefoxBrowser(playwright);
            case WEBKIT -> createWebkitBrowser(playwright);
        };
    }

    private static Browser createWebkitBrowser(Playwright playwright) {
        return playwright.webkit().launch(
                new BrowserType
                        .LaunchOptions()
                        .setHeadless(headless)
                        .setSlowMo(slowMo));
    }

    private static Browser createFirefoxBrowser(Playwright playwright) {
        return playwright.firefox().launch(
                new BrowserType
                        .LaunchOptions()
                        .setHeadless(headless)
                        .setSlowMo(slowMo));
    }

    private static Browser createChromiumBrowser(Playwright playwright) {
        return playwright.chromium().launch(
                new BrowserType
                        .LaunchOptions()
                        .setHeadless(headless)
                        .setSlowMo(slowMo));
    }

}
