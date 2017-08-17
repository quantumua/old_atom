package com.betamedia.atom.testslibrary.option24.web.personalDetailsSlide;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;

/**
 * @author Leonid Artemiev
 */

public class PersonalDetailsSlideFunctionalityTest extends WebEndToEndTest {

    @BeforeMethod
    @Parameters({"countrycode", "phonecountryprefix"})
    public void before(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        registedAndStart(countrycode, phonecountryprefix);
    }
	/*
	 *[testlink]  CTW-5624:Verify slide appear after registration and welcome slide
	 */
    @Parameters({"countrycode"})
    @Test(description = "CTW-5624:Verify slide appear after registration and welcome slide")
    @TestLinkProperties(displayId ="CTW-5624")
	public void  verifySlideAppearAfterRegistrationAndWelcomeSlide() {
        Assert.assertTrue("Additional details page is available right after Welcome page", pages().accountAdditionalDetailsPage().exists());
    }
    
	/*
	 *[testlink]   CTW-5625:Verify "Submit" bttn is mandatory for moving fwd
	 */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5625:Verify Submit bttn is mandatory for moving fwd")
    @TestLinkProperties(displayId ="CTW-5625")
    public void  verifySubmitButtonIsMandatoryForMovingFwd() {
    	Assert.assertFalse(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled());
    }
    
    /*
     *[testlink]  CTW-5630:Verify all 3 fields are mandatory
     * 
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5630:Verify all 3 fields are mandatory")
    @TestLinkProperties(displayId ="CTW-5630")
    public void  verifyAll3FieldsAreMandatory() {
        AccountAdditionalDetails accountAdditionalDetails = AccountAdditionalDetails.builder().build();
        pages().accountAdditionalDetailsPage().selectBirthDateDay(accountAdditionalDetails.birthDateDay);
    	softAssert().assertFalse(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled(), "Verification that submit button is disabled when birthDateDay set only");
    	pages().accountAdditionalDetailsPage().selectNationality(accountAdditionalDetails.nationality);
    	softAssert().assertFalse(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled(), "Verification that submit button is disabled when birthDateDay and nationality are set");
    	pages().accountAdditionalDetailsPage().selectBirthDateMonth(accountAdditionalDetails.birthDateMonth);
    	pages().accountAdditionalDetailsPage().selectCountryOfBirth(accountAdditionalDetails.countryOfBirth);
    	softAssert().assertFalse(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled(), "Verification that submit button is disabled when birthDateDay, nationality, birthDateMonth, countryOfBirth are set");
    	
    }
    /*
     *[testlink]  CTW-5638:Verify submit bttn enables when all 3 fields are filled
     * 
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5638:Verify submit bttn enables when all 3 fields are filled")
    @TestLinkProperties(displayId ="CTW-5638")
    public void  verifySubmitButtonEnableWhenAllFieldsAreFilled() {
    	pages().accountAdditionalDetailsPage().selectAllFormElements(AccountAdditionalDetails.builder().build());
    	softAssert().assertTrue(pages().accountAdditionalDetailsPage().isUpdateBtnEnabled(), "Verification that submit button is enabled");
    }
    
    /*
     * [testlink] CTW-5647:Verify date of birth drop down functionality
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5647:Verify date of birth drop down functionality")
    @TestLinkProperties(displayId ="CTW-5647")
    public void VerifyDateOfBirthDropDownFunctionality(){
        pages().accountAdditionalDetailsPage().exists();
        softAssert().assertEquals(pages().accountAdditionalDetailsPage().getBirthDayDataList().size(),32, "Verify BirthDayDataList size is 32");
        pages().accountAdditionalDetailsPage().selectBirthDayData();
        softAssert().assertEquals(pages().accountAdditionalDetailsPage().getBirthDaySelectedItem(), "3", "Verify BirthDayList size is 3");
    }
 
    /*
     * [testlink] CTW-5660:Verify selection changes drop down color
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5660:Verify selection changes drop down color")
    @TestLinkProperties(displayId ="CTW-5660")
    public void verifySelectionChangesDropDownColor(){
        String colorOfBorderBeforeClick = pages().accountAdditionalDetailsPage().getBirthDateDayElementColor();
        pages().accountAdditionalDetailsPage().selectBirthDayData();
        String colorOfBorderAfterClick = pages().accountAdditionalDetailsPage().getBirthDateDayElementColor();
        softAssert().assertNotEquals(colorOfBorderAfterClick, colorOfBorderBeforeClick);
    }

    /*
     * [testlink] CTW-5663:Verify opening drop downs changes arrow direction
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5663:Verify opening drop downs changes arrow direction")
    @TestLinkProperties(displayId ="CTW-5663")
    public void verifyOpeningDropDownsChangesArrowDirection() {
        String backgroundButtonBeforeClick = pages().accountAdditionalDetailsPage().getElementsBackground();
        pages().accountAdditionalDetailsPage().selectBirthDayData();
        String backgroundButtonAfterClick = pages().accountAdditionalDetailsPage().getElementsBackground();
        softAssert().assertNotEquals(backgroundButtonAfterClick, backgroundButtonBeforeClick, "Verification that arrow is changed opposit when selecting any option from the drop down");
    }

    /*
     * [testlink] CTW-5666:Click on submit moves you to FNS first slide
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5666:Click on submit moves you to FNS first slide")
    @TestLinkProperties(displayId ="CTW-5666")
    public void clickOnSubmitMovesYouToFNSFirstSlide() {
        pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder().build());
        Assert.assertTrue(pages().fnsPersonalInformation().exists());
    }

    public void registedAndStart(String countrycode, String phonecountryprefix) {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get()).withCountry(countrycode)
                .withPhoneCountryPrefix(phonecountryprefix)
                .build());
        pages().welcomeDialog().isStartBtnDisplayed();
        pages().welcomeDialog().start();
    }
}
