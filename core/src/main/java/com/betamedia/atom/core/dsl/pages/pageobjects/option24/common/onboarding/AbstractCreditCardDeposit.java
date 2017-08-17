package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by lartemyev on 8/7/17.
 */
public abstract class AbstractCreditCardDeposit extends AbstractPageObject implements CreditCardDepositPage, ScriptOperations {

    @StoredId
    protected By depositAmount;
    @StoredId
    protected By creditCardNumber;
    @StoredId
    protected By cvv2;
    @StoredId
    protected By expiryDateMonth;
    @StoredId
    protected By expiryDateYear;
    @StoredId
    protected By cardHoldersFirstName;
    @StoredId
    protected By cardHoldersLastName;
    @StoredId
    protected By billingAddress;
    @StoredId
    protected By city;
    @StoredId
    protected By zipCode;
    @StoredId
    protected By country;
    @StoredId
    protected By submit;

    protected AbstractCreditCardDeposit(WebDriver webDriver) {
        super(webDriver);
    }
}
