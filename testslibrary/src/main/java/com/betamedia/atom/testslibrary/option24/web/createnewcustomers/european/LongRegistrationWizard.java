package com.betamedia.atom.testslibrary.option24.web.createnewcustomers.european;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 7/17/17.
 */
public class LongRegistrationWizard extends AbstractOnboardingUserExperienceTest {

    /**
     * open register form
     * select European country;
     * register user;
     * make sure user appears in the CRM DataBase;
     */
    @Test(description = "CTW-5416:Verify full registration on SEU")
    public void verifySignUpButtonRedirectToOnBoardingOpenAccount() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withPhoneCountryPrefix(Country.UNITED_KINGDOM.getPhonePrefix())
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        pages().welcomePage().isStartBtnDisplayed();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customerRegistrationInfo.getEmail());
    }
}
