package com.betamedia.qe.af.core.dsl.pages.pageobjects.browser;

/**
 * Created by vsnigur on 5/10/17.
 */
public interface BrowserOperations {
    void deleteAllCookies();

    void get(String url);

    void maximizeWindow();

    void closeBrowser();

    void refreshPage();
}
