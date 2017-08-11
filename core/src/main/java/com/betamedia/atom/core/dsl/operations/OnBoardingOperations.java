package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;

import static com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions.AccessType;

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

    void assertUserCreatedInDatabase(String userEmail);

    void assertUsernameScore(String username, Double expectedScore);

    void assertUsernameLoginType(String username, AccessType accessType);

    void assertTrafficSource(String userLoginName, int expectedTrafficSourceId);

    void assertContactBaseBirthDate(String userName, String expectedBirthDate);

    void assertUsersHaveConnection(String userLoginName, String secondUser, String expectedConnectionRoleName);

    void assertUsersHaveNotConnection(String userLoginName, String secondUser);

}
