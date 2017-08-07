package com.betamedia.atom.core.dsl.pages.factory.widgets;

import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.CreditCardDepositPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.FnsEmployerInfo;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.FnsPersonalInformation;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.FnsTradingExperience;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.UploadDocumentsPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.AccountAdditionalDetailsPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.SetLeveragePage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.login.LoginPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.AccountDetailsPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.Navigation;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.RegisterPage;

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

    AccountAdditionalDetailsPage accountAdditionalDetailsPage();

    UploadDocumentsPage uploadDocumentsPage();

}
