package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations;

import java.util.List;

public interface CreditCardDepositPage extends LocalizationOperations {

    void submit(CreditCardDeposit info);
    boolean isDisplayed();
    void waitforCreditCardDepositPage();
    void close();

    boolean invalidCreditCardNumberErrorExists();
    String getErrorMessageHint();
    String getCreditCardNumber();
    String getCreditCardCity();
    String getCreditCardZipCode();
    List<String> getExpiryDateMonthList();
    void selectExpiryDateMonth();
    void expandDropDownButton();
    int getExpiryDateMonthSelectedItem();
    List<String> getExpiryDateYearList();
    void expandYearDropDownButton();
    void selectExpiryDateYear();
    int getExpiryDateYearSelectedItem();
    String getSelectedCountryName();
    void scrollToCountry();
    void moveCursorToSubmitButton();
    String getSubmitButtonCollor();
}
