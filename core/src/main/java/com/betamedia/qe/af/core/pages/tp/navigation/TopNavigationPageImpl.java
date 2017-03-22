package com.betamedia.qe.af.core.pages.tp.navigation;

import com.betamedia.qe.af.core.pages.AbstractPageObject;
import com.betamedia.qe.af.core.pages.annotation.StoredId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public class TopNavigationPageImpl extends AbstractPageObject implements TopNavigationPage {

    @StoredId("loginBtn")
    private By loginBtn;
    @StoredId("myAccountBtn")
    private By myAccountBtn;

    public TopNavigationPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isLoggedIn() {
        return find(myAccountBtn).isDisplayed();
    }

    @Override
    public void logIn() {
        waitFor(loginBtn);
        find(loginBtn).click();
    }
}
