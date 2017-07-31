package com.betamedia.atom.core.dsl.pages.factory.web;


import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.login.LoginPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl.WelcomeBackMessageImpl;


/**
 * @author Lilian Medina
 *         Date: 5/11/17.
 */
public interface WEBPageFactory {

    RegistrationPage registrationPage();

    LoginPage loginPage();

    TopNavigationPage topNavigationPage();
    
    WelcomePage welcomepage();
    
    AccountAdditionalDetailsPage accountAdditionalDetailsPage();     
    
    FnsPersonalInformation fnsPersonalInformation();
    
    FnsTradingExperience fnsTradingExperience();
    
    RiskWarning riskwarning();
    
    CreditCardDepositPage creditCardDepositPage();

    DocumentUploadForm documentUploadForm();

    SignatureRiskWarning signatureRiskWarning();

    WelcomeBackMessageImpl welcomeBackMessage();

    WithdrawalPage withdrawalPage();

}
