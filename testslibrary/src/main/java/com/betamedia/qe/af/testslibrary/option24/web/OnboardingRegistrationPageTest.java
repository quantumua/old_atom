package com.betamedia.qe.af.testslibrary.option24.web;


import com.betamedia.qe.af.core.testingtype.web.WEBEndToEndTest;

import org.testng.annotations.Test;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */

public class OnboardingRegistrationPageTest extends WEBEndToEndTest {

    @Test
    public void registrationTest() {
     	pages().topNavigationPage().signUp();
        pages().registrationPage().register();
    }
}
        


