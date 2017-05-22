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
    @StoredId
    private By cfdBtn;
    @StoredId
    private By signUpBtn;
    
    
    public TopNavigationPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void signUp() {
    	waitUntilDisplayed(signUpBtn);
         find(mainMenu, signUpBtn).click();
    }
    
    @Override
    public boolean isLoggedIn() {
        return waitUntilDisplayed(myAccountBtn) != null;
    }

    @Override
    public boolean isLoggedOut() {
        return waitUntilDisplayed(loginBtn) != null;
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
        waitUntilDisplayed(mainMenu);
    }

    @Override
    public void cfd() {
        waitUntilDisplayed(mainMenu);
        find(mainMenu, cfdBtn).click();
    }

}
