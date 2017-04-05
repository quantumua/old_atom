package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public class TopNavigationPageImpl extends AbstractPageObject implements TopNavigationPage {

    @StoredId
    private By mainMenu;
    @StoredId("loginBtn")
    private By loginBtn;
    @StoredId("myAccountBtn")
    private By myAccountBtn;
    @StoredId
    private By binaryBtn;

    public TopNavigationPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isLoggedIn() {
        waitUntilDisplayed(myAccountBtn);
        return find(myAccountBtn).isDisplayed();
    }

    @Override
    public void logIn() {
        waitUntilDisplayed(loginBtn);
        find(loginBtn).click();
    }

    @Override
    public void binary() {
        waitUntilDisplayed(mainMenu);
        find(mainMenu, binaryBtn).click();
    }

}
