package com.betamedia.qe.af.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class AbstractPageObject {

    private final int MAX_WAIT_SEC = 60;

    protected WebDriver webDriver;

    protected AbstractPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected abstract By getLocator();

    protected void waitFor() {
        WebDriverWait wait = new WebDriverWait(webDriver, MAX_WAIT_SEC);
        wait.until(ExpectedConditions.elementToBeClickable(getLocator()));
    }
}
