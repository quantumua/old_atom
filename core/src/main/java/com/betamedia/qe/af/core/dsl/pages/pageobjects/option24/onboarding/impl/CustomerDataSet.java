package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl;


import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;

public class CustomerDataSet {
	
	public static final int CHARS_IN_ID = 6;
    public static final int CHARS_IN_PHONE_NUMBER = 11;
	private static final String DEFAULT_FIRST_NAME	= "Test";
	private static final String DEFAULT_LAST_NAME	= "Test";
	private static final String DEFAULT_CURENCY		= "EUR";
	private static final String DEFAULT_COUNTRY_CODE= "JM";
	private static final String DEFAULT_PASSWORD	= "123123";
	
	
	public static CustomerRO dataCustomerReg() {
		CustomerBuilder customerLongReg = new CustomerBuilder();
		customerLongReg.setFirstName(DEFAULT_FIRST_NAME);
		customerLongReg.setLastName(DEFAULT_LAST_NAME);
		customerLongReg.setCurrency(DEFAULT_CURENCY);
		customerLongReg.setPassword(DEFAULT_PASSWORD);
		customerLongReg.setCountryCode(DEFAULT_COUNTRY_CODE);
		
		
		
		
		
		return customerLongReg.createCustomerRO();
		
			
       }
	

}
