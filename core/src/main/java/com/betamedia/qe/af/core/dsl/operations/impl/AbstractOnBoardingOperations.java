package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.dsl.operations.OnBoardingOperations;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.repositories.AbstractUserExperienceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.testng.Assert.assertEquals;

/**
 * Created by mbelyaev on 5/19/17.
 */
public abstract class AbstractOnBoardingOperations<T extends EnvironmentDependent> implements OnBoardingOperations<T> {
    private static final Integer EXPERIENCE_LOW = 1000000;
    private static final Integer EXPERIENCE_UNKNOWN = 1000010;
    private static final Integer EXPERIENCE_REJECTED = 1000020;
    private static final Integer EXPERIENCE_NO = 1000030;
    private static final Integer EXPERIENCE_HIGH = 1000040;
    private static final Integer EXPERIENCE_EXPERT = 1000001;

    @Autowired
    private AbstractUserExperienceInfoRepository<T> userExperienceInfoRepository;

    @Override
    public void assertCustomerRejected(String customerId) {
        assertCustomerExperience(customerId, EXPERIENCE_REJECTED);
    }

    @Override
    public void assertCustomerUnknown(String customerId) {
        assertCustomerExperience(customerId, EXPERIENCE_UNKNOWN);
    }

    @Override
    public void assertCustomerNoExperience(String customerId) {
        assertCustomerExperience(customerId, EXPERIENCE_NO);
    }

    @Override
    public void assertCustomerLowExperience(String customerId) {
        assertCustomerExperience(customerId, EXPERIENCE_LOW);
    }

    @Override
    public void assertCustomerHighExperience(String customerId) {
        assertCustomerExperience(customerId, EXPERIENCE_HIGH);
    }

    @Override
    public void assertCustomerExpert(String customerId) {
        assertCustomerExperience(customerId, EXPERIENCE_EXPERT);
    }

    @Override
    public void assertUsernameRejected(String username) {
        assertUsernameExperience(username, EXPERIENCE_REJECTED);
    }

    @Override
    public void assertUsernameUnknown(String username) {
        assertUsernameExperience(username, EXPERIENCE_UNKNOWN);
    }

    @Override
    public void assertUsernameNoExperience(String username) {
        assertUsernameExperience(username, EXPERIENCE_NO);
    }

    @Override
    public void assertUsernameLowExperience(String username) {
        assertUsernameExperience(username, EXPERIENCE_LOW);
    }

    @Override
    public void assertUsernameHighExperience(String username) {
        assertUsernameExperience(username, EXPERIENCE_HIGH);
    }

    @Override
    public void assertUsernameExpert(String username) {
        assertUsernameExperience(username, EXPERIENCE_EXPERT);
    }

    private void assertCustomerExperience(String customerId, Integer expectedExperience) {
        assertEquals(userExperienceInfoRepository.findOne(customerId).getExperienceLevel(), expectedExperience);
    }

    private void assertUsernameExperience(String username, Integer expectedExperience) {
        assertEquals(userExperienceInfoRepository.findByUsername(username).getExperienceLevel(), expectedExperience);
    }

}
