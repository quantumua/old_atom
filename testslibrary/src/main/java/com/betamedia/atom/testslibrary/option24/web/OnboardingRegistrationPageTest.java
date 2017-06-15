package com.betamedia.atom.testslibrary.option24.web;


import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;
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
        


