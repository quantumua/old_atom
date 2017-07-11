package com.betamedia.atom.testslibrary.option24.web.ctw;

import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;

/**
 * @author leonid.a
 */

public class VerifyPersonalDetailsSlideTest extends WEBEndToEndTest{
	/*
	 *[testlink]  CTW-5624:Verify slide appear after registration and welcome slide
	 * 
	 */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5624")
	  public void  verifySlideAppearAfterRegistrationAndWelcomeSlide(String countrycode) {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register(countrycode);
	        pages().welcomepage().isStartBtnDisplayed();
	        pages().welcomepage().start();
	        pages().accountAdditionalDetailsPage().exists();
    }
    
	/*
	 *[testlink]   CTW-5625:Verify "Submit" bttn is mandatory for moving fwd
	 * 
	 */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5625")
    public void  verifySubmitButtonIsMandatoryForMovingFwd(String countrycode) {
     	pages().topNavigationPage().signUp();
        pages().registrationPage().register(countrycode);
        pages().welcomepage().isStartBtnDisplayed();
        pages().welcomepage().start();
    	Assert.assertFalse(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled());
    }
    
    /*
     *[testlink]  CTW-5630:Verify all 3 fields are mandatory
     * 
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5630")
    public void  verifyAll3FieldsAreMandatory(String countrycode) {
     	pages().topNavigationPage().signUp();
        pages().registrationPage().register(countrycode);
        pages().welcomepage().isStartBtnDisplayed();
        pages().welcomepage().start();
    	pages().accountAdditionalDetailsPage().SelectBirthDateDay(AccountAdditionalDetails.builder().build());
    	Assert.assertFalse(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled());
    	pages().accountAdditionalDetailsPage().SelectNationality(AccountAdditionalDetails.builder().build());
    	Assert.assertFalse(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled());
    	pages().accountAdditionalDetailsPage().SelectBirthDateMonth(AccountAdditionalDetails.builder().build());
    	pages().accountAdditionalDetailsPage().SelectCountryOfBirth(AccountAdditionalDetails.builder().build());
    	Assert.assertFalse(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled());
    	
    }
    /*
     *[testlink]  CTW-5638:Verify submit bttn enables when all 3 fields are filled
     * 
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5638")
    public void  verifySubmitButtonEnableWhenAllFieldsAreFilled(String countrycode) {
     	pages().topNavigationPage().signUp();
        pages().registrationPage().register(countrycode);
        pages().welcomepage().isStartBtnDisplayed();
        pages().welcomepage().start();
    	pages().accountAdditionalDetailsPage().SelectAllData(AccountAdditionalDetails.builder().build());
    	Assert.assertTrue(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled());
    }
    
    /*
     * [testlink] CTW-5647:Verify date of birth drop down functionality
     * 
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5647")
    public void VerifyDateOofBirthDropDownFunctionality(String countrycode){
        pages().topNavigationPage().signUp();
        pages().registrationPage().register(countrycode);
        pages().welcomepage().isStartBtnDisplayed();
        pages().welcomepage().start();
        pages().accountAdditionalDetailsPage().clickDropDownButton();
        Assert.assertEquals(pages().accountAdditionalDetailsPage().getBirthDayDataList().size(),32);
        Assert.assertEquals(pages().accountAdditionalDetailsPage().getBirthDaySelectedItem(), "3");
    }
 
    /*
     * [testlink] CTW-5660:Verify selection changes drop down color
     * 
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5660")
    public void verifySelectionChangesDropDownColor(String countrycode){
        pages().topNavigationPage().signUp();
        pages().registrationPage().register(countrycode);
        pages().welcomepage().isStartBtnDisplayed();
        pages().welcomepage().start();
        String colorOfBorderBeforeClick = pages().accountAdditionalDetailsPage().getColorOfElement();
        pages().accountAdditionalDetailsPage().clickDropDownButton();
        pages().accountAdditionalDetailsPage().selectBirthDayData();
        String colorOfBorderAfterClick = pages().accountAdditionalDetailsPage().getColorOfElement();
        Assert.assertNotEquals(colorOfBorderAfterClick, colorOfBorderBeforeClick);
    }

    /*
     * [testlink] CTW-5663:Verify opening drop downs changes arrow direction
     * 
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5663")
    public void verifyOpeningDropDownsChangesArrowDirection(String countrycode) {
        pages().topNavigationPage().signUp();
        pages().registrationPage().register(countrycode);
        pages().welcomepage().isStartBtnDisplayed();
        pages().welcomepage().start();
        String backgroundButtonBeforeClick = pages().accountAdditionalDetailsPage().getElementsBackground();
        pages().accountAdditionalDetailsPage().clickDropDownButton();
        pages().accountAdditionalDetailsPage().selectBirthDayData();
        String backgroundButtonAfterClick = pages().accountAdditionalDetailsPage().getElementsBackground();
        Assert.assertNotEquals(backgroundButtonAfterClick, backgroundButtonBeforeClick);
    }

    /*
     * [testlink] CTW-5666:Click on submit moves you to FNS first slide
     * 
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5666")
    public void clickOnSubmitMovesYouToFNSFirstSlide(String countrycode) {
        pages().topNavigationPage().signUp();
        pages().registrationPage().register(countrycode);
        pages().welcomepage().isStartBtnDisplayed();
        pages().welcomepage().start();
        pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder().build());
        Assert.assertTrue(pages().fnsPersonalInformation().exists());
    }  
}
