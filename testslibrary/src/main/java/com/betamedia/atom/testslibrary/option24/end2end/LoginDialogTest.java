package com.betamedia.atom.testslibrary.option24.end2end;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public class LoginDialogTest extends WebEndToEndTest {

    @Test
    public void loginTest() {
        pages().topNavigationPage().logIn();
        pages().loginDialog().login("vasichka", "123123");
        pages().disclaimerNotification().tryAccept();
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
    }

    @Test
    public void failedLoginTest() {
        pages().topNavigationPage().logIn();
        pages().loginDialog().login("randomname", "randompassword");
        Assert.assertTrue(pages().loginErrorNotification().isDisplayed());
    }

    @Parameters({"username", "password"})
    @Test
    public void parametrizedLoginTest(String username, String password){
        pages().topNavigationPage().logIn();
        pages().loginDialog().login(username, password);
        Assert.assertTrue(pages().loginErrorNotification().isDisplayed());
    }

    @Test(description = "Simple login test and logout for default rest user.")
    public void logoutTest() {
        pages().topNavigationPage().logIn();
        pages().loginDialog().login("vasichka", "123123");
        pages().disclaimerNotification().tryAccept();
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().controlPanel().logOut();
        Assert.assertTrue(pages().landingPage().isOnPage());
    }

    /**
     * Register the new customer with mobile CRM api and then login with newly created customer's login/password
     */
    @Test
    public void registerAndLoginTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().topNavigationPage().logIn();
        pages().loginDialog().login(customer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        Assert.assertTrue(pages().welcomeBackMessage().exists());
    }

    /**
     * login, clean cookies, refresh page and login again
     */
    @Test
    public void loginCleanCookiesLoginAgainTest() {
        pages().topNavigationPage().logIn();
        pages().loginDialog().login("vasichka", "123123");
        pages().disclaimerNotification().tryAccept();
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        pages().topNavigationPage().logIn();
        pages().loginDialog().login("vasichka", "123123");
        pages().disclaimerNotification().tryAccept();
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
    }

}
