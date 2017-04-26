package com.betamedia.qe.af.core.dsl.pages.factory.tp;


import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.Assets;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.Bidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.BinarySelector;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.DisclaimerNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginErrorNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.LandingPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.Positions;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TPPageFactory {
    LoginPage loginPage();

    TopNavigationPage topNavigationPage();

    DisclaimerNotification disclaimerNotification();

    LoginErrorNotification loginErrorNotification();

    Bidder bidder();

    Assets assets();

    BinarySelector binarySelector();

    Positions positions();

    LandingPage landingPage();
}
