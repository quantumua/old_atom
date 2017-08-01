package com.betamedia.atom.testslibrary.option24.web;


import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import org.testng.annotations.Test;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */

public class OnboardingRegistrationPageTest extends WebEndToEndTest {
	

    @Test
    public void registrationTest() {
    	
    	pages().topNavigationPage().signUp();
    	pages().registrationDialog().register(Country.SPAIN.getName());
        
        
    }
}
        


