package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.dialogs;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;

public interface CreditCardDepositDialog {

    void submit(CreditCardDeposit info);
    boolean isDisplayed();
    void waitforCreditCardDepositPage();

}
