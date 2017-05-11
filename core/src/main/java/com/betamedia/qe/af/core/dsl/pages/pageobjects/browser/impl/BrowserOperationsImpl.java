package com.betamedia.qe.af.core.dsl.pages.pageobjects.browser.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.browser.BrowserOperations;
import org.openqa.selenium.WebDriver;

/**
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
}
