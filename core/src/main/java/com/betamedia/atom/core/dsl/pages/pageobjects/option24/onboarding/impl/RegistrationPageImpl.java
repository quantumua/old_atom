package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.RegistrationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Lilian Medina
 *         Date:5/15/17.
 */
public class RegistrationPageImpl extends AbstractPageObject implements RegistrationPage {

    private static final Logger logger = LogManager.getLogger(RegistrationPageImpl.class);
    public static String DEFAULT_CUSTOMER_NAME = "Test";

    @StoredId
    private By registrationWidget;
    @StoredId
    private By firstNameField;
    @StoredId
    private By lastNameField;
    @StoredId
    private By emailField;
    @StoredId
    private By phoneNumberField;
    @StoredId
    private By selectCountry;
    @StoredId
    private By passwordRegField;
    @StoredId
    private By reenterPasswordRegField;
    @StoredId
    private By submitButtonReg;
    @StoredId
    private By accountAgreeField;
    @StoredId
    private By selectedFlagField;
    @StoredId
    private By accountAgreeCheckbox;


    public RegistrationPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
//    TODO Please see RegisterPageImpl and change in similar manner
    public void register(CustomerRO customer) {

		/* firstName */
        waitUntilDisplayed(registrationWidget);
        find(registrationWidget, firstNameField).sendKeys(customer.getFirstName());
        // TODO Auto-generated method stub

   	    /* lastName */

        find(registrationWidget, lastNameField).sendKeys(customer.getLastName());

	    /* Email */

        find(registrationWidget, emailField).sendKeys(customer.getEmail());

   	    /* Phone Number */
        find(registrationWidget, phoneNumberField).sendKeys(customer.getPhone());

   	    /* Password */
        find(registrationWidget, passwordRegField).click();
        find(registrationWidget, passwordRegField).sendKeys(customer.getPassword());

   	    /* Re- enter password */
        waitUntilDisplayed(reenterPasswordRegField);
        find(registrationWidget, reenterPasswordRegField).sendKeys(customer.getPassword());


   	    /*Account Agree */
   	    executeScript("arguments[0].click()", find(accountAgreeCheckbox));

        /*Country Election*/
        find(selectedFlagField).click();
        find(selectCountry).click();
        /* */

        logger.info("EMAIL  " + customer.getEmail());
        logger.info("Phone  " + customer.getPhone());
        logger.info("passwod  " + customer.getPassword());

        submitRegistration();

    }

    @Override
    public void register() {
        register(CustomerRO.builder(WebSiteNamingStrategy.get()).build());
    }

    private void submitRegistration() {
    	
    	try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        waitUntilDisplayed(submitButtonReg);
        find(registrationWidget, submitButtonReg).click();
       

    }


}
