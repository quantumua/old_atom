package com.betamedia.atom.core.dsl.pages.factory.tp;


import com.betamedia.atom.core.dsl.pages.pageobjects.option24.assets.Assets;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.bidder.CfdBidder;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.controlpanel.ControlPanel;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl.SetLeveragePageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.login.DisclaimerNotification;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.login.LoginErrorNotification;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.login.LoginPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.messages.DialogBox;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.messages.MessageBox;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.messages.impl.RejectMessageOnLoginImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.LandingPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.orders.CfdPositions;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TPPageFactory {
    LoginPage loginPage();

    TopNavigationPage topNavigationPage();

    DisclaimerNotification disclaimerNotification();

    LoginErrorNotification loginErrorNotification();

    CfdBidder cfdBidder();

    Assets assets();

    LandingPage landingPage();

    ControlPanel controlPanel();

    MessageBox messageBox();

    CfdPositions cfdPositions();

    DialogBox dialogBox();

    CrmNavigation crmNavigation();

    FnsTradingExperience fnsTradingExperience();

    FnsPersonalInformation fnsPersonalInformation();

    CrmLoginPage crmLoginPage();

    AccountDetailsPage accountDetails();

    AccountAdditionalDetailsPage accountAdditionalDetails();

    CreditCardDepositPage creditCardDeposit();

    RegisterPage registerPage();

    WelcomePage welcomePage();

    RiskWarning riskWarningPage();

    DocumentUploadForm documentUploadForm();

    LeveragePopup leveragePopup();

    SetLeverageDialogImpl setLeverageDialog();

    StartTradeDialogImpl startTradeDialog();

    SetLeveragePageImpl setLeveragePage();

    RejectMessageOnLoginImpl rejectMessage();

    WelcomeBackMessageImpl welcomeBackMessage();

    ConfirmCloseMessageImpl confirmCloseMessage();

    UploadDocumentDialogImpl uploadDocumentDialog();
    
    UploadDocumentsPageImpl uploadDocumentsPage();

    SignatureRiskWarning signatureRiskWarning();

    ConfirmAnswersDialog confirmAnswers();

    RegistrationDialog registrationDialog();

    RedirectDialog redirectDialog();

    LoadingDialog loadingDialog();

    ThankYouPage thankYouPage();

    FnsEmployerInfo fnsEmployerInfo();
}
