package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.Currency;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import org.testng.annotations.Optional;

/**
 * Created by vsnigur on 8/31/17.
 */
public class AbstractWebCustomerRegistrationTest extends AbstractCustomerRegistrationTest {

    /**
     * Create user via WEB UI using specific parameters
     *
     * @param countrycode - country code
     * @param phoneCountryPrefix - phone country prefix to use
     * @param depositAmount - deposit amount to set in credit card deposit slide
     */

    public CustomerRegistrationInfo createUser(String countrycode, String phoneCountryPrefix, @Optional String depositAmount){
        pages().topNavigationPage().signUp();
        CustomerRegistrationInfo customerRegistrationInfo=CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get()).withCountry(countrycode)
                .withPhoneCountryPrefix(phoneCountryPrefix)
                .withCurrency(Currency.USD.getFullName())
                .build();
        pages().registrationDialog().register(customerRegistrationInfo);
        pages().welcomeDialog().isStartBtnDisplayed();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        PersonalInformation personalInfo = getPersonalInformation();
        passQuestionnaire(personalInfo, getTradingExperienceInfo());
        pages().creditCardDeposit().submit((CreditCardDeposit.builder()
                .withDepositAmount(depositAmount)
                .build()));
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().submit(personalInfo);
        return customerRegistrationInfo;
    }

    /**
     * Create user via WEB UI
     * pass all questionnaires using provided information
     * @param personalInformation - personal information for answers
     * @param tradingExperienceInfo - trading questionnaires for answers
     * @return - created customer object
     */
    protected CustomerRegistrationInfo createUser(
            PersonalInformation personalInformation, TradingExperienceInfo tradingExperienceInfo, boolean riskWarning) {
        CustomerRegistrationInfo customerRegistrationInfo = getCustomerRegistrationInfo();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        passQuestionnaire(personalInformation,tradingExperienceInfo);
        if (riskWarning) pages().riskWarning().accept();
        pages().creditCardDeposit().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        return customerRegistrationInfo;
    }



}
