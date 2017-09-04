package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.adapters.MobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO.CustomerROBuilder;
import com.betamedia.atom.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.atom.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.atom.core.api.tp.entities.response.*;
import com.betamedia.atom.core.dsl.operations.CustomerOperations;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.entities.*;
import com.betamedia.atom.core.persistence.repositories.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;
import static org.testng.Assert.*;
import static org.testng.Assert.assertNotNull;

/**
 * This class is designed to facilitate the execution of common operations related to customer operations.
 * It can be used as a "building block" when writing integration tests.
 *
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 * @see CRMCustomer
 */
public abstract class AbstractCustomerOperations<T extends EnvironmentDependent> implements CustomerOperations<T> {

    private static final Logger logger = LogManager.getLogger(AbstractCustomerOperations.class);

    @Autowired
    private MobileCRMHTTPAdapter<T> mobileCRMHTTPAdapter;

    @Autowired
    private AbstractTrackingInfoRepository<T> trackingInfoRepository;

    @Autowired
    private AbstractTrackingInfoExtensionRepository<T> trackingInfoExtensionRepository;

    @Autowired
    private AbstractContactBaseRepository<T> contactBaseRepository;

    @Autowired
    private AbstractContactExtensionRepository<T> contactExtensionRepository;

    @Autowired
    private AbstractCreditCardDepositExtensionBase<T> creditCardDepositExtensionBase;

    @Autowired
    private AbstractRiskLimitsRepository<T> riskLimitsRepository;

    @Autowired
    private AbstractTradingAccountExtensionRepository<T> tradingAccountExtensionRepository;

    /**
     * Registers new CRM customer with default customer builder
     */
    @Override
    public CRMCustomer register() {
        return register(CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
    }

    /**
     * Registers new CRM customer for given CustomerRO object.
     */
    @Override
    public CRMCustomer register(CustomerRO customerRO) {
        CRMResponse<CRMRegisterResult> register = mobileCRMHTTPAdapter.register(customerRO);
        return verifyAndReturnCRMCustomer(register);
    }

    /**
     * Registers new CRM customer with marketing parameters.
     */
    @Override
    public CRMCustomer register(CustomerRO customerRO, MarketingParametersRO marketingParametersRO) {
        CRMResponse<CRMRegisterResult> register = mobileCRMHTTPAdapter.register(customerRO,
                marketingParametersRO);
        return verifyAndReturnCRMCustomer(register);
    }

    /**
     * Registers new CRM customer and expects errors.
     */
    @Override
    public List<CRMError> registerWithErrors(CustomerRO customerRO) {
        CRMResponse<CRMRegisterResult> register = mobileCRMHTTPAdapter.register(customerRO);
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
        CRMResponse<CRMRegisterResult> loginResponse = mobileCRMHTTPAdapter.login(username, password);
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
        CRMResponse logoutResponse = mobileCRMHTTPAdapter.logout(customerId);
        List<CRMError> logoutErrors = logoutResponse == null ? null : logoutResponse.getErrors();
        assertNull(logoutResponse, "There were errors during customer logout:" + logoutErrors);
    }

    /**
     * Performs a deposit with given deposit builder.
     */
    @Override
    public CRMDeposit deposit(MobileDepositRO deposit) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdapter.deposit(deposit);
        return verifyAndReturnDepositResult(depositResponse);
    }

