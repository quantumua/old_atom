package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.RegistrationPage;

import java.util.concurrent.TimeUnit;

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
    @StoredId("submitButtonReg")
    private By submitButtonReg;
    @StoredId("accountAgreeField")
    private By accountAgreeField;

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
   	        	  
   	     
   	    /*Account Agree */
  	     find(accountAgreeField).click();
   	    
   	  System.out.println("EMAIL  " + dataCustomer.getEmail());
 	  System.out.println("Phone  " + dataCustomer.getPhone());
 	  System.out.println("passwod  " + dataCustomer.getPassword());
 	  
 	 
      	    
		
	}


	@Override
	public void SubmitRegistration() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitUntilDisplayed(submitButtonReg); 	
	    find(registrationWidget, submitButtonReg).click();
	try {
		Thread.sleep(100000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

	
//	@Override
//	public void waitforNext() {
//	find(registrationWidget, submitButtonReg).click();
//	}

//	@Override
//	public void SubmitRegistration() {
//		find(submitButton).click();
//		
//	}

}
