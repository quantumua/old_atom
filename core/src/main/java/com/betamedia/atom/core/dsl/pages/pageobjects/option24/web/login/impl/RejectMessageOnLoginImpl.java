package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.RejectMessageOnLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by vsnigur on 6/2/17.
 */
public class RejectMessageOnLoginImpl extends AbstractPageObject implements RejectMessageOnLogin {
    @StoredId
    private  By rejectCaption;

    public RejectMessageOnLoginImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void isDisplayed() {
        Assert.assertTrue(waitUntilDisplayed(rejectCaption).isDisplayed());
    }
}
