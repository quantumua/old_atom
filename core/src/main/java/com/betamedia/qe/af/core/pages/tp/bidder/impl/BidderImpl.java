package com.betamedia.qe.af.core.pages.tp.bidder.impl;

import com.betamedia.qe.af.core.pages.AbstractPageObject;
import com.betamedia.qe.af.core.pages.annotation.StoredId;
import com.betamedia.qe.af.core.pages.tp.bidder.Bidder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

/**
 * Created by mbelyaev on 3/21/17.
 */
public class BidderImpl extends AbstractPageObject implements Bidder {

    @StoredId
    private By amountInput;
    @StoredId
    private By highLowButton;
    @StoredId
    private By shortTermButton;
    @StoredId
    private By doubleProfitButton;
    @StoredId
    private By assetSelector;
    @StoredId
    private By assetSearchBox;
    @StoredId
    private By assetDropdown;
    @StoredId
    private By lowButton;
    @StoredId
    private By highButton;
    @StoredId
    private By closePosition;

    public BidderImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public Bidder highLow() {
        waitUntilDisplayed(highLowButton);
        find(highLowButton).click();
        return this;
    }

    @Override
    public Bidder shortTerm() {
        waitUntilDisplayed(shortTermButton);
        find(shortTermButton).click();
        return this;
    }

    @Override
    public Bidder doubleProfit() {
        waitUntilDisplayed(doubleProfitButton);
        find(doubleProfitButton).click();
        return this;
    }

    @Override
    public Bidder asset(String searchString) {
        waitUntilExists(assetSelector);
        if (!find(assetSelector).isDisplayed()) {
            if (!find(assetSearchBox).isDisplayed()) {
                waitUntilDisplayed(assetDropdown);
                find(assetDropdown).click();
            }
            waitUntilDisplayed(assetSearchBox);
            find(assetSearchBox).sendKeys(searchString);
        }
        waitUntilDisplayed(assetSelector);
        //TODO externalize the bmAvailable property (is it possible to use By locator for identity check?)
        assertTrue(find(assetSelector).getAttribute("class").contains("bmAvailable"), "Selected asset must be available");
        find(assetSelector).click();
        return this;
    }

    @Override
    public Bidder setAmount(String value) {
        waitUntilDisplayed(amountInput);
        WebElement inputField = find(amountInput);
        inputField.clear();
        inputField.sendKeys(value);
        return this;
    }

    @Override
    public Bidder bidLow() {
        waitUntilDisplayed(lowButton);
        find(lowButton).click();
        return this;
    }

    @Override
    public Bidder bidHigh() {
        waitUntilDisplayed(highButton);
        find(highButton).click();
        return this;
    }

    @Override
    public Bidder confirm() {
        waitUntilDisplayed(closePosition);
        waitUntil(driver -> !find(closePosition).isDisplayed());
        return this;
    }

}
