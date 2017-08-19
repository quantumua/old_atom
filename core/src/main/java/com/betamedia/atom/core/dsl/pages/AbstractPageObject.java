package com.betamedia.atom.core.dsl.pages;

import com.betamedia.atom.core.dsl.pages.extensions.WaitOperations;
import com.betamedia.atom.core.dsl.pages.extensions.WebElementOperations;
import com.betamedia.atom.core.dsl.pages.extensions.base.*;
import com.betamedia.atom.core.dsl.pages.localization.LocalizationStorage;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class AbstractPageObject implements Finding, Localizing, Waiting, ExecutesScripts, FrameSwitching, WithActions, Logging, WebElementOperations, WaitOperations {
    private static final int MAX_WAIT_SEC = 60;
    private static final Logger logger = LogManager.getLogger(AbstractPageObject.class);

    private WebDriver webDriver;
    private LocalizationStorage localizations;

    protected AbstractPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Find the first {@link WebElement} using the by locator chain of {@link By}
     *
     * @param first element locator
     * @param rest  element locator chain
     * @return The first matching element on the current page
     * @throws NoSuchElementException If no matching elements are found
     */
    @Override
    public WebElement find(By first, By... rest) {
        WebElement element = webDriver.findElement(first);
        for (By b : rest) {
            element = element.findElement(b);
        }
        return element;
    }

    /**
     * Find the list of elements using the by locator {@link By}
     *
     * @param by element locator
     * @return list of detected elements
     */
    @Override
    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    @Override
    public Wait<WebDriver> getWait() {
        return new WebDriverWait(webDriver, MAX_WAIT_SEC);
    }

    @Override
    public String getLocalization(By key, Language language) {
        return localizations.get(key).get(language);
    }

    /**
     * Executes the given script
     *
     * @see JavascriptExecutor#executeScript(String, Object...)
     */
    @Override
    public void executeScript(String script, Object... arguments) {
        ((JavascriptExecutor) webDriver).executeScript(script, arguments);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public int getTimeout() {
        return MAX_WAIT_SEC;
    }

    /**
     * Create {@link Actions} object to construct complex gestures
     *
     * @return {@link Actions} object for active {@link WebDriver}
     */
    @Override
    public Actions makeActions() {
        return new Actions(webDriver);
    }

    /**
     * Switch {@link WebDriver} context to an iFrame located with a {@link By}
     *
     * @param iFrame {@link By} locator for an iFrame
     * @throws NoSuchFrameException           If the given element is neither an IFRAME nor a FRAME element.
     * @throws StaleElementReferenceException If the WebElement has gone stale.
     */
    @Override
    public void switchToFrame(By iFrame) {
        webDriver.switchTo().frame(waitUntilDisplayed(iFrame));
    }

    /**
     * Switch {@link WebDriver} context to default
     */
    @Override
    public void leaveFrame() {
        webDriver.switchTo().defaultContent();
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
     * Take screen shot of page
     *
     * @return {@link File} screen shot object
     * @see TakesScreenshot#getScreenshotAs(OutputType)
     */
    protected File takeScreenShot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
    }

    protected void uploadFromPath(String path, By first, By... rest) {
        waitUntilDisplayed(first, rest).sendKeys(path);
    }


    /**
     * This is special method to clear text for editable fields like "Upload Input"
     * It sometimes doesn't allow to clear as not editable, so we need to catch exception
     */
    protected void clearElementText(By element) {
        try {
            find(element).clear();
        } catch (Exception e) {
            getLogger().info("Unable to clear text in element: " + element);
        }
    }

    protected String getUrlAddress() {
        return webDriver.getCurrentUrl();
    }

    protected String getTabUrl(int tabId) {
        String currentTab = webDriver.getWindowHandle();
        String result="";
        List<String> availableWindows = new ArrayList<>(webDriver.getWindowHandles());
        if (!availableWindows.isEmpty()) {
            result = webDriver.switchTo().window(availableWindows.get(tabId)).getCurrentUrl();
        }
        webDriver.switchTo().window(currentTab);
        return result;
    }

    protected void switchToTab(int tabId) {
        List<String> availableWindows = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(availableWindows.get(tabId));
    }
}
