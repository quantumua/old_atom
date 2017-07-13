package com.betamedia.atom.testslibrary.option24.web.ctw;


import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;


public class ReflectionInCRMTest extends AbstractOnboardingUserExperienceTest{
	
    /*
     * Reflection in CRM
     * [testlink] CTW-5679:Verify the 3 fields update in the CRM
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5679:Verify the 3 fields update in the CRM")
    public void verifyThe3FieldsUpdateInTheCRM(String countrycode) {
    	CRMCustomer crmCustomer = createUser(OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE);
    	pages().welcomeBackMessage().continueQuestionnaire();
    	
    	String customerID = crmCustomer.getId();
    	
    	String birthDateBefore = operations().customerOperations().findBirthDate(customerID);
    	Integer nationalityBefore = operations().customerOperations().findNationality(customerID);
    	Integer countryOfBirthBefore = operations().customerOperations().findCountryOfBirth(customerID);
    	
    	Assert.assertNotEquals("Account birth date must be different from default",			
    					AccountAdditionalDetails.builder().getDefaultBirthDate(), 
    					birthDateBefore);
    	Assert.assertNotEquals("Account nationality must be different from default",		
    					AccountAdditionalDetails.builder().getDegaultNationality(), 
    					nationalityBefore);    	
    	Assert.assertNotEquals("Account country of birth must be different from default",
    					AccountAdditionalDetails.builder().getDegaultCountryOfBirth(), 
    					countryOfBirthBefore);
    	
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().fnsPersonalInformation().exists();
        
    	String birthDateAfter = operations().customerOperations().findBirthDate(customerID);
    	Integer nationalityAfter = operations().customerOperations().findNationality(customerID);
    	Integer countryOfBirthAfter = operations().customerOperations().findCountryOfBirth(customerID);
    	
    	Assert.assertNotEquals(birthDateBefore, birthDateAfter);
    	Assert.assertNotEquals(nationalityBefore, nationalityAfter);
    	Assert.assertNotEquals(countryOfBirthBefore, countryOfBirthAfter);
    }
}
