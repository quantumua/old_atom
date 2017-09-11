package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by lartemyev on 8/7/17.
 */
public abstract class AbstractCreditCardDeposit extends AbstractPageObject implements CreditCardDepositPage, ScriptOperations {

    @StoredId (localized = true)
    protected By depositAmount;
    @StoredId (localized = true)
    protected By creditCardNumber;
    @StoredId (localized = true)
    protected By cvv2;
    @StoredId (localized = true)
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
    @StoredId
    protected By modalClose;
    @StoredId
    protected By invalidCreditCardNumberError;
    @StoredId
    protected By errorMessageHint;


    protected AbstractCreditCardDeposit(WebDriver webDriver) {
        super(webDriver);
    }
}
