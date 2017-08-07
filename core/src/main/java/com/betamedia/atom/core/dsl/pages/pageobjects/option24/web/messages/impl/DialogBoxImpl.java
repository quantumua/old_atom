package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.messages.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.messages.DialogBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by vsnigur on 5/15/17.
 */
public class DialogBoxImpl extends AbstractPageObject implements DialogBox {

    @StoredId
    private By dialogBox;

    @StoredId
    private By bmDialogTitle;

    @StoredId
    private By closeButton;

    @StoredId
    private By bmDialogText;

    public DialogBoxImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void close() {
        waitUntilDisplayed(dialogBox, closeButton);
        find(dialogBox, closeButton).click();
    }

    @Override
    public String getTitle() {
        waitUntilDisplayed(dialogBox);
        return find(bmDialogTitle).getText();
    }

    @Override
    public String getMessageText() {
        waitUntilDisplayed(dialogBox);
        return find(bmDialogText).getText();
    }


    @Override
    public Boolean exists() {
        waitUntilDisplayed(dialogBox);
        return find(dialogBox).isDisplayed();
    }

    @Override
    public void assertTitle(String title) {
        waitUntilDisplayed(dialogBox);
        Assert.assertEquals(find(bmDialogTitle).getText(), title);
    }

    @Override
    public void assertMessage(String message) {
        waitUntilDisplayed(dialogBox);
        Assert.assertEquals(find(bmDialogText).getText(), message);
    }
}
