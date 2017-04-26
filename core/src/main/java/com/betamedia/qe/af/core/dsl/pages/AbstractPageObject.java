package com.betamedia.qe.af.core.dsl.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class AbstractPageObject {

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

    protected WebElement find(By... by) {
        List<By> bys = Arrays.asList(by);
        return find(find(bys.get(0)), bys.subList(1, bys.size()));
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

    private WebElement find(WebElement webElement, List<By> bys) {
        if (bys.isEmpty()) {
            return webElement;
        }
        return find(webElement.findElement(bys.get(0)), bys.subList(1, bys.size()));
    }

}
