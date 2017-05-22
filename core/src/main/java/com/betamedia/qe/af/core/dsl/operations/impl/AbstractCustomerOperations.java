package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.api.tp.adapters.MobileCRMHTTPAdaper;
import com.betamedia.qe.af.core.api.tp.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MarketingParametersBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MobileDepositBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.*;
import com.betamedia.qe.af.core.dsl.operations.CustomerOperations;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.entities.ContactExtension;
import com.betamedia.qe.af.core.persistence.entities.RiskLimits;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfo;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfoExtension;
import com.betamedia.qe.af.core.persistence.repositories.AbstractContactExtensionRepository;
import com.betamedia.qe.af.core.persistence.repositories.AbstractRiskLimitsRepository;
import com.betamedia.qe.af.core.persistence.repositories.AbstractTrackingInfoExtensionRepository;
import com.betamedia.qe.af.core.persistence.repositories.AbstractTrackingInfoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.testng.Assert.*;

/**
 * This class is designed to facilitate the execution of common operations related to customer operations.
 * It can be used as a "building block" when writing integration tests.
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 * @see CRMCustomer
 */
public abstract class AbstractCustomerOperations<T extends EnvironmentDependent> implements CustomerOperations<T> {

    private static final Logger logger = LogManager.getLogger(AbstractCustomerOperations.class);

    @Autowired
    private MobileCRMHTTPAdaper<T> mobileCRMHTTPAdaper;

    @Autowired
    private AbstractTrackingInfoRepository<T> trackingInfoRepository;

    @Autowired
    private AbstractTrackingInfoExtensionRepository<T> trackingInfoExtensionRepository;

    @Autowired
    public AbstractContactExtensionRepository<T> contactExtensionRepository;

    @Autowired
    public AbstractRiskLimitsRepository<T> riskLimitsRepository;

    @Autowired
    public AbstractContactExtensionRepository<T> contactExtensionRepository;

    @Autowired
    public AbstractRiskLimitsRepository<T> riskLimitsRepository;

    /**
     * Registers new CRM customer with default customer builder
     */
    @Override
    public CRMCustomer register() {
        return register(new CustomerBuilder());
    }

    /**
     * Registers new CRM customer with given customer builder.
     */
    @Override
    public CRMCustomer register(CustomerBuilder customerBuilder) {
        CRMResponse<CRMRegisterResult> register = mobileCRMHTTPAdaper.register(customerBuilder.createCustomerRO());
        return verifyAndReturnCRMCustomer(register);
    }

    /**
     * Registers new CRM customer with marketing parameters.
     */
    @Override
    public CRMCustomer register(CustomerBuilder customerBuilder, MarketingParametersBuilder marketingParametersBuilder) {
        CRMResponse<CRMRegisterResult> register = mobileCRMHTTPAdaper.register(customerBuilder.createCustomerRO(),
                marketingParametersBuilder.createMarketingRO());
        return verifyAndReturnCRMCustomer(register);
    }

    /**
     * Registers new CRM customer and expects errors.
     */
    @Override
    public List<CRMError> registerWithErrors(CustomerBuilder customerBuilder) {
        CRMResponse<CRMRegisterResult> register = mobileCRMHTTPAdaper.register(customerBuilder.createCustomerRO());
        List<CRMError> registerErrors = register.getErrors();
        assertFalse(registerErrors.isEmpty(), "Registration errors were expected, but there were none.");
        return registerErrors;
    }

    private CRMCustomer verifyAndReturnCRMCustomer(CRMResponse<CRMRegisterResult> register) {
        CRMRegisterResult registrationResult = register.getResult();
        List<CRMError> registrationErrors = register.getErrors();

        assertNotNull(registrationResult, "The new customer wasn't created" + registrationErrors);
        assertTrue(registrationErrors.isEmpty(), "There were errors during customer registration:" + registrationErrors);
        return registrationResult.getCustomer();
    }

    /**
     * Logs in an existing user with given username and password.
     */
    @Override
    public CRMCustomer login(String username, String password) {
        CRMResponse<CRMRegisterResult> loginResponse = mobileCRMHTTPAdaper.login(username, password);
        CRMRegisterResult loggedInCustomer = loginResponse.getResult();
        List<CRMError> loginErrors = loginResponse.getErrors();

        assertNotNull(loggedInCustomer, "The new customer wasn't created" + loginErrors);
        assertTrue(loginErrors.isEmpty(), "There were errors during customer registration:" + loginErrors);
        return loggedInCustomer.getCustomer();
    }

    /**
     * Logouts an existing user with given customer id.
     */
    @Override
    public void logout(String customerId) {
        CRMResponse logoutResponse = mobileCRMHTTPAdaper.logout(customerId);
        List<CRMError> logoutErrors = logoutResponse == null ? null : logoutResponse.getErrors();
        assertNull(logoutResponse, "There were errors during customer logout:" + logoutErrors);
    }

