package com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gigaspaces.internal.query.predicate.SpacePredicates.in;

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
//FIXME no corresponding entry in repository; is there really a submit button in nav header?
//    @StoredId("submitButton")
//    private By submitButton;
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
    	waitUntilDisplayed(signUpBtn);
         find(mainMenu, signUpBtn).click();
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
        waitUntilExists(loginBtn).click();
    }

    @Override
    public void binary() {
        waitUntilDisplayed(mainMenu);
        find(mainMenu, binaryBtn).click();
        waitUntilDisplayed(mainMenu);
    }

    @Override
    public void cfd() {
        waitUntilDisplayed(mainMenu);
        find(mainMenu, cfdBtn).click();
    }

    @Override
    public void goToHomePage() {
        waitUntilDisplayed(homePageLink);
        find(homePageLink).click();
        waitUntilPageLoad();
    }

	@Override
	public boolean isSubmitBtn() {
//FIXME see submitButton above; consider different name for method
//		return waitUntilDisplayed(submitButton) != null;
        return false;
	}

    @Override
    public List<String> getProducts() {
        return findElements(productButtons).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
