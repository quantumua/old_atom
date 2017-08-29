package com.betamedia.atom.core.dsl.pages.factory.web;

import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.assets.Assets;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.assets.impl.AssetsImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.CfdBidder;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.impl.CfdBidderImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.RejectMessageOnLogin;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.usercontrolpanel.ControlPanel;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.usercontrolpanel.impl.ControlPanelImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.withdrawal.WithdrawalPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.withdrawal.impl.WithdrawalPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.DisclaimerNotification;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.LoginDialog;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.LoginErrorNotification;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.impl.DisclaimerNotificationImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.impl.LoginDialogImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.impl.LoginErrorNotificationImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.messages.DialogBox;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.messages.MessageBox;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.messages.impl.DialogBoxImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.messages.impl.MessageBoxImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.impl.RejectMessageOnLoginImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.LandingPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.impl.LandingPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.TopNavigationPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.impl.TopNavigationPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.orders.CfdPositions;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.orders.impl.CfdPositionsImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl.AccountAdditionalDetailsDialogImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl.WebFnsEmployerInformationImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl.WebFnsPersonalInformationImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl.WebFnsTradingExperienceImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl.CreditCardDepositPageImpl;
import com.betamedia.atom.core.dsl.type.ProductType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * @author Lilian Medina
 *         Date: 5/11/17.
 */
@Lazy
@Component
@Scope(SCOPE_PROTOTYPE)
public class WebPageFactoryImpl extends AbstractPageFactory implements WebPageFactory {

    @Override
    public ProductType getType() {
        return ProductType.WEB;
    }

    @Override
    public LoginDialog loginDialog() {
        return creator.getPage(LoginDialogImpl.class);
    }

    @Override
    public TopNavigationPage topNavigationPage() {
        return creator.getPage(TopNavigationPageImpl.class);
    }

    @Override
    public WelcomeDialog welcomepage() {
        return creator.getPage(WelcomeDialogImpl.class);
    }

    @Override
    public WelcomeBackMessageImpl welcomeBackMessage() {
        return creator.getPage(WelcomeBackMessageImpl.class);
    }

    @Override
    public AccountAdditionalDetailsDialog accountAdditionalDetailsPage() {
        return creator.getPage(AccountAdditionalDetailsDialogImpl.class);
    }

    @Override
    public FnsPersonalInformation fnsPersonalInformation() {
        return creator.getPage(WebFnsPersonalInformationImpl.class);
    }

    @Override
    public FnsTradingExperience fnsTradingExperience() {
        return creator.getPage(WebFnsTradingExperienceImpl.class);
    }

    @Override
    public FnsEmployerInfo fnsEmployerInfo() {
        return creator.getPage(WebFnsEmployerInformationImpl.class);
    }

    @Override
    public RiskWarningDialog riskWarning() {
        return creator.getPage(RiskWarningDialogImpl.class);
    }

    @Override
    public CreditCardDepositPage creditCardDepositPage() {
        return creator.getPage(CreditCardDepositPageImpl.class);
    }

    @Override
    public WithdrawalPage withdrawalPage() {
        return creator.getPage(WithdrawalPageImpl.class);
    }

    @Override
    public DialogBox dialogBox() {
        return creator.getPage(DialogBoxImpl.class);
    }

    @Override
    public DisclaimerNotification disclaimerNotification() {
        return creator.getPage(DisclaimerNotificationImpl.class);
    }

    @Override
    public LoginErrorNotification loginErrorNotification() {
        return creator.getPage(LoginErrorNotificationImpl.class);
    }

    @Override
    public CfdBidder cfdBidder() {
        return creator.getPage(CfdBidderImpl.class);
    }

    @Override
    public Assets assets() {
        return creator.getPage(AssetsImpl.class);
    }

    @Override
    public LandingPage landingPage() {
        return creator.getPage(LandingPageImpl.class);
    }

    @Override
    public ControlPanel controlPanel() {
        return creator.getPage(ControlPanelImpl.class);
    }

    @Override
    public MessageBox messageBox() {
        return creator.getPage(MessageBoxImpl.class);
    }

    @Override
    public CfdPositions cfdPositions() {
        return creator.getPage(CfdPositionsImpl.class);
    }

    @Override
    public AccountAdditionalDetailsDialog accountAdditionalDetails() {
        return creator.getPage(AccountAdditionalDetailsDialogImpl.class);
    }

    @Override
    public AccountDetailsPage accountDetails() {
        return creator.getPage(AccountDetailsTabImpl.class);
    }

    @Override
    public CreditCardDepositPage creditCardDeposit() {
        return creator.getPage(CreditCardDepositPageImpl.class);
    }

    @Override
    public WelcomeDialogImpl welcomeDialog() {
        return creator.getPage(WelcomeDialogImpl.class);
    }

    @Override
    public RiskWarningDialog riskWarningPage() {
        return creator.getPage(RiskWarningDialogImpl.class);
    }

    @Override
    public SetLeverageDialog setLeverageDialog() {
        return creator.getPage(SetLeverageDialogImpl.class);
    }

    @Override
    public StartTradeDialog startTradeDialog() {
        return creator.getPage(StartTradeDialogImpl.class);
    }

    @Override
    public RejectMessageOnLogin rejectMessage() {
        return creator.getPage(RejectMessageOnLoginImpl.class);
    }

    @Override
    public ConfirmCloseMessage confirmCloseMessage() {
        return creator.getPage(ConfirmCloseMessageImpl.class);
    }

    @Override
    public UploadDocumentsDialog uploadDocumentDialog() {
        return creator.getPage(UploadDocumentsDialogImpl.class);
    }

    @Override
    public LeveragePopup leveragePopup() {
        return creator.getPage(LeveragePopupImpl.class);
    }

    @Override
    public InsufficientFundsDialog insufficientFundsDialog() {return  creator.getPage(InsufficientFundsDialogImpl.class);}

    @Override
    public SignatureRiskWarning signatureRiskWarning() {
        return creator.getPage(SignatureRiskWarningImpl.class);
    }

    @Override
    public ConfirmAnswersDialog confirmAnswers() { return creator.getPage(ConfirmAnswersDialogImpl.class); }

    @Override
    public RegistrationDialog registrationDialog() {return creator.getPage(RegistrationDialogImpl.class); }

    @Override
    public RedirectDialog redirectDialog() { return creator.getPage(RedirectDialogImpl.class); }

    @Override
    public LoadingDialog loadingDialog() { return creator.getPage(LoadingDialogImpl.class); }

    @Override
    public ThankYouPage thankYouPage() {
        return creator.getPage(ThankYouPageImpl.class);
    }

    @Override
    public UploadDocumentsTab uploadDocumentsTab() {
        return creator.getPage(UploadDocumentsTabImpl.class);
    }
}

