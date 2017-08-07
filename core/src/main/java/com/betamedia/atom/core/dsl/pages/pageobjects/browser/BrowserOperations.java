package com.betamedia.atom.core.dsl.pages.pageobjects.browser;

import java.io.File;

/**
 * Created by vsnigur on 5/10/17.
 */
public interface BrowserOperations {
    void deleteAllCookies();

    void get(String url);

    void maximizeWindow();

    void closeBrowser();

    void refreshPage();

    File takeScreenShot();

    void waitUntilPageLoad();

    String getTabUrl(int tabId);

    String getUrl();

    void switchToTab(int tabId);
}