    /**
     * Performs a deposit with given deposit builder.
     */
    @Override
    public CRMDeposit deposit(MobileDepositBuilder depositBuilder) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdaper.deposit(depositBuilder.createMobileDepositRO());
        return verifyAndReturnDepositResult(depositResponse);
    }

    /**
     * Performs a deposit with marketing parameters.
     */
    @Override
    public CRMDeposit deposit(MobileDepositBuilder depositBuilder, MarketingParametersBuilder marketingParametersBuilder) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdaper.deposit(depositBuilder.createMobileDepositRO(), marketingParametersBuilder.createMarketingRO());
        return verifyAndReturnDepositResult(depositResponse);
    }

    private CRMDeposit verifyAndReturnDepositResult(CRMResponse<MobileCRMDeposit> depositResponse) {
        CRMDeposit depositResult = depositResponse.getResult();
        List<CRMError> depositErrors = depositResponse.getErrors();

        assertNotNull(depositResult, "Deposit wasn't made" + depositErrors);
        assertTrue(depositErrors.isEmpty(), "There were errors when performing the deposit:" + depositErrors);
        return depositResult;
    }

    /**
     * Performs a deposit and expects errors.
     */
    @Override
    public List<CRMError> depositWithErrors(MobileDepositBuilder depositBuilder) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdaper.deposit(depositBuilder.createMobileDepositRO());
        List<CRMError> depositErrors = depositResponse.getErrors();
        assertFalse(depositErrors.isEmpty(), "Deposit errors were expected, but there were none.");
        return depositErrors;
    }

    /**
     * Performs a deposit by name with given deposit builder.
     */
    @Override
    public CRMDeposit depositByName(MobileDepositBuilder depositBuilder) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdaper.depositByName(depositBuilder.createMobileDepositRO());
        CRMDeposit depositResult = depositResponse.getResult();
        List<CRMError> depositErrors = depositResponse.getErrors();

        assertNotNull(depositResult, "Deposit by name wasn't made" + depositErrors);
        assertTrue(depositErrors.isEmpty(), "There were errors when performing the deposit by name:" + depositErrors);
        return depositResult;
    }

    /**
     * Performs a deposit by name and expects errors.
     */
    @Override
    public List<CRMError> depositByNameWithErrors(MobileDepositBuilder depositBuilder) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdaper.depositByName(depositBuilder.createMobileDepositRO());
        List<CRMError> depositErrors = depositResponse.getErrors();
        assertFalse(depositErrors.isEmpty(), "Deposit errors were expected, but there were none.");
        return depositErrors;
    }

    /**
     * Performs a database query to find customer tracking info extension by a given customer id.
     */
    @Override
    public TrackingInfoExtension getCustomerTrackingInfoExtensionByCustomerId(String customerId) {
        List<TrackingInfoExtension> infoExtensions =
                trackingInfoExtensionRepository.findByCustomerIdOrderByCookieCreationTimeDesc(customerId);
        assertFalse(infoExtensions.isEmpty());
        return infoExtensions.get(0);
    }

    /**
     * Performs a database query to find customer tracking extension info by keyword.
     */
    @Override
    public TrackingInfoExtension getCustomerTrackingInfoExtensionByKeyword(String keyword) {
        List<TrackingInfoExtension> infoExtensions =
                trackingInfoExtensionRepository.findByKeywordOrderByCookieCreationTimeDesc(keyword);
        assertFalse(infoExtensions.isEmpty());
        return infoExtensions.get(0);
    }

    /**
     * Performs a database query to find customer tracking info by tracking info id.
     */
    @Override
    public TrackingInfo getCustomerTrackingInfo(String trackingInfoId) {
        TrackingInfo trackingInfo = trackingInfoRepository.findOne(trackingInfoId);
        assertNotNull(trackingInfo);
        return trackingInfo;
    }

    @Override
    public CRMCustomer registerWithWizardConditions(OnboardingWizardConditions wizardConditions) {
        CRMCustomer registeredCustomer = register(new CustomerBuilder());
        ContactExtension contactExtension = saveAndReturnContactExtension(registeredCustomer.getId(), wizardConditions);

        String tradingAccountId = registeredCustomer.getBinaryAccount().getExternalId();
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(tradingAccountId);
        if (wizardConditions.hasDeposit()) {
            deposit(depositBuilder);
        }

        if (wizardConditions.hasPendingDeposit()) {
            double maxLimit = findMaximumDepositLimit(contactExtension);
            depositBuilder.setAmount((long)maxLimit + 1000);
            depositWithErrors(depositBuilder);
        }

        return registeredCustomer;
    }

    private ContactExtension saveAndReturnContactExtension(String contactId, OnboardingWizardConditions wizardConditions) {
        final Integer defaultBirthCountry = 100000207;
        final Integer defaultNationality = 100000207;

        ContactExtension contactExtension = contactExtensionRepository.findOne(contactId);
        assertNotNull(contactExtension);

        contactExtension.setExperienceLevel(wizardConditions.getExperienceLevel().getLevel());
        contactExtension.setPoiStatus(wizardConditions.getPoiStatus().getStatus());
        contactExtension.setPorStatus(wizardConditions.getPorStatus().getStatus());
        contactExtension.setAccountType(wizardConditions.getAccountType().getType());

        contactExtension.setFnsPersonal(wizardConditions.isFnsPersonal());
        contactExtension.setFnsTrading(wizardConditions.isFnsTrading());
        contactExtension.setRiskWarning(wizardConditions.hasRiskWarning());
        contactExtension.setHasRegulationAnswers(wizardConditions.hasRegulationAnswers());

        if (wizardConditions.hasAdditionalDetails()) {
            contactExtension.setCountryOfBirth(defaultBirthCountry);
            contactExtension.setNationality(defaultNationality);
        }

        contactExtensionRepository.save(contactExtension);
        return contactExtension;
    }

    private double findMaximumDepositLimit(ContactExtension contactExtension) {
        String riskLimitsId = contactExtension.getRiskLimitsId();

        RiskLimits riskLimits = riskLimitsRepository.findOne(riskLimitsId);
        assertNotNull(riskLimits);

        return riskLimits.getDailyLimit();
    }
}
