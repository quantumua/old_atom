package com.betamedia.atom.core.dsl.pages.factory.widgets;

import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.UploadDocumentsPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.SetLeveragePage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.login.LoginPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.AccountDetailsPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.Navigation;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.RegisterPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl.AccountAdditionalDetailsPageImpl;

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

    SetLeveragePage setLeveragePage();

    CreditCardDepositPage creditCardDeposit();

    AccountAdditionalDetailsDialog accountAdditionalDetailsPage();

    UploadDocumentsPage uploadDocumentsPage();

}
