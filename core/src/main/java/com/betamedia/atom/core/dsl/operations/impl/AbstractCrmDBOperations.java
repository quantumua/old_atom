package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.dsl.operations.CrmDBOperations;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Maksym Tsybulskyy
 *         Date: 8/11/17.
 */
public abstract class AbstractCrmDBOperations<T extends EnvironmentDependent> implements CrmDBOperations {

    @Autowired
    private AbstractConnectionBaseRepository<T> connectionBaseRepository;

    @Autowired
    private AbstractConnectionRoleBaseRepository<T> connectionRoleBaseRepository;

    @Autowired
    AbstractContactBaseRepository<T> contactBaseRepository;

    @Autowired
    private AbstractContactExtensionRepository<T> contactExtensionRepository;

    @Autowired
    private AbstractRiskLimitsRepository<T> riskLimitsRepository;

    @Autowired
    private AbstractTrackingInfoExtensionRepository<T> trackingInfoExtensionRepository;

    @Autowired
    private AbstractTrackingInfoRepository<T> trackingInfoRepository;

    @Autowired
    private AbstractTradingAccountExtensionRepository<T> tradingAccountExtensionRepository;

    @Autowired
    private AbstractWithdrawalRequestRepository<T> withdrawalRequestRepository;

    @Autowired
    private AbstractCreditCardDepositExtensionBase<T> creditCardDepositExtensionBase;

    @Override
    public AbstractConnectionBaseRepository connectionBaseRepository() {
        return connectionBaseRepository;
    }

    @Override
    public AbstractConnectionRoleBaseRepository connectionRoleBaseRepository() {
        return connectionRoleBaseRepository;
    }

    @Override
    public AbstractContactBaseRepository contactBaseRepository() {
        return contactBaseRepository;
    }

    @Override
    public AbstractContactExtensionRepository contactExtensionRepository() {
        return contactExtensionRepository;
    }

    @Override
    public AbstractRiskLimitsRepository riskLimitsRepository() {
        return riskLimitsRepository;
    }

    @Override
    public AbstractTrackingInfoExtensionRepository trackingInfoExtensionRepository() {
        return trackingInfoExtensionRepository;
    }

    @Override
    public AbstractTrackingInfoRepository trackingInfoRepository() {
        return trackingInfoRepository;
    }

    @Override
    public AbstractTradingAccountExtensionRepository tradingAccountExtensionRepository() {
        return tradingAccountExtensionRepository;
    }

    @Override
    public AbstractWithdrawalRequestRepository withdrawalRequestRepository() {
        return withdrawalRequestRepository;
    }

    @Override
    public AbstractCreditCardDepositExtensionBase creditCardDepositExtensionBase() {
        return creditCardDepositExtensionBase;
    }
}
