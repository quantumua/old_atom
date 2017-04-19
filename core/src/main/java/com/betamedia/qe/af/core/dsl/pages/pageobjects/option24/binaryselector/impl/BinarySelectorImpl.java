package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.BinarySelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 4/18/17.
 */
public class BinarySelectorImpl extends AbstractPageObject implements BinarySelector {

    @StoredId
    private By highLowButton;
    @StoredId
    private By shortTermButton;
    @StoredId
    private By doubleProfitButton;

    public BinarySelectorImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public BinarySelector highLow() {
        waitUntilDisplayed(highLowButton);
        find(highLowButton).click();
        return this;
    }

    @Override
    public BinarySelector shortTerm() {
        waitUntilDisplayed(shortTermButton);
        find(shortTermButton).click();
        return this;
    }

    @Override
    public BinarySelector doubleProfit() {
        waitUntilDisplayed(doubleProfitButton);
        find(doubleProfitButton).click();
        return this;
    }

}
