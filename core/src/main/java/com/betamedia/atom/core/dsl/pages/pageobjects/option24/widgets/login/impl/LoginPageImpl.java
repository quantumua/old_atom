package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.login.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.login.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class LoginPageImpl extends AbstractPageObject implements LoginPage {
    private static final String AUTHENTICATION_EXCEPTION = "AutenticationEX";

    @StoredId
    private By usernameField;
    @StoredId
    private By passwordField;
    @StoredId
    private By loginSubmit;
    @StoredId
    private By loginButton;
    @StoredId
    private By resultPlaceholder;

    public LoginPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void login(String username, String password) {
        waitUntil(() -> {
            find(usernameField).sendKeys(username);
            find(passwordField).sendKeys(password);
            click(loginSubmit);

            //TODO: Temporary workaround, without pause a "connection exception" is thrown in the widget.
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(find(resultPlaceholder).getText().contains(AUTHENTICATION_EXCEPTION)){
                click(loginButton);
                return false;
            }
            return true;
        });
    }
}
