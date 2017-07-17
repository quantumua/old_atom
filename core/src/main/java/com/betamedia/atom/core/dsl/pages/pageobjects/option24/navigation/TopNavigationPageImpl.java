package com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
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
    private By signUpBtn;
    @StoredId
    private By homePageLink;
    @StoredId
    private By productButtons;
    @StoredId
    private By languageMenu;
    @StoredId
    private By flagLanguage;
    

    public TopNavigationPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void signUp() {
    	waitUntilDisplayed(signUpBtn).click();
    }
    
    @Override
    public boolean isLoggedIn() {
        return waitUntilDisplayed(myAccountBtn) != null;
    }

    @Override
    public boolean isLoggedOut() {
        return waitUntilDisplayed(loginBtn) != null;
    }

    @Override
    public void logIn() {
        waitUntilDisplayed(loginBtn).click();
    }

    @Override
    public void cfd() {
        waitUntilDisplayed(mainMenu,cfdBtn).click();
    }

    @Override
    public void goToHomePage() {
        waitUntilDisplayed(homePageLink).click();
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
    
    /**
     * Switch whole portal UI to given language
     * @param language - language code like EN for English, DE for German
     */
    @Override    
    public void selectLanguage(String language) {
    	Reporter.log("Switching to language: " + language + "<br/>");
        waitUntilDisplayed(languageMenu).click();
        findElements(flagLanguage).stream()
                        .filter(element -> element.getAttribute("data-icl-code").toLowerCase().contains(language.toLowerCase()))
                        .findFirst().orElse(null)
                        .click();
    }
}
