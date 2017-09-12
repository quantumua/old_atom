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
    protected By chatLink;
    @StoredId (localized = true)
    protected By depositColumn;
    @StoredId (localized = true)
    protected By depositColumnDescription;
    @StoredId (localized = true)
    protected By depositAmount;
    @StoredId (localized = true)
    protected By creditCardNumber;
    @StoredId (localized = true)
    protected By cvv2;
    @StoredId
    protected By expiryDateMonth;
    @StoredId (localized = true)
    protected By expiryDateMonthCaption; // the first element of DropDown menu
    @StoredId
    protected By expiryDateMonthDropDownElements;
    @StoredId
    protected By expiryDateMonthDropDownSelectItem;
    @StoredId
    protected By expiryDateYear;
    @StoredId (localized = true)
    protected By expiryDateYearCaption; // the first element of DropDown menu
    @StoredId
    protected By expiryDateYearDropDownElements;
    @StoredId
    protected By expiryDateYearDropDownSelectItem;
    @StoredId (localized = true)
    protected By cardHoldersFirstName;
    @StoredId (localized = true)
    protected By cardHoldersLastName;
    @StoredId (localized = true)
    protected By billingAddress;
    @StoredId (localized = true)
    protected By city;
    @StoredId (localized = true)
    protected By zipCode;
    @StoredId
    protected By country;
    @StoredId (localized = true)
    protected By countryCaption; // the first element of DropDown menu
    @StoredId
    protected By countryScrollToElement;
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
