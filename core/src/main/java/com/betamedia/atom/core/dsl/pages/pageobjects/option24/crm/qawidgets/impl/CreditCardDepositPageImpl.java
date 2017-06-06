package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.CreditCardDepositPage;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 5/18/17.
 */
public class CreditCardDepositPageImpl extends AbstractPageObject implements CreditCardDepositPage {

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


    public CreditCardDepositPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void submit(CreditCardDeposit info) {
        click(depositAmount);
        find(depositAmount).sendKeys(info.depositAmount);
        click(creditCardNumber);
        find(creditCardNumber).sendKeys(info.creditCardNumber);
        find(cvv2).sendKeys(info.cvv2);
        in(expiryDateMonth).selectByValue(info.expiryDateMonth);
        in(expiryDateYear).selectByValue(info.expiryDateYear);
        find(cardHoldersFirstName).sendKeys(info.cardHoldersFirstName);
        find(cardHoldersLastName).sendKeys(info.cardHoldersLastName);
        find(billingAddress).sendKeys(info.billingAddress);
        find(city).sendKeys(info.city);
        find(zipCode).sendKeys(info.zipCode);

        if (info.country != null) {
            find(country).sendKeys(info.country);
        }

        find(submit).click();
    }
}
