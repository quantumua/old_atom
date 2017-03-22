package com.betamedia.qe.af.core.pages.tp.bidder.impl;

import com.betamedia.qe.af.core.pages.AbstractPageObject;
import com.betamedia.qe.af.core.pages.annotation.StoredId;
import com.betamedia.qe.af.core.pages.tp.bidder.Bidder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by mbelyaev on 3/21/17.
 */
public class BidderImpl extends AbstractPageObject implements Bidder {

    @StoredId("bidderAmount")
    private By bidderAmount;

    //TODO page must have element IDs according to framework spec
    private By highLowButton = By.cssSelector(".bmSelectItem.bmSelectHighLowGame.on");
    private By shortTermButton = By.cssSelector(".bmSelectItem.bmSelectShortTermGame");
    private By doubleProfitButton = By.cssSelector(".bmSelectItem.bmSelectDoubleProfitGame");
    //TODO AUDJPY temp
    private By assetSelector = By.className("bmIdAUDJPY");
    private By assetDropdown = By.className("bmCurrentAsset");
    private By lowButton = By.cssSelector(".bmButtonsWrapp.bmSelection2");
    private By highButton = By.cssSelector(".bmButtonsWrapp.bmSelection1");
    private By closePosition = By.cssSelector(".bmButtonWrapp.bmClosePositionDialog");

    public BidderImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public Bidder highLow() {
        waitFor(highLowButton);
        find(highLowButton).click();
        return this;
    }

    @Override
    public Bidder shortTerm() {
        waitFor(shortTermButton);
        find(shortTermButton).click();
        return this;
    }

    @Override
    public Bidder doubleProfit() {
        waitFor(doubleProfitButton);
        find(doubleProfitButton).click();
        return this;
    }

    @Override
    public Bidder asset() {
        if (!find(assetSelector).isDisplayed()){
            waitFor(assetDropdown);
            find(assetDropdown).click();
        }
        waitFor(assetSelector);
        find(assetSelector).click();
        return this;
    }

    @Override
    public Bidder setAmount(String value) {
        waitFor(bidderAmount);
        WebElement inputField = find(bidderAmount);
        inputField.clear();
        inputField.sendKeys(value);
        return this;
    }

    @Override
    public Bidder bidLow(){
        waitFor(lowButton);
        find(lowButton).click();
        return this;
    }

    @Override
    public Bidder bidHigh(){
        waitFor(highButton);
        find(highButton).click();
        return this;
    }

    @Override
    public Bidder closePosition() {
        waitFor(closePosition);
        WebDriverWait wait = new WebDriverWait(webDriver, MAX_WAIT_SEC);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closePosition));
        return this;
    }

}
