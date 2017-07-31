package com.betamedia.atom.testslibrary.option24.web.ctw;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.tp.TPEndToEndTest;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */

public class DocumentsUploadSlideSanityTest extends TPEndToEndTest{

    /*
     *[TestLink] CTW-5765:Successful upload document: POI section - Passport doc
     */
    @Parameters({"countrycode"})
    @Test(description = "CTW-5765:Successful upload document: POI section - Passport doc")
    public void successfulUploadDocumentPOISectionPassportDoc(@Optional("de") String countrycode){
        pages().topNavigationPage().signUp();
        CustomerRO customer = CustomerRO.builder(WebSiteNamingStrategy.get()).setCountryCode(countrycode).build();
        pages().registrationDialog().register(customer);
        pages().welcomePage().isStartBtnDisplayed();
        pages().welcomePage().start();
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentsPage().goToDocumentsUpload();
        pages().uploadDocumentsPage().selsetPOI();
        pages().uploadDocumentsPage().browseForFileUpload();
    }
}