    /**
     * Performs a deposit with marketing parameters.
     */
    @Override
    public CRMDeposit deposit(MobileDepositRO deposit, MarketingParametersRO marketingParametersRO) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdapter.deposit(deposit, marketingParametersRO);
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
    public List<CRMError> depositWithErrors(MobileDepositRO deposit) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdapter.deposit(deposit);
        List<CRMError> depositErrors = depositResponse.getErrors();
        assertFalse(depositErrors.isEmpty(), "Deposit errors were expected, but there were none.");
        return depositErrors;
    }

    /**
     * Performs a deposit by name with given deposit builder.
     */
    @Override
    public CRMDeposit depositByName(MobileDepositRO deposit) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdapter.depositByName(deposit);
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
    public List<CRMError> depositByNameWithErrors(MobileDepositRO deposit) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdapter.depositByName(deposit);
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

    /**
     * Performs a database query to find contact id. Then finds binary account in the database and gets its leverage.
     */
    @Override
    public Integer getCustomerLeverageByUsername(String username) {
        ContactExtension contactExtension = contactExtensionRepository.findByUsername(username);
        assertNotNull(contactExtension);
        List<TradingAccountExtension> tradingAccountExtensions = tradingAccountExtensionRepository.findByCustomerId(contactExtension.getContactId());

        for (TradingAccountExtension tradingAccountExtension : tradingAccountExtensions) {
            int leverage = tradingAccountExtension.getLeverage();
            if (leverage > 0) return leverage;
        }
        return 0;
    }

    @Override
    public CRMCustomer registerWithWizardConditions(OnboardingWizardConditions wizardConditions) {
        return registerWithWizardConditions(CustomerRO.builder(CRMMobileAPINamingStrategy.get()), wizardConditions);
    }

    @Override
    public CRMCustomer registerWithWizardConditions(CustomerROBuilder customerBuilder, OnboardingWizardConditions wizardConditions) {
        CRMCustomer registeredCustomer = register(customerBuilder.setUserNamingStrategy(CRMMobileAPINamingStrategy.get()).build());
        updateCustomersOnboardingConditions(registeredCustomer, wizardConditions);
        return registeredCustomer;
    }

    @Override
    public ContactExtension updateCustomersOnboardingConditions(CRMCustomer registeredCustomer, OnboardingWizardConditions wizardConditions) {
        ContactExtension contactExtension = contactExtensionRepository.findOne(registeredCustomer.getId());

        if (wizardConditions.hasDeposit()) {
            //TODO user to deposit to binary, adjust for binary platform deprecation
            placeWizardDeposit(registeredCustomer.getDisplayId(), contactExtension);
        }

        assertNotNull(contactExtension);

        contactExtension.setExperienceLevel(wizardConditions.getExperienceLevel().getLevel());
        contactExtension.setPoiStatus(wizardConditions.getPoiStatus().getStatus());
        contactExtension.setPorStatus(wizardConditions.getPorStatus().getStatus());
        contactExtension.setAccountType(wizardConditions.getAccountType().getType());
        contactExtension.setPoiOcrStatus(wizardConditions.getPoiOcrStatus().getStatus());

        contactExtension.setFnsPersonal(wizardConditions.isFnsPersonal());
        contactExtension.setFnsTrading(wizardConditions.isFnsTrading());
        contactExtension.setRiskWarning(wizardConditions.hasRiskWarning());
        contactExtension.setHasRegulationAnswers(wizardConditions.hasRegulationAnswers());
        contactExtension.setCustomerCompliant(wizardConditions.isCustomerCompliant());

        contactExtensionRepository.save(contactExtension);
        return contactExtension;
    }

    private void placeWizardDeposit(String tradingAccountId, ContactExtension contactExtension) {
        final Integer defaultBirthCountry = 100000207;
        final Integer defaultNationality = 100000207;

        //workaround:
        //it is not possible to place a deposit if additional details section is not filled
        //and additional details have to be filled via UI
        contactExtension.setCountryOfBirth(defaultBirthCountry);
        contactExtension.setNationality(defaultNationality);
        contactExtensionRepository.save(contactExtension);

        deposit(MobileDepositRO.builder(tradingAccountId).build());

        contactExtension.setCountryOfBirth(null);
        contactExtension.setNationality(null);
        contactExtensionRepository.save(contactExtension);
    }

    @Override
    public ContactExtension updateExperienceScoreInDB(String contactId, int experienceScore) {

        ContactExtension contactExtension = contactExtensionRepository.findOne(contactId);
        softAssert().assertNotNull(contactExtension);
        contactExtension.setExperienceScore((double) experienceScore);
        contactExtensionRepository.save(contactExtension);
        return contactExtension;
    }

    @Override
    public double findMaximumDepositLimit(String contactId) {
        ContactExtension contactExtension = contactExtensionRepository.findOne(contactId);
        String riskLimitsId = contactExtension.getRiskLimitsId();

        RiskLimits riskLimits = riskLimitsRepository.findOne(riskLimitsId);
        softAssert().assertNotNull(riskLimits);

        return riskLimits.getVerifySettleFrom();
    }

    @Override
    public ContactExtension getContactExtension(String contactId) {
        ContactExtension contactExtension = contactExtensionRepository.findOne(contactId);
        assertNotNull(contactExtension, "Unable to locate customer by ID: " + contactId);
        return contactExtension;
    }

    @Override
    public ContactBase getContactBase(String contactId) {
        ContactBase contactBase = contactBaseRepository.findOne(contactId);
        assertNotNull(contactBase, "Unable to locate customer by ID: " + contactId);
        return contactBase;
    }

    @Override
    public ContactBase findByEmailAddress (String emailAddress) {
        ContactBase contactBase = contactBaseRepository.findByEmailAddress1(emailAddress);
        assertNotNull(contactBase, "Unable to locate customer by email: " + emailAddress);
        return contactBase;
    }

    @Override
    public ContactExtension findExtByEmailAddress (String emailAddress) {
        ContactExtension contactExtension = contactExtensionRepository.findByUsername(emailAddress);
        assertNotNull(contactExtension, "Unable to locate customer by email: " + emailAddress);
        return contactExtension;
    }

    @Override
    public CreditCarddepositExtensionBase findDeposit(String customerID){
        CreditCarddepositExtensionBase ccExtBase = creditCardDepositExtensionBase.findByCustomerId(customerID);
        assertNotNull(ccExtBase, "Unable to locate customer by email: " + customerID);
        return ccExtBase;
    }
}