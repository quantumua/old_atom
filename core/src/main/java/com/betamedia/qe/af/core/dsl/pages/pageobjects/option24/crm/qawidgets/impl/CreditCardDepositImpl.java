package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.crm.form.builders.CreditCardDepositBuilder;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 5/18/17.
 */
public class CreditCardDepositImpl extends AbstractPageObject implements com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.CreditCardDeposit {

    @StoredId
    private By depositAmount;
    @StoredId
    private By creditCardNumber;
    @StoredId
    private By cvv2;
    @StoredId
    private By expiryDateMonth;
    @StoredId
    private By expiryDateYear;
    @StoredId
    private By cardHoldersFirstName;
    @StoredId
    private By cardHoldersLastName;
    @StoredId
    private By billingAddress;
    @StoredId
    private By city;
    @StoredId
    private By zipCode;
    @StoredId
    private By country;
    @StoredId
    private By submit;


    public CreditCardDepositImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void submit(CreditCardDepositBuilder.CreditCardDeposit info) {
        waitUntilDisplayed(depositAmount);
        find(depositAmount).sendKeys(info.depositAmount);
        find(creditCardNumber).sendKeys(info.creditCardNumber);
        find(cvv2).sendKeys(info.cvv2);
        in(expiryDateMonth).selectByValue(info.expiryDateMonth);
        in(expiryDateYear).selectByValue(info.expiryDateYear);
        find(cardHoldersFirstName).sendKeys(info.cardHoldersFirstName);
        find(cardHoldersLastName).sendKeys(info.cardHoldersLastName);
        find(billingAddress).sendKeys(info.billingAddress);
        find(city).sendKeys(info.city);
        find(zipCode).sendKeys(info.zipCode);
        find(country).sendKeys(info.country);
        find(submit).click();
    }
}
