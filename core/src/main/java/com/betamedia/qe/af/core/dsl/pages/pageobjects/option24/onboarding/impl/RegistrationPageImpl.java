package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.RegistrationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Lilian Medina
 *         Date:5/15/17.
 */
public class RegistrationPageImpl extends AbstractPageObject implements RegistrationPage {

   
    @StoredId
    private By registrationWidget;
    @StoredId
    private By firstnameField;
    @StoredId
    private By lastnameField;
    @StoredId
    private By emailField;

    public RegistrationPageImpl(WebDriver webDriver) {
    	 super(webDriver);
    }

    @Override
    public void firstName() {
    	 waitUntilDisplayed(registrationWidget); 
    	 find(registrationWidget, firstnameField).sendKeys("test");
    	 
    }

    @Override
    public void lastName() {
    	 waitUntilDisplayed(registrationWidget); 
    	 find(registrationWidget, lastnameField).sendKeys("test");
        
    }

    @Override
    public void email() {
    	 waitUntilDisplayed(registrationWidget); 
    	 find(registrationWidget, emailField).sendKeys("Hol6@a.com");
    }

    @Override
    public void phoneNumber() {
      
    }

    @Override
    public void password() {
       
    }
    
    @Override
    public void reenterPassword() {
       
    }
    
    @Override
    public void checkboxAccountAgree() {
      
    }

	@Override
	public void country() {
		// TODO Auto-generated method stub
		
	}

}
