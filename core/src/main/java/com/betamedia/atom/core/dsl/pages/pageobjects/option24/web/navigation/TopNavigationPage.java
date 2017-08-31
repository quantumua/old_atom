package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public interface TopNavigationPage {
    void logIn();

    void deposit();

    void cfd();

    boolean isLoggedIn();

    boolean isLoggedOut();
    
    void signUp();

    void trade();

    void goToHomePage();
    
    List<String> getProducts();

    boolean languageExists();

    void selectLanguage(String language);

    void goToMyAccount();

    void bankingDetails();

    void withdrawalTab();

    void waitForLoggedOut();
}
