package com.betamedia.qe.af.core.dsl.pages.factory.tp;


import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.bidder.Bidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.login.DisclaimerNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.login.LoginErrorNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.login.LoginPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.navigation.TopNavigationPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.orders.Positions;

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

    Positions positions();
}
