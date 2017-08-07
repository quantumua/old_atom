package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.LoginDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public class LoginDialogImpl extends AbstractPageObject implements LoginDialog {

    @StoredId("usernameField")
    private By usernameField;
    @StoredId("passwordField")
    private By passwordField;
    @StoredId("submitButton")
    private By submitButton;

    public LoginDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void login(String username, String password) {
        waitUntilDisplayed(submitButton);
        find(usernameField).sendKeys(username);
        find(passwordField).sendKeys(password);
        find(submitButton).click();
    }

	@Override
	public boolean isSubmitBtnExists() {
		return waitUntilDisplayed(submitButton) != null;
	}
}
