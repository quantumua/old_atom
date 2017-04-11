package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public interface TopNavigationPage {
    void logIn();

    void logOut();

    void binary();

    boolean isLoggedIn();

    boolean isLoggedOut();
}
