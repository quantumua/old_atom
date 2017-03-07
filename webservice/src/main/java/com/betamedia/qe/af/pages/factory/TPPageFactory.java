package com.betamedia.qe.af.pages.factory;


import com.betamedia.qe.af.pages.tp.login.DisclaimerNotification;
import com.betamedia.qe.af.pages.tp.login.LoginErrorNotification;
import com.betamedia.qe.af.pages.tp.login.LoginPage;
import com.betamedia.qe.af.pages.tp.navigation.TopNavigationPage;

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
