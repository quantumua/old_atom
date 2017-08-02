package com.betamedia.atom.core.dsl.pages.factory.web;

import com.betamedia.atom.core.dsl.pages.pageobjects.option24.assets.Assets;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.bidder.CfdBidder;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.controlpanel.ControlPanel;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.login.DisclaimerNotification;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.login.LoginErrorNotification;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.messages.DialogBox;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.messages.MessageBox;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.messages.impl.RejectMessageOnLoginImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.LandingPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.login.LoginDialog;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.orders.CfdPositions;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.dialogs.AccountAdditionalDetailsDialog;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.*;

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

	WelcomeBackMessageImpl welcomeBackMessage();

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

    CreditCardDepositPage creditCardDeposit();

    WelcomeDialogImpl welcomeDialog();

    RiskWarningDialog riskWarningPage();

    LeveragePopup leveragePopup();

    SetLeverageDialogImpl setLeverageDialog();

    StartTradeDialogImpl startTradeDialog();

    RejectMessageOnLoginImpl rejectMessage();

    ConfirmCloseMessageImpl confirmCloseMessage();

    UploadDocumentDialogImpl uploadDocumentDialog();

    DocumentUploadForm documentUploadForm();

    ConfirmAnswersDialog confirmAnswers();

    RedirectDialog redirectDialog();

    LoadingDialog loadingDialog();

    ThankYouPage thankYouPage();

}
