package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.CrmLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class CrmLoginPageImpl extends AbstractPageObject implements CrmLoginPage {
    @StoredId
    private By usernameField;
    @StoredId
    private By passwordField;
    @StoredId
    private By loginButton;

    public CrmLoginPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void login(String username, String password) {
        waitUntilDisplayed(usernameField).sendKeys(username);
        waitUntilDisplayed(passwordField).sendKeys(password);
        click(loginButton);
    }
}