package com.betamedia.qe.af.core.dsl.pages.factory.tp;


import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.Assets;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.BinaryBidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.CfdBidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.BinarySelector;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.controlpanel.ControlPanel;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.CrmLoginPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.CrmNavigation;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsPersonalInformation;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsTradingExperience;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.DisclaimerNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginErrorNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.DialogBox;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.MessageBox;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.LandingPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.BinaryPositions;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.CfdPositions;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TPPageFactory {
    LoginPage loginPage();

    TopNavigationPage topNavigationPage();

    DisclaimerNotification disclaimerNotification();

    LoginErrorNotification loginErrorNotification();

    BinaryBidder binaryBidder();

    CfdBidder cfdBidder();

    Assets assets();

    BinarySelector binarySelector();

    BinaryPositions binaryPositions();

    LandingPage landingPage();

    ControlPanel controlPanel();

    MessageBox messageBox();

    CfdPositions cfdPositions();

    DialogBox dialogBox();

    CrmNavigation crmNavigation();

    FnsTradingExperience fnsTradingExperience();

    FnsPersonalInformation fnsPersonalInformation();

    CrmLoginPage crmLoginPage();
}
