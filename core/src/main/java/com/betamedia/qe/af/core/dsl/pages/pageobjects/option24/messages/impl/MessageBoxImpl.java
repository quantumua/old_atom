package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.MessageBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 4/28/17.
 */
public class MessageBoxImpl extends AbstractPageObject implements MessageBox {
    @StoredId
    private By messageBox;
    @StoredId
    private By okButton;

    public MessageBoxImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void ok() {
        waitUntilDisplayed(messageBox, okButton);
        find(messageBox, okButton).click();
    }
}
