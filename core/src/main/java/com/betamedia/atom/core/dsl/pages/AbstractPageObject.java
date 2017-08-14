package com.betamedia.atom.core.dsl.pages;

import com.betamedia.atom.core.dsl.pages.localization.LocalizationStorage;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class AbstractPageObject {
    private static final Logger logger = LogManager.getLogger(AbstractPageObject.class);

    private static final String DOM_UPDATE_MESSAGE = "DOM updated when retrieving value";
    private static final String EXPECTED_LOOKUP_FAILURE_MESSAGE = "Could not find element";
    private static final int MAX_WAIT_SEC = 60;

    private WebDriver webDriver;
    private LocalizationStorage localizations;

    protected AbstractPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Wait until element is displayed ({@link WebElement#isDisplayed()})
     *
     * @param first element locator
     * @param rest  element locator chain
     * @return located {@link WebElement} (never <code>null</code>)
     */
    protected WebElement waitUntilDisplayed(By first, By... rest) {
        return getWait().until(driver -> ignoringStale(() -> elementIfVisible(find(first, rest))));
    }

    /**
     * Wait until element exists on page (not necessarily visible)
     *
     * @param first element locator
     * @param rest  element locator chain
     * @return <code>true</code> when element is found
     */
    protected WebElement waitUntilExists(By first, By... rest) {
        return getWait().until(driver -> ignoringStale(() -> find(first, rest)));
    }

    protected WebElement waitUntilClickable(By first, By... rest) {
        return getWait().until(ExpectedConditions.elementToBeClickable(find(first, rest)));
    }

    /**
     * Wait until arbitrary condition is not null or false
     *
     * @param isValid condition to evaluate
     * @return return value of condition
     */
    protected <T> T waitUntil(Supplier<T> isValid) {
        return getWait().until(d -> ignoringStale(isValid));
    }

    /**
     * A wrapper that handles {@link TimeoutException} and {@link NoSuchElementException} for {@link WebElement} retrieving methods.
     *
     * @param supplier supplier of the element
     * @return {@link Optional<WebElement>} containing element or empty if nothing was located
     */
    protected Optional<WebElement> maybe(Supplier<WebElement> supplier) {
        try {
            return Optional.ofNullable(supplier.get());
        } catch (TimeoutException | NoSuchElementException e) {
            logger.debug(EXPECTED_LOOKUP_FAILURE_MESSAGE, e);
            Reporter.log(EXPECTED_LOOKUP_FAILURE_MESSAGE + '\n');
            return Optional.empty();
        }
    }

    /**
     * Find the list of elements using the by locator {@link By}
     *
     * @param by element locator
     * @return list of detected elements
     */
    protected List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    /**
     * Find the first {@link WebElement} using the by locator chain of {@link By}
     *
     * @param first element locator
     * @param rest  element locator chain
     * @return The first matching element on the current page
     * @throws NoSuchElementException If no matching elements are found
     */
    protected WebElement find(By first, By... rest) {
        WebElement element = webDriver.findElement(first);
        for (By b : rest) {
            element = element.findElement(b);
        }
        return element;
    }

    /**
     * Create {@link Actions} object to construct complex gestures
     *
     * @return {@link Actions} object for active {@link WebDriver}
     */
    protected Actions makeActions() {
        return new Actions(webDriver);
    }

    /**
     * Evaluate {@link Supplier} value for given iFrame located with a {@link By} chain
     *
     * @param supplier function to evaluate
     * @param iFrames  {@link By} locator chain for an iFrame
     * @return return value of supplier
     * @throws NoSuchFrameException           If the given element is neither an IFRAME nor a FRAME element.
     * @throws StaleElementReferenceException If the WebElement has gone stale.
     */
    protected <T> T inIFrame(Supplier<T> supplier, By... iFrames) {
        try {
            for (By iFrame : iFrames) {
                switchToFrame(iFrame);
            }
            return supplier.get();
        } finally {
            leaveFrame();
        }
    }

    /**
     * Switch {@link WebDriver} context to an iFrame located with a {@link By}
     *
     * @param iFrame {@link By} locator for an iFrame
     * @throws NoSuchFrameException           If the given element is neither an IFRAME nor a FRAME element.
     * @throws StaleElementReferenceException If the WebElement has gone stale.
     */
    protected void switchToFrame(By iFrame) {
        webDriver.switchTo().frame(find(iFrame));
    }

    /**
     * Switch {@link WebDriver} context to default
     */
    protected void leaveFrame() {
        webDriver.switchTo().defaultContent();
    }

    /**
     * Execute {@link Consumer} of {@link WebElement} for given {@link WebElement} located with a {@link By} chain.<br/>
     * This is for scenarios when target {@link WebElement} is expected to update often, raising {@link StaleElementReferenceException}.<br/>
     * The method will try to execute {@link Consumer} for {@link AbstractPageObject#MAX_WAIT_SEC} seconds
     *
     * @param supplier element supplier function
     * @param consumer consumer to execute on located {@link WebElement}
     */
    protected void retryOnStale(Supplier<? extends WebElement> supplier, Consumer<? super WebElement> consumer) {
        long start = System.currentTimeMillis();
        do {
            try {
                consumer.accept(supplier.get());
            } catch (StaleElementReferenceException e) {
                logger.debug(DOM_UPDATE_MESSAGE, e);
                Reporter.log(DOM_UPDATE_MESSAGE + '\n');
            }
        } while (System.currentTimeMillis() - start < AbstractPageObject.MAX_WAIT_SEC * 1000);
    }

    /**
     * Execute {@link Function} of {@link WebElement} for given {@link WebElement} located with a {@link By} chain.<br/>
     * This is for scenarios when target {@link WebElement} is expected to update often, raising {@link StaleElementReferenceException}.<br/>
     * The method will try to execute {@link Function} for {@link AbstractPageObject#MAX_WAIT_SEC} seconds
     *
     * @param supplier element supplier function
     * @param function function to evaluate on located {@link WebElement}
     * @return the function result
     */
    protected <T> T retryOnStale(Supplier<? extends WebElement> supplier, Function<? super WebElement, T> function) {
        long start = System.currentTimeMillis();
        do {
            try {
                return function.apply(supplier.get());
            } catch (StaleElementReferenceException e) {
                logger.debug(DOM_UPDATE_MESSAGE, e);
                Reporter.log(DOM_UPDATE_MESSAGE + '\n');
            }
        } while (System.currentTimeMillis() - start < AbstractPageObject.MAX_WAIT_SEC * 1000);
        return null;
    }

    /**
     * Check if {@link WebElement} located with {@link By} chain exists on page
     *
     * @param first element locator chain
     * @param rest  element locator chain
     * @return <code>true</code> if element was found on page, <code>false</code> otherwise
     */
    protected boolean exists(By first, By... rest) {
        try {
            find(first, rest);
            return true;
        } catch (NoSuchElementException e) {
            logger.debug(EXPECTED_LOOKUP_FAILURE_MESSAGE, e);
            Reporter.log(EXPECTED_LOOKUP_FAILURE_MESSAGE + '\n');
            return false;
        }
    }

    /**
     * Wait until element is displayed and perform {@link WebElement#click()} on it
     *
     * @param first element locator
     * @param rest  element locator chain
     */
    protected void click(By first, By... rest) {
        waitUntilDisplayed(first, rest).click();
    }

    /**
     * Scroll web page to the element to get visibility of it
     *
     * @param element WebElement
     */
    protected WebElement scrollIntoView(WebElement element) {
        logger.info(String.format("scrolling to element: %s", element));
        executeScript("arguments[0].scrollIntoView()", element);
        return element;
    }

    /**
     * Retrieves a <code>select</code> element at the locator and wraps it in {@link Select}
     *
     * @param first element locator
     * @param rest  element locator chain
     * @return {@link Select} for the element
     */

    protected Select inSelect(By first, By... rest) {
        return inSelect(waitUntilDisplayed(first, rest));
    }

    /**
     * Creates {@link Select} for a given {@link WebElement}
     *
     * @param element source <code>WebElement</code>
     * @return {@link Select} for the element
     */
    protected static Select inSelect(WebElement element) {
        return new Select(element);
    }

    /**
     * Checks CSS property of {@link WebElement} to be equal to expected value
     *
     * @param propertyName CSS property name
     * @param expectedValue expected property value
     * @param first element locator
     * @param rest element locator chain
     * @return <code>true</code> if property value matches expected value, <code>false</code> otherwise
     */
    protected boolean checkCssProperty(String propertyName, String expectedValue, By first, By... rest) {
        return find(first, rest).getCssValue(propertyName).equals(expectedValue);
    }

    /**
     * Delete all the cookies for the current domain.
     *
     * @see WebDriver.Options#deleteAllCookies
     */
    protected void deleteAllCookies() {
        webDriver.manage().deleteAllCookies();
        Set<Cookie> allCookies = webDriver.manage().getCookies();
        allCookies.forEach(logger::error);
    }

    /**
     * Load a new web page in the current browser window. This is done using an HTTP GET operation,
     * and the method will block until the load is complete.
     *
     * @see WebDriver#get(String)
     */
    protected void get(String url) {
        webDriver.get(url);
    }

    /**
     * Maximizes the current window if it is not already maximized
     *
     * @see WebDriver.Window#maximize()
     */
    protected void maximizeWindow() {
        webDriver.manage().window().maximize();
    }

    /**
     * Quits this driver, closing every associated window
     *
     * @see WebDriver#quit()
     */
    protected void closeBrowser() {
        webDriver.quit();
    }

    /**
     * Refresh the current page
     *
     * @see WebDriver.Navigation#refresh()
     */
    protected void refreshPage() {
        webDriver.navigate().refresh();
    }

    /**
     * Executes the given script
     *
     * @see JavascriptExecutor#executeScript(String, Object...)
     */
    protected void executeScript(String script, Object... arguments) {
        ((JavascriptExecutor) webDriver).executeScript(script, arguments);
    }

    /**
     * Take screen shot of page
     *
     * @return {@link File} screen shot object
     * @see TakesScreenshot#getScreenshotAs(OutputType)
     */
    protected File takeScreenShot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
    }

    /**
     * Waits until the page is fully loaded.
     *
     * @see JavascriptExecutor#executeScript(String, Object...)
     */
    protected void waitUntilPageLoad() {
        getWait().until(wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    protected void setDisplayBlock(By first, By... rest) {
        executeScript("arguments[0].style.display='block'", find(first, rest));
    }

    protected void uploadFromPath(String path, By first, By... rest) {
        find(first, rest).sendKeys(path);
    }

    private Wait<WebDriver> getWait() {
        return new WebDriverWait(webDriver, MAX_WAIT_SEC);
    }

    private static <T> T ignoringStale(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (StaleElementReferenceException e) {
            return null;
        }
    }

    protected String getUrlAddress() {
        return webDriver.getCurrentUrl();
    }

    private static WebElement elementIfVisible(WebElement element) {
        return element.isDisplayed() ? element : null;
    }

    protected String getTabUrl(int tabId) {
        String currentTab = webDriver.getWindowHandle();
        String result="";
        ArrayList<String> availableWindows = new ArrayList(webDriver.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            result = webDriver.switchTo().window(availableWindows.get(tabId)).getCurrentUrl();
        }
        webDriver.switchTo().window(currentTab);
        return result;
    }

    protected void switchToTab(int tabId) {
        ArrayList<String> availableWindows = new ArrayList(webDriver.getWindowHandles());
        webDriver.switchTo().window(availableWindows.get(tabId));
    }

    protected String getLocalization(By key, Language language) {
        return localizations.get(key).get(language);
    }
}
