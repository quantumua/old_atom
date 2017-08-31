package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.TopNavigationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public class TopNavigationPageImpl extends AbstractPageObject implements TopNavigationPage {

    @StoredId
    private By mainMenu;
    @StoredId
    private By loginBtn;
    @StoredId
    private By myAccountBtn;
    @StoredId
    private By cfdBtn;
    @StoredId
    private By tradeBtn;
    @StoredId
    private By signUpBtn;
    @StoredId
    private By homePageLink;
    @StoredId
    private By productButtons;
    @StoredId
    private By languageMenu;
    @StoredId
    private By flagLanguage;
    @StoredId
    private By bankPageLink;
    @StoredId
    private By withdrawalTab;
    @StoredId
    private By depositBtn;


    public TopNavigationPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void signUp() {
        waitUntilDisplayed(signUpBtn).click();
        Reporter.log("Click 'SIGN UP' button.<br/>");
    }
    
    @Override
    public boolean isLoggedIn() {
        return maybe(() -> waitUntilDisplayed(myAccountBtn)).isPresent();
    }

    @Override
    public void goToMyAccount() {
        waitUntilDisplayed(myAccountBtn).click();
    }

    @Override
    public boolean isLoggedOut() {
        return maybe(() -> waitUntilDisplayed(loginBtn)).isPresent();
    }

    @Override
    public void logIn() {
        waitUntilDisplayed(loginBtn).click();
    }

    @Override
    public void deposit(){
        waitUntilDisplayed(depositBtn).click();
    }

    @Override
    public void cfd() {
        waitUntilDisplayed(mainMenu,cfdBtn).click();
    }

    @Override
    public void trade(){
        waitUntilDisplayed(mainMenu,tradeBtn).click();
    }

    @Override
    public void goToHomePage() {
        waitUntilDisplayed(homePageLink).click();
        waitUntilPageLoad();
    }

    @Override
    public void bankingDetails() {
        waitUntilDisplayed(bankPageLink).click();
        waitUntilPageLoad();
    }

    @Override
    public List<String> getProducts() {
        return findElements(productButtons).stream().map(WebElement::getText).collect(Collectors.toList());
    }
    
    @Override
    public boolean languageExists() {
        return waitUntilDisplayed(languageMenu).isDisplayed();
    }
    
    /*temporal8/
     * 
     */
    @Override
    public void withdrawalTab() {
        waitUntilDisplayed(withdrawalTab).click();
        waitUntilPageLoad();
    }

    
    /**
     * Switch whole portal UI to given language
     * @param language - language code like EN for English, DE for German
     */
    @Override    
    public void selectLanguage(String language) {
        waitUntilPageLoad();
        Reporter.log("Waiting for switch UI to language: " + language + "<br/>");
        waitUntilDisplayed(languageMenu).click();
        Reporter.log("Switching UI to language: " + language + "<br/>");

        waitUntilDisplayed(flagLanguage).findElements(flagLanguage).stream()
                        .filter(element -> element.getAttribute("data-icl-code").toLowerCase().contains(language.toLowerCase()))
                        .findFirst().orElse(null)
                        .click();

        waitUntilClickable(languageMenu) ;
    }

    @Override
    public void waitForLoggedOut() {
        waitUntilPageLoad();
        waitUntilDisplayed(signUpBtn);
    }
}