package com.betamedia.atom.testslibrary.option24.web.experience;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 8/30/17.
 */
public class ExperienceLevelsTests extends AbstractExperienceLevelsTests {


    private static final boolean RISK_WARNING = true;
    private static final boolean NO_RISK_WARNING = false;

    @Test(description = "CTW-5508:Experience level - Rejected")
    @TestLinkProperties(displayId = "CTW-5508")
    public void ExperienceLevelRejected() {
        operations().onBoardingOperations().assertUsernameRejected(
                makeCustomer(getPersonalInformationScore0(),getRejectedTradingExperienceInfo(), RISK_WARNING).getEmail());
    }

    @Test(description = "CTW-5514:Experience level - No Experience")
    @TestLinkProperties(displayId = "CTW-5514")
    public void ExperienceLevelNoExperience() {
        operations().onBoardingOperations().assertUsernameNoExperience(
                makeCustomer(getPersonalInformationScore0(),getNoTradingExperienceInfo(), RISK_WARNING).getEmail());
    }

    @Test(description = "CTW-5510:Experience level - Low Experience")
    @TestLinkProperties(displayId = "CTW-5510")
    public void ExperienceLevelLowExperience() {
        operations().onBoardingOperations().assertUsernameLowExperience(
                makeCustomer(getPersonalInformationScore0(),getLowTradingExperienceInfo(),NO_RISK_WARNING).getEmail());
    }

    @Test(description = "CTW-5511:Experience level - High Experience")
    @TestLinkProperties(displayId = "CTW-5511")
    public void ExperienceLevelHighExperience() {
        operations().onBoardingOperations().assertUsernameHighExperience(
                makeCustomer(getPersonalInformationScoreMaximum(),getHighTradingExperienceInfo(),NO_RISK_WARNING).getEmail());
    }

    @Test(description = "CTW-5515:Experience level - Expert")
    @TestLinkProperties(displayId = "CTW-5515")
    public void ExperienceLevelExpertExperience() {
        operations().onBoardingOperations().assertUsernameExpert(
                makeCustomer(getPersonalInformationScoreMaximum(),getExpertTradingExperienceInfo(),NO_RISK_WARNING).getEmail());
    }

    /**
     * Method makes customer via web
     * pass all questionnaires using provided information
     * @param personalInformation - personal information for answers
     * @param tradingExperienceInfo - trading experience for answers
     * @return - created customer object
     */
    protected CustomerRegistrationInfo makeCustomer(
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

    /**
     * make customer through the web
     * @return customer info object used for customer creation
     */
    protected CustomerRegistrationInfo getCustomerRegistrationInfo() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRegistrationInfo);
        return customerRegistrationInfo;
    }

}
