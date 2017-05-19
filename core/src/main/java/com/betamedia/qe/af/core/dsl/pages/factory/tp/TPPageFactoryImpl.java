package com.betamedia.qe.af.core.dsl.pages.factory.tp;

import com.betamedia.qe.af.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.Assets;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.impl.AssetsImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.BinaryBidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.CfdBidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.impl.BinaryBidderImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.impl.CfdBidderImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.BinarySelector;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.impl.BinarySelectorImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.controlpanel.ControlPanel;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.controlpanel.impl.ControlPanelImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.*;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl.*;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.DisclaimerNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginErrorNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl.DisclaimerNotificationImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl.LoginErrorNotificationImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl.LoginPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.DialogBox;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.MessageBox;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.impl.DialogBoxImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.impl.MessageBoxImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.LandingPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.LandingPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.BinaryPositions;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.CfdPositions;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.impl.BinaryPositionsImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.impl.CfdPositionsImpl;
import com.betamedia.qe.af.core.dsl.pages.type.ProductType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Lazy
@Component
@Scope(SCOPE_PROTOTYPE)
public class TPPageFactoryImpl extends AbstractPageFactory implements TPPageFactory {
    @Override
    public LoginPage loginPage() {
        return creator.getPage(LoginPageImpl.class);
    }

    @Override
    public DialogBox dialogBox() {
        return creator.getPage(DialogBoxImpl.class);
    }

    @Override
    public TopNavigationPage topNavigationPage() {
        return creator.getPage(TopNavigationPageImpl.class);
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
    public BinaryBidder binaryBidder() {
        return creator.getPage(BinaryBidderImpl.class);
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
    public BinarySelector binarySelector() {
        return creator.getPage(BinarySelectorImpl.class);
    }

    @Override
    public BinaryPositions binaryPositions() {
        return creator.getPage(BinaryPositionsImpl.class);
    }

    @Override
    public LandingPage landingPage() {
        return creator.getPage(LandingPageImpl.class);
    }

    @Override
    public ProductType getType() {
        return ProductType.TP;
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
    public CrmNavigation crmNavigation() {
        return creator.getPage(CrmNavigationImpl.class);
    }

    @Override
    public FnsTradingExperience fnsTradingExperience() {
        return creator.getPage(FnsTradingExperienceImpl.class);
    }

    @Override
    public FnsPersonalInformation fnsPersonalInformation() {
        return creator.getPage(FnsPersonalInformationImpl.class);
    }

    @Override
    public CrmLoginPage crmLoginPage() {
        return creator.getPage(CrmLoginPageImpl.class);
    }

    @Override
    public AccountDetails accountDetails() {
        return creator.getPage(AccountDetailsImpl.class);
    }

    @Override
    public AccountAdditionalDetails accountAdditionalDetails() {
        return creator.getPage(AccountAdditionalDetailsImpl.class);
    }

    @Override
    public CreditCardDeposit creditCardDeposit() {
        return creator.getPage(CreditCardDepositImpl.class);
    }

    @Override
    public Register register() {
        return creator.getPage(RegisterImpl.class);
    }

}
