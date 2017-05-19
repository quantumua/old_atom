package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.api.tp.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MarketingParametersBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MobileDepositBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMError;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfo;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfoExtension;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 */
public interface CustomerOperations <T extends EnvironmentDependent> extends EnvironmentDependent{

    CRMCustomer register();

    CRMCustomer register(CustomerBuilder customerBuilder);

    CRMCustomer register(CustomerBuilder customerBuilder, MarketingParametersBuilder marketingParametersBuilder);

    List<CRMError>  registerWithErrors(CustomerBuilder customerBuilder);

    CRMCustomer login(String username, String password);

    void logout(String customerId);

    CRMDeposit deposit(MobileDepositBuilder depositBuilder);

    CRMDeposit deposit(MobileDepositBuilder depositBuilder, MarketingParametersBuilder marketingParametersBuilder);

    List<CRMError> depositWithErrors(MobileDepositBuilder depositBuilder);

    CRMDeposit depositByName(MobileDepositBuilder depositBuilder);

    List<CRMError>  depositByNameWithErrors(MobileDepositBuilder depositBuilder);

    TrackingInfoExtension getCustomerTrackingInfoExtensionByCustomerId(String customerId);

    TrackingInfoExtension getCustomerTrackingInfoExtensionByKeyword(String keyword);

    TrackingInfo getCustomerTrackingInfo(String trackingInfoId);

    CRMCustomer registerWithWizardConditions(OnboardingWizardConditions wizardConditions);
}
