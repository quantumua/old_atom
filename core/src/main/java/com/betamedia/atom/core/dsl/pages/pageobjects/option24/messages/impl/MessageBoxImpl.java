package com.betamedia.atom.core.dsl.pages.pageobjects.option24.messages.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.messages.MessageBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by mbelyaev on 4/28/17.
 */
public class MessageBoxImpl extends AbstractPageObject implements MessageBox {
    @StoredId
    private By messageBox;
    @StoredId
    private By okButton;
    @StoredId
    private By requoteTrade;
    @StoredId
    private By buttonsBlock;
    @StoredId
    private By firstButton;

    public MessageBoxImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void ok() {
        waitUntil(() -> tryFind(messageBox, okButton)
                .orElseGet(() -> find(requoteTrade, buttonsBlock, firstButton))
                .isDisplayed());
        tryFind(requoteTrade, buttonsBlock, firstButton).ifPresent(WebElement::click);
        waitUntilDisplayed(messageBox, okButton);
        find(messageBox, okButton).click();
        waitUntil(() -> !exists(messageBox));
    }

}
