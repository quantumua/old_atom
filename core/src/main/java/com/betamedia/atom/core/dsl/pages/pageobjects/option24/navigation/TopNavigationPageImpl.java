package com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public class TopNavigationPageImpl extends AbstractPageObject implements TopNavigationPage {

    @StoredId
    private By mainMenu;
    @StoredId("loginBtn")
    private By loginBtn;
    @StoredId("myAccountBtn")
    private By myAccountBtn;
    @StoredId
    private By binaryBtn;
    @StoredId
    private By cfdBtn;
    @StoredId
    private By signUpBtn;
    @StoredId
    private By homePageLink;
    @StoredId
    private By productButtons;

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
    public void binary() {
        waitUntilDisplayed(mainMenu);
        find(mainMenu, binaryBtn).click();
        waitUntilDisplayed(mainMenu);
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
}
