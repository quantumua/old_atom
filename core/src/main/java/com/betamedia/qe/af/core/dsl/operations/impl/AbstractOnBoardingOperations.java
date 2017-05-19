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

    @Autowired
    private AbstractUserExperienceInfoRepository<T> userExperienceInfoRepository;

    @Override
    public void assertCustomerRejected(String customerId) {
        assertCustomerExperience(customerId, 1000010);
    }

    @Override
    public void assertCustomerUnknown(String customerId) {
        assertCustomerExperience(customerId, 1000020);
    }

    @Override
    public void assertCustomerNoExperience(String customerId) {
        assertCustomerExperience(customerId, 1000030);
    }

    @Override
    public void assertCustomerLowExperience(String customerId) {
        assertCustomerExperience(customerId, 1000000);
    }

    @Override
    public void assertCustomerHighExperience(String customerId) {
        assertCustomerExperience(customerId, 1000040);
    }

    @Override
    public void assertCustomerExpert(String customerId) {
        assertCustomerExperience(customerId, 1000001);
    }

    private void assertCustomerExperience(String customerId, Integer expectedExperience) {
        assertEquals(userExperienceInfoRepository.findOne(customerId).getExperienceLevel(), expectedExperience);
    }

}
