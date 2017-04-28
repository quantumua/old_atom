package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.CfdBidder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by mbelyaev on 4/28/17.
 */
public class CfdBidderImpl extends AbstractPageObject implements CfdBidder {
    @StoredId
    private By volumeInput;
    @StoredId
    private By buyButton;
    @StoredId
    private By sellButton;
    @StoredId
    private By tradeButton;

    public CfdBidderImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public CfdBidder setAmount(String value) {
        waitUntilDisplayed(volumeInput);
        WebElement inputField = find(volumeInput);
        inputField.clear();
        inputField.sendKeys(value);
        return this;
    }

    @Override
    public CfdBidder sell() {
        waitUntilDisplayed(sellButton);
        find(sellButton).click();
        return this;
    }

    @Override
    public CfdBidder buy() {
        waitUntilDisplayed(buyButton);
        find(buyButton).click();
        return this;
    }

    @Override
    public CfdBidder confirm() {
        waitUntilDisplayed(tradeButton);
        find(tradeButton).click();
        return this;
    }
}
