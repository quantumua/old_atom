package com.betamedia.atom.core.dsl.pages.factory.widgets;

import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.impl.SetLeveragePageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.RegistrationPage;

/**
 * Created by vsnigur on 7/31/17.
 */
public interface WidgetsPageFactory {

    LoginPage loginPage();

    Navigation navigation();

    FnsTradingExperience fnsTradingExperience();

    FnsPersonalInformation fnsPersonalInformation();

    FnsEmployerInfo fnsEmployerInfo();

    RegisterPage registerPage();

    AccountDetailsPage accountDetails();

    RegistrationPage registrationPage();

    SetLeveragePageImpl setLeveragePage();

    CreditCardDepositPage creditCardDeposit();

    AccountAdditionalDetailsPage accountAdditionalDetailsPage();
}
