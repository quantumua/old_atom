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

    protected static final int MAX_WAIT_SEC = 60;

    private WebDriver webDriver;

    protected AbstractPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected boolean waitUntilDisplayed(By element) {
        return getWait().until(driver -> find(element).isDisplayed());
    }

    protected boolean waitUntilDisplayed(By... elements) {
        return getWait().until(driver -> find(elements).isDisplayed());
    }

    protected boolean waitUntilExists(By element) {
        return getWait().until(driver -> !driver.findElements(element).isEmpty());
    }

    protected boolean waitUntil(Supplier<Boolean> isTrue) {
        return getWait().until(d -> isTrue.get());
    }

    protected WebElement find(By by) {
        return webDriver.findElement(by);
    }

    protected WebElement find(By... bys) {
        return find(Arrays.asList(bys));
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

    protected Actions makeActions() {
        return new Actions(webDriver);
    }

    protected <T> T inIFrame(Supplier<T> supplier, By iFrame) {
        try {
            switchToFrame(iFrame);
            return supplier.get();
        } finally {
            leaveFrame();
        }
    }

    protected <T> T inIFrame(Supplier<T> supplier, By... iFrames) {
        try {
            Arrays.stream(iFrames).forEach(this::switchToFrame);
            return supplier.get();
        } finally {
            leaveFrame();
        }
    }

    private Wait<WebDriver> getWait() {
        return new WebDriverWait(webDriver, MAX_WAIT_SEC);
    }

    private void switchToFrame(By iFrame) {
        webDriver.switchTo().frame(find(iFrame));
    }

    private void leaveFrame() {
        webDriver.switchTo().defaultContent();
    }

    protected void performOnUpdatingElement(Consumer<WebElement> consumer, By... by) {
        List<By> bys = Arrays.asList(by);
        long start = System.currentTimeMillis();
        do {
            try {
                consumer.accept(find(bys));
            } catch (StaleElementReferenceException e) {
                logger.debug(DOM_UPDATE_MESSAGE, e);
                Reporter.log(DOM_UPDATE_MESSAGE);
            }
        } while (System.currentTimeMillis() - start < AbstractPageObject.MAX_WAIT_SEC * 1000);
    }

    protected <T> T retrieveFromUpdatingElement(Function<WebElement, T> function, By... by) {
        List<By> bys = Arrays.asList(by);
        long start = System.currentTimeMillis();
        do {
            try {
                return function.apply(find(bys));
            } catch (StaleElementReferenceException e) {
                logger.debug(DOM_UPDATE_MESSAGE, e);
                Reporter.log(DOM_UPDATE_MESSAGE + "\n");
            }
        } while (System.currentTimeMillis() - start < AbstractPageObject.MAX_WAIT_SEC * 1000);
        return null;
    }

    protected boolean exists(By... by) {
        try {
            find(by);
            return true;
        } catch (NoSuchElementException e) {
            logger.debug(EXPECTED_LOOKUP_FAILURE_MESSAGE, e);
            Reporter.log(EXPECTED_LOOKUP_FAILURE_MESSAGE + "\n");
            return false;
        }
    }
}
