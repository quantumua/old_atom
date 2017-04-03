package com.betamedia.qe.af.core.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

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

    protected void waitUntilDisplayed(By element) {
        Wait<WebDriver> wait = new WebDriverWait(webDriver, MAX_WAIT_SEC);
        wait.until(driver -> driver.findElement(element).isDisplayed());
    }

    protected void waitUntilExists(By element) {
        Wait<WebDriver> wait = new WebDriverWait(webDriver, MAX_WAIT_SEC);
        wait.until(driver -> !driver.findElements(element).isEmpty());
    }

    protected void waitUntil(Function<? super WebDriver, Boolean> isTrue) {
        Wait<WebDriver> wait = new WebDriverWait(webDriver, MAX_WAIT_SEC);
        wait.until(isTrue);
    }

    protected WebElement find(By by) {
        return webDriver.findElement(by);
    }

}
