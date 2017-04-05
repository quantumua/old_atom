package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginPage;
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
        waitUntilDisplayed(submitButton);
        find(usernameField).sendKeys(username);
        find(passwordField).sendKeys(password);
        find(submitButton).click();
    }

}