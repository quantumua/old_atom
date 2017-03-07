package com.betamedia.qe.af.pages.tp.login.impl;

import com.betamedia.qe.af.entities.page.AbstractPageObject;
import com.betamedia.qe.af.pages.common.annotation.StoredId;
import com.betamedia.qe.af.pages.tp.login.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public class LoginPageImpl extends AbstractPageObject implements LoginPage {

    @StoredId("usernameField")
    private By usernameField;
    @StoredId("passwordField")
    private By passwordField;
    @StoredId("submitButton")
    private By submitButton;

    public LoginPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void login(String username, String password) {
        webDriver.findElement(usernameField).sendKeys(username);
        webDriver.findElement(passwordField).sendKeys(password);
        webDriver.findElement(submitButton).click();
    }

//    @Override
//    public LoginPage goTo() {
//        TPPages.topNavigationPage().logIn();
//        return this;
//    }

    @Override
    public By getLocator() {
        return submitButton;
    }
}
