package com.betamedia.atom.testslibrary.option24.web.personalDetailsSlide;


import com.betamedia.atom.core.persistence.entities.ContactBase;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractRestAPICustomerRegistrationTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;

/**
 * @author Leonid Artemiev
 * @since 07/12/2017
 */

public class PersonalDetailsSlideReflectionInCRMTest extends AbstractRestAPICustomerRegistrationTest {
	
    /*
     * Reflection in CRM verification
     * [testlink] CTW-5679:Verify the 3 fields update in the CRM
     */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5679:Verify the 3 fields update in the CRM")
	@TestLinkProperties(displayId ="CTW-5679")
    public void verifyThe3FieldsUpdateInTheCRM(String countrycode) {
    	CRMCustomer crmCustomer = createUser(OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE);
    	pages().welcomeBackMessage().continueQuestionnaire();
    	
    	String customerID = crmCustomer.getId();

		final ContactBase contactBaseBefore = operations().customerOperations().getContactBase(customerID);
		final ContactExtension contactExtensionBefore = operations().customerOperations().getContactExtension(customerID);
    	
    	softAssert().assertNotEquals(
    	        AccountAdditionalDetails.builder().getDefaultBirthDate(),
				contactBaseBefore.getBirthDate(),
                "Account birth date must be different from default");
        softAssert().assertNotEquals(
                AccountAdditionalDetails.builder().getDefaultNationality(),
    			contactExtensionBefore.getNationality(),
                "Account nationality must be different from default");
    	softAssert().assertNotEquals(
    	        AccountAdditionalDetails.builder().getDefaultCountryOfBirth(),
				contactExtensionBefore.getCountryOfBirth(),
                "Account country of birth must be different from default");
    	
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().fnsPersonalInformation().exists();

		final ContactBase contactBaseAfter = operations().customerOperations().getContactBase(customerID);
		final ContactExtension contactExtensionAfter = operations().customerOperations().getContactExtension(customerID);

    	softAssert().assertNotEquals(contactBaseBefore.getBirthDate(), contactBaseAfter.getBirthDate(), "Account birth date must be different after Account Additional Details is set");
        softAssert().assertNotEquals(contactExtensionBefore.getNationality(), contactExtensionAfter.getNationality(), "Account nationality must be different after Account Additional Details is set");
        softAssert().assertNotEquals(contactExtensionBefore.getCountryOfBirth(), contactExtensionAfter.getCountryOfBirth(), "Account country of birth must be different after Account Additional Details is set");
    }
}
