package com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public interface TopNavigationPage {
    void logIn();

    void binary();

    void cfd();

    boolean isLoggedIn();

    boolean isLoggedOut();
    
    void signUp();

    void goToHomePage();
    
    boolean isSubmitBtn();
}