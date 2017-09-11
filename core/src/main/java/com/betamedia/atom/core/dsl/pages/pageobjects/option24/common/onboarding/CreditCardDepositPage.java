package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations;

public interface CreditCardDepositPage extends LocalizationOperations {

    void submit(CreditCardDeposit info);
    boolean isDisplayed();
    void waitforCreditCardDepositPage();
    void close();

    boolean invalidCreditCardNumberErrorExists();
    String getErrorMessageHint();
    String getCreditCardNumber();
}
