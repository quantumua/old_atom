package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.qe.af.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMError;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.entities.ContactExtension;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfo;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfoExtension;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 */
public interface CustomerOperations <T extends EnvironmentDependent> extends EnvironmentDependent{

    CRMCustomer register();

    CRMCustomer register(CustomerRO customerRO);

    CRMCustomer register(CustomerRO customerRO, MarketingParametersRO marketingParametersRO);

    List<CRMError>  registerWithErrors(CustomerRO customerRO);

    CRMCustomer login(String username, String password);

    void logout(String customerId);

    CRMDeposit deposit(MobileDepositRO deposit);

    CRMDeposit deposit(MobileDepositRO deposit, MarketingParametersRO marketingParametersRO);

    List<CRMError> depositWithErrors(MobileDepositRO deposit);

    CRMDeposit depositByName(MobileDepositRO deposit);

    List<CRMError>  depositByNameWithErrors(MobileDepositRO depositRO);

    TrackingInfoExtension getCustomerTrackingInfoExtensionByCustomerId(String customerId);

    TrackingInfoExtension getCustomerTrackingInfoExtensionByKeyword(String keyword);

    TrackingInfo getCustomerTrackingInfo(String trackingInfoId);

    CRMCustomer registerWithWizardConditions(OnboardingWizardConditions wizardConditions);

    ContactExtension updateOnboardingConditionsInDB(String contactId, OnboardingWizardConditions wizardConditions);

    ContactExtension updateExperienceScoreInDB(String contactId, int experienceScore);

    double findMaximumDepositLimit(String contactId);
}
