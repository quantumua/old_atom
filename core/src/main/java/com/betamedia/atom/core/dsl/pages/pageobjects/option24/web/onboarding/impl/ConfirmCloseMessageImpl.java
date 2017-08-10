package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.ConfirmCloseMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 6/7/17.
 */
public class ConfirmCloseMessageImpl extends AbstractPageObject implements ConfirmCloseMessage {

    @StoredId
    private By confirmCloseMessage;

    @StoredId
    private By confirmImSure;

    @StoredId
    private By confirmNo;


    public ConfirmCloseMessageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists(){
        return waitUntilExists(confirmCloseMessage).isDisplayed();
    }

    @Override
    public void acceptClose() {
        waitUntilDisplayed(confirmImSure).click();
    }

    @Override
    public void dismissClose() {
        waitUntilDisplayed(confirmNo).click();
    }
}
