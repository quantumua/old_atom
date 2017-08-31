package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;

/**
 * Created by vsnigur on 8/31/17.
 */
public class AbstractRestAPICustomerRegistrationTest extends AbstractCustomerRegistrationTest {

    /**
     * Create user via mobile API using parameters to update in DB
     * @param experienceLevel - questionnaires level for the user
     * @param experienceScore - questionnaires score to add for the user into DB
     * @return - created CRMCustomer object
     */
    protected CRMCustomer createUser(OnboardingWizardConditions.ExperienceLevel experienceLevel, ExperienceScore experienceScore) {
        return createUser(CustomerRO.builder(CRMMobileAPINamingStrategy.get()), getConditions(experienceLevel), experienceScore);
    }

    /**
     * Create user via mobile API using parameters to update in DB
     *
     * @param experienceLevel - questionnaires level for the user
     * @param experienceScore - questionnaires score to add for the user into DB
     * @return - created CRMCustomer object
     */
    protected CRMCustomer createUser(String countryCode, OnboardingWizardConditions.ExperienceLevel experienceLevel, ExperienceScore experienceScore) {
        return createUser(CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setCountryCode(countryCode), getConditions(experienceLevel), experienceScore);
    }

}
