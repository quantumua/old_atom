package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.entities.*;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.atom.core.api.tp.entities.response.CRMError;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 */
public interface CustomerOperations<T extends EnvironmentDependent> extends EnvironmentDependent {

    CRMCustomer register();

    CRMCustomer register(CustomerRO customerRO);

    CRMCustomer register(CustomerRO customerRO, MarketingParametersRO marketingParametersRO);

    List<CRMError> registerWithErrors(CustomerRO customerRO);

    CRMCustomer login(String username, String password);

    void logout(String customerId);

    CRMDeposit deposit(MobileDepositRO deposit);

    CRMDeposit deposit(MobileDepositRO deposit, MarketingParametersRO marketingParametersRO);

    List<CRMError> depositWithErrors(MobileDepositRO deposit);

    CRMDeposit depositByName(MobileDepositRO deposit);

    List<CRMError> depositByNameWithErrors(MobileDepositRO depositRO);

    TrackingInfoExtension getCustomerTrackingInfoExtensionByCustomerId(String customerId);

    TrackingInfoExtension getCustomerTrackingInfoExtensionByKeyword(String keyword);

    TrackingInfo getCustomerTrackingInfo(String trackingInfoId);

    Integer getCustomerLeverageByUsername(String username);

    CRMCustomer registerWithWizardConditions(OnboardingWizardConditions wizardConditions);

    CRMCustomer registerWithWizardConditions(CustomerRO.CustomerROBuilder customerBuilder, OnboardingWizardConditions wizardConditions);

    ContactExtension updateCustomersOnboardingConditions(CRMCustomer customer, OnboardingWizardConditions wizardConditions);

    ContactExtension updateExperienceScoreInDB(String contactId, int experienceScore);

    double findMaximumDepositLimit(String contactId);

    ContactExtension getContactExtension(String contactId);

    ContactBase getContactBase(String contactId);

    ContactBase findByEmailAddress(String emailAddress);

    ContactExtension findExtByEmailAddress (String emailAddress);

    CreditCarddepositExtensionBase findDeposit(String customerID);
}