package O01_uitests.lib.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

public class BasePage {

    protected final Page page;
    protected final Locator pageLocator;

    public BasePage(Page page) {
        this.page = page;
        pageLocator = page.locator("div#app");
    }

    protected List<String> selectOptionByValue(Locator selectField, String value) {
        return selectField.selectOption(new SelectOption().setValue(value));
    }
}
