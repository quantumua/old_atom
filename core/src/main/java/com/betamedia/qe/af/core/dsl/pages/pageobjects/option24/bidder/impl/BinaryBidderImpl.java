package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.BinaryBidder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by mbelyaev on 3/21/17.
 */
public class BinaryBidderImpl extends AbstractPageObject implements BinaryBidder {

    @StoredId
    private By amountInput;
    @StoredId
    private By lowButton;
    @StoredId
    private By highButton;
    @StoredId
    private By closePosition;
    @StoredId
    private By errorMinAmount;
    @StoredId
    private By errorMaxAmount;
    @StoredId
    private By errorValidAmount;

    public BinaryBidderImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public BinaryBidder setAmount(String value) {
        waitUntilDisplayed(amountInput);
        WebElement inputField = find(amountInput);
        inputField.clear();
        inputField.sendKeys(value);
        return this;
    }

    @Override
    public BinaryBidder bidLow() {
        waitUntilDisplayed(lowButton);
        find(lowButton).click();
        return this;
    }

    @Override
    public BinaryBidder bidHigh() {
        waitUntilDisplayed(highButton);
        find(highButton).click();
        return this;
    }

    @Override
    public BinaryBidder confirm() {
        waitUntilDisplayed(closePosition);
        waitUntil(() -> !find(closePosition).isDisplayed());
        return this;
    }

    @Override
    public String getMaxAmountMessage() {
        waitUntilDisplayed(errorMaxAmount);
        return find(errorMaxAmount).getText();
    }

    @Override
    public String getMinAmountMessage() {
        waitUntilDisplayed(errorMinAmount);
        return find(errorMinAmount).getText();
    }

    @Override
    public String getNotValidAmountMessage() {
        waitUntilDisplayed(errorValidAmount);
        return find(errorValidAmount).getText();
    }

    @Override
    public void assertMaxAmountMessage(String message) {
        Assert.assertTrue(find(errorMaxAmount).isDisplayed());
        Assert.assertEquals(find(errorMaxAmount).getText(),message);
    }

    @Override
    public void assertMinAmountMessage(String message) {
        Assert.assertTrue(find(errorMinAmount).isDisplayed());
        Assert.assertEquals(find(errorMinAmount).getText(),message);
    }

    @Override
    public void assertValidAmountMessage(String message) {
        Assert.assertTrue(find(errorValidAmount).isDisplayed());
        Assert.assertEquals(find(errorValidAmount).getText(),message);
    }
}
