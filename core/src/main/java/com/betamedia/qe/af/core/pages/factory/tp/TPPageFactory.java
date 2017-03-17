package com.betamedia.qe.af.core.pages.factory.tp;


import com.betamedia.qe.af.core.pages.tp.login.DisclaimerNotification;
import com.betamedia.qe.af.core.pages.tp.login.LoginErrorNotification;
import com.betamedia.qe.af.core.pages.tp.login.LoginPage;
import com.betamedia.qe.af.core.pages.tp.navigation.TopNavigationPage;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TPPageFactory {
    LoginPage loginPage();

    TopNavigationPage topNavigationPage();

    DisclaimerNotification disclaimerNotification();

    LoginErrorNotification loginErrorNotification();
}
