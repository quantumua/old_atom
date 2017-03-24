package com.betamedia.qe.af.tests.tp;


import com.betamedia.qe.af.core.tests.tp.TPWebDriverTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */

public class LoginPageTest extends TPWebDriverTest {

    @Test
    public void loginTest() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login("vasichka", "123123");
        pages().disclaimerNotification().accept();
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
    }

    @Test
    public void failedLoginTest() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login("randomname", "randompassword");
        Assert.assertTrue(pages().loginErrorNotification().isDisplayed());
    }

    @Parameters({"username", "password"})
    @Test
    public void parametrizedLoginTest(String username, String password){
        pages().topNavigationPage().logIn();
        pages().loginPage().login(username, password);
        Assert.assertTrue(pages().loginErrorNotification().isDisplayed());
    }
}
