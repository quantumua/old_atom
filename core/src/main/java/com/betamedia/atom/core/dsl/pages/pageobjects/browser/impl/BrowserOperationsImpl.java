package com.betamedia.atom.core.dsl.pages.pageobjects.browser.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.pageobjects.browser.BrowserOperations;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Pseudo-PageObject that exposes browser-related operations on current {@link WebDriver}
 *
 * Created by vsnigur on 5/10/17.
 */
public class BrowserOperationsImpl extends AbstractPageObject implements BrowserOperations {

    public BrowserOperationsImpl(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * delete all cookies in the web browser for current webdriver session
     */
    @Override
    public void deleteAllCookies() {
        super.deleteAllCookies();
    }

    /**
     * get url into web browser address by webdriver current session
     * @param url
     */
    @Override
    public void get(String url) {
        super.get(url);
    }

    /**
     * maximize  web browser window before test actions
     */
    @Override
    public void maximizeWindow() {
        super.maximizeWindow();
    }

    /**
     * close web browser for current webdriver session in thread
     */
    @Override
    public void closeBrowser() {
        super.closeBrowser();
    }

    /**
     * refresh browser page content
     */
    @Override
    public void refreshPage() {
        super.refreshPage();
    }

    /**
     * Take screenshot of page
     * @return {@link File} screen shot object
     */
    @Override
    public File takeScreenShot() {
        return super.takeScreenShot();
    }

    /**
     * Waits until the page is fully loaded.
     */
    @Override
    public void waitUntilPageLoad() {
        super.waitUntilPageLoad();
    }

    @Override
    public String getTabUrl(int tabId) {
        return super.getTabUrl(tabId);
    }

    @Override
    public String getUrl() {
        return super.getUrlAddress();
    }

    @Override
    public void switchToTab(int tabId) {
        super.switchToTab(tabId);
    }
}
