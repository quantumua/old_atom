package com.betamedia.qe.af.core.dsl.pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.Arrays;
import java.util.List;
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

    protected AbstractPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Wait until element is displayed ({@link WebElement#isDisplayed()})
     *
     * @param element locator for element
     * @return <code>true</code> when element is displayed
     */
    protected boolean waitUntilDisplayed(By element) {
        return getWait().until(driver -> find(element).isDisplayed());
    }

    /**
     * Wait until element is displayed ({@link WebElement#isDisplayed()})
     *
     * @param elements locator chain for element
     * @return <code>true</code> when element is displayed
     */
    protected boolean waitUntilDisplayed(By... elements) {
        return getWait().until(driver -> find(elements).isDisplayed());
    }

    /**
     * Wait until element exists on page (not necessarily visible)
     *
     * @param element locator for element
     * @return <code>true</code> when element is found
     */
    protected boolean waitUntilExists(By element) {
        return getWait().until(driver -> !driver.findElements(element).isEmpty());
    }

    /**
     * Wait until arbitrary condition is true
     *
     * @param isTrue condition to evaluate
     * @return <code>true</code> when condition is true
     */
    protected boolean waitUntil(Supplier<Boolean> isTrue) {
        return getWait().until(d -> isTrue.get());
    }

    /**
     * Find the first {@link WebElement} using the by locator {@link By}
     *
     * @param by element locator
     * @return The first matching element on the current page
     * @throws NoSuchElementException If no matching elements are found
     */
    protected WebElement find(By by) {
        return webDriver.findElement(by);
    }

    /**
     * Find the first {@link WebElement} using the by locator chain of {@link By}
     *
     * @param by element locator chain
     * @return The first matching element on the current page
     * @throws NoSuchElementException If no matching elements are found
     */
    protected WebElement find(By... by) {
        return find(Arrays.asList(by));
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
     * Evaluate {@link Supplier} value for given iFrame located with a {@link By}
     *
     * @param supplier function to evaluate
     * @param iFrame   {@link By} locator for an iFrame
     * @return return value of supplier
     * @throws NoSuchFrameException           If the given element is neither an IFRAME nor a FRAME element.
     * @throws StaleElementReferenceException If the WebElement has gone stale.
     */
    protected <T> T inIFrame(Supplier<T> supplier, By iFrame) {
        try {
            switchToFrame(iFrame);
            return supplier.get();
        } finally {
            leaveFrame();
        }
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
            Arrays.stream(iFrames).forEach(this::switchToFrame);
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
     * @param consumer consumer to execute on located {@link WebElement}
     * @param by       element locator chain
     */
    protected void performOnUpdatingElement(Consumer<WebElement> consumer, By... by) {
        List<By> bys = Arrays.asList(by);
        long start = System.currentTimeMillis();
        do {
            try {
                consumer.accept(find(bys));
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
     * @param function function to evaluate on located {@link WebElement}
     * @param by       element locator chain
     * @return the function result
     */
    protected <T> T retrieveFromUpdatingElement(Function<WebElement, T> function, By... by) {
        List<By> bys = Arrays.asList(by);
        long start = System.currentTimeMillis();
        do {
            try {
                return function.apply(find(bys));
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
     * @param by element locator chain
     * @return <code>true</code> if element was found on page, <code>false</code> otherwise
     */
    protected boolean exists(By... by) {
        try {
            find(by);
            return true;
        } catch (NoSuchElementException e) {
            logger.debug(EXPECTED_LOOKUP_FAILURE_MESSAGE, e);
            Reporter.log(EXPECTED_LOOKUP_FAILURE_MESSAGE + '\n');
            return false;
        }
    }

    /**
     * Delete all the cookies for the current domain.
     *
     * @see WebDriver.Options#deleteAllCookies
     */
    protected void deleteAllCookies() {
        webDriver.manage().deleteAllCookies();
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

    private Wait<WebDriver> getWait() {
        return new WebDriverWait(webDriver, MAX_WAIT_SEC);
    }

    private WebElement find(List<By> bys) {
        return find(find(bys.get(0)), bys.subList(1, bys.size()));
    }

    private WebElement find(WebElement webElement, List<By> bys) {
        if (bys.isEmpty()) {
            return webElement;
        }
        return find(webElement.findElement(bys.get(0)), bys.subList(1, bys.size()));
    }

}
