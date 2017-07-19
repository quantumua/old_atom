package com.betamedia.atom.testslibrary.option24.web.createnewcustomers.international;

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
     * select country from international group;
     * redirect to the international site;
     * register user;
     * make sure user appears in the CRM DataBase;
     */
    @Test(description = "CTW-5764:Verify full registration on SINT")
    public void verifySignUpButtonRedirectToOnBoardingOpenAccount() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withPhoneCountryPrefix(Country.ZAMBIA.getPhonePrefix())
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(Country.ZAMBIA.getName());
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        pages().creditCardDeposit().waitforCreditCardDepositPage();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customerRegistrationInfo.getEmail());
    }
}
