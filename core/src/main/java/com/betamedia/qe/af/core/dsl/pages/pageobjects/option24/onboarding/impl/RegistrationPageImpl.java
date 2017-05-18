package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
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

	CustomerRO dataCustomer = CustomerDataSet.dataCustomerReg();
	
    @StoredId
    private By registrationWidget;
    @StoredId
    private By firstnameField;
    @StoredId
    private By lastnameField;
    @StoredId
    private By emailField;
    @StoredId
    private By phoneNumberField;
    @StoredId
    private By selecCountry;
    @StoredId
    private By passwordRegField;
    @StoredId
    private By reenterPasswordRegField;
    

    public RegistrationPageImpl(WebDriver webDriver) {
    	 super(webDriver);
    }


	@Override
	public void fillLongRegistration() {
		
		/* firstName */
		waitUntilDisplayed(registrationWidget); 
   	    find(registrationWidget, firstnameField).sendKeys(dataCustomer.getFirstName());
		// TODO Auto-generated method stub
    
   	    /* lastName */
   	    
	    find(registrationWidget, lastnameField).sendKeys(dataCustomer.getLastName());
	    
	    /* Email */
	    
   	    find(registrationWidget, emailField).sendKeys(dataCustomer.getEmail());
   	    
   	    /* Phone Number */
   	    find(registrationWidget, phoneNumberField).sendKeys(dataCustomer.getPhone());
   	    
   	    /* Password */
   	    find(registrationWidget, passwordRegField).click();
   	    find(registrationWidget, passwordRegField).sendKeys(dataCustomer.getPassword());
   	    
   	    /* Re- enter password */
    	 waitUntilDisplayed(reenterPasswordRegField); 
   	     find(registrationWidget, reenterPasswordRegField).sendKeys(dataCustomer.getPassword());
   	     
   	    
   	  System.out.println("EMAIL  " + dataCustomer.getEmail());
 	  System.out.println("Phone  " + dataCustomer.getPhone());
 	  System.out.println("passwod  " + dataCustomer.getPassword());
   
   	    
		
	}

}
