package com.betamedia.atom.testslibrary.option24.web.ctw;


import com.betamedia.atom.core.persistence.entities.ContactBase;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;

/**
 * @author Leonid Artemiev
 * @since 07/12/2017
 */

public class ReflectionInCRMTest extends AbstractOnboardingUserExperienceTest {
	
    /*
     * Reflection in CRM verification
     * [testlink] CTW-5679:Verify the 3 fields update in the CRM
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5679:Verify the 3 fields update in the CRM")
    public void verifyThe3FieldsUpdateInTheCRM(String countrycode) {
    	CRMCustomer crmCustomer = createUser(OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE);
    	pages().welcomeBackMessage().continueQuestionnaire();
    	
    	String customerID = crmCustomer.getId();

		final ContactBase contactBaseBefore = operations().customerOperations().getContactBase(customerID);
		final ContactExtension contactExtensionBefore = operations().customerOperations().getContactExtension(customerID);
    	
    	Assert.assertNotEquals("Account birth date must be different from default",			
    					AccountAdditionalDetails.builder().getDefaultBirthDate(),
				contactBaseBefore.getBirthDate());
    	Assert.assertNotEquals("Account nationality must be different from default",		
    					AccountAdditionalDetails.builder().getDegaultNationality(), 
    					contactExtensionBefore.getNationality());
    	Assert.assertNotEquals("Account country of birth must be different from default",
    					AccountAdditionalDetails.builder().getDegaultCountryOfBirth(),
				contactExtensionBefore.getCountryOfBirth());
    	
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().fnsPersonalInformation().exists();

		final ContactBase contactBaseAfter = operations().customerOperations().getContactBase(customerID);
		final ContactExtension contactExtensionAfter = operations().customerOperations().getContactExtension(customerID);

    	Assert.assertNotEquals(contactBaseBefore.getBirthDate(), contactBaseAfter.getBirthDate());
    	Assert.assertNotEquals(contactExtensionBefore.getNationality(), contactExtensionAfter.getNationality());
    	Assert.assertNotEquals(contactExtensionBefore.getCountryOfBirth(), contactExtensionAfter.getCountryOfBirth());
    }
}
