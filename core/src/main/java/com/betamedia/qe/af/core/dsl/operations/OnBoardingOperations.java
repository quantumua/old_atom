package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;

/**
 * Created by mbelyaev on 5/19/17.
 */
public interface OnBoardingOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    void assertCustomerRejected(String customerId);

    void assertCustomerUnknown(String customerId);

    void assertCustomerNoExperience(String customerId);

    void assertCustomerLowExperience(String customerId);

    void assertCustomerHighExperience(String customerId);

    void assertCustomerExpert(String customerId);

    void assertUsernameRejected(String username);

    void assertUsernameUnknown(String username);

    void assertUsernameNoExperience(String username);

    void assertUsernameLowExperience(String username);

    void assertUsernameHighExperience(String username);

    void assertUsernameExpert(String username);
}
