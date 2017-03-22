package com.betamedia.qe.af.core.pages.tp.login.impl;

import com.betamedia.qe.af.core.pages.AbstractPageObject;
import com.betamedia.qe.af.core.pages.annotation.StoredId;
import com.betamedia.qe.af.core.pages.tp.login.LoginPage;
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
        waitFor(submitButton);
        find(usernameField).sendKeys(username);
        find(passwordField).sendKeys(password);
        find(submitButton).click();
    }

//    @Override
//    public LoginPage goTo() {
//        TPPages.topNavigationPage().logIn();
//        return this;
//    }

}
