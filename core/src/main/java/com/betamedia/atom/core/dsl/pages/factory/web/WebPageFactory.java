package com.betamedia.atom.core.dsl.pages.factory.web;

import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.withdrawal.WithdrawalPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.CfdBidder;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.assets.Assets;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.orders.CfdPositions;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.DisclaimerNotification;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.LoginDialog;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.LoginErrorNotification;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.RejectMessageOnLogin;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.messages.DialogBox;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.messages.MessageBox;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.LandingPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.TopNavigationPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.usercontrolpanel.ControlPanel;

/**
 * @author Lilian Medina
 *         Date: 5/11/17.
 */
public interface WebPageFactory {

    TopNavigationPage topNavigationPage();

	LoginDialog loginDialog();

    WelcomeDialog welcomepage();

    AccountAdditionalDetailsDialog accountAdditionalDetailsPage();

    FnsPersonalInformation fnsPersonalInformation();

    FnsTradingExperience fnsTradingExperience();

    FnsEmployerInfo fnsEmployerInfo();

    RiskWarningDialog riskWarning();

    CreditCardDepositPage creditCardDepositPage();

	SignatureRiskWarning signatureRiskWarning();

	WelcomeBackMessage welcomeBackMessage();

	WithdrawalPage withdrawalPage();

    RegistrationDialog registrationDialog();

    DisclaimerNotification disclaimerNotification();

    LoginErrorNotification loginErrorNotification();

    CfdBidder cfdBidder();

    Assets assets();

    LandingPage landingPage();

    ControlPanel controlPanel();

    MessageBox messageBox();

    CfdPositions cfdPositions();

    DialogBox dialogBox();

    AccountAdditionalDetailsDialog accountAdditionalDetails();

    AccountDetailsPage accountDetails();

    CreditCardDepositPage creditCardDeposit();

    WelcomeDialog welcomeDialog();

    RiskWarningDialog riskWarningPage();

    LeveragePopup leveragePopup();

    InsufficientFundsDialog insufficientFundsDialog();

    SetLeverageDialog setLeverageDialog();

    StartTradeDialog startTradeDialog();

    RejectMessageOnLogin rejectMessage();

    ConfirmCloseMessage confirmCloseMessage();

    UploadDocumentsDialog uploadDocumentDialog();

    ConfirmAnswersDialog confirmAnswers();

    RedirectDialog redirectDialog();

    LoadingDialog loadingDialog();

    ThankYouPage thankYouPage();

    UploadDocumentsTab uploadDocumentsTab();
}
