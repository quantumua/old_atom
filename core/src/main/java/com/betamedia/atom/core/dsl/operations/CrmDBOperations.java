package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.repositories.*;

/**
 * @author Maksym Tsybulskyy
 *         Date: 8/11/17.
 */
public interface CrmDBOperations <T extends EnvironmentDependent> extends EnvironmentDependent {

    AbstractConnectionBaseRepository connectionBaseRepository();

    AbstractConnectionRoleBaseRepository connectionRoleBaseRepository();

    AbstractContactBaseRepository contactBaseRepository();

    AbstractContactExtensionRepository contactExtensionRepository();

    AbstractRiskLimitsRepository riskLimitsRepository();

    AbstractTrackingInfoExtensionRepository trackingInfoExtensionRepository();

    AbstractTrackingInfoRepository trackingInfoRepository();

    AbstractTradingAccountExtensionRepository tradingAccountExtensionRepository();

    AbstractWithdrawalRequestRepository withdrawalRequestRepository();

    AbstractCreditCardDepositExtensionBase creditCardDepositExtensionBase();
}
