package com.betamedia.atom.testslibrary.option24.web.ctw;

import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */

public class DocumentsUploadSlideSanityTest extends WebEndToEndTest {

	/*
	 *[TestLink] CTW-5765:Successful upload document: POI section - Passport doc
	 */
	@Parameters({"countrycode"})
	@Test(description = "CTW-5765:Successful upload document: POI section - Passport doc")
	public void successfulUploadDocumentPOISectionPassportDoc(@Optional("de") String countrycode){
    	pages().topNavigationPage().signUp();
        CustomerRO customer = CustomerRO.builder(WebSiteNamingStrategy.get()).setCountryCode(countrycode).build();
        pages().registrationDialog().register(customer);
        pages().welcomeDialog().isStartBtnDisplayed();
        pages().welcomeDialog().start();
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentDialog().goToDocumentsUpload();
        pages().uploadDocumentDialog().selsetPOI();
        pages().uploadDocumentDialog().browseForFileUpload();
	}
}
