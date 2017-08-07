package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;

public interface CreditCardDepositPage {

    void submit(CreditCardDeposit info);
    boolean isDisplayed();
    void waitforCreditCardDepositPage();

}
