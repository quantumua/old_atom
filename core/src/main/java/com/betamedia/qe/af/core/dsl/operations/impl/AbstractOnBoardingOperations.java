package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.api.crm.form.entities.OnboardingWizardConditions.ExperienceLevel;
import com.betamedia.qe.af.core.dsl.operations.OnBoardingOperations;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.repositories.AbstractUserExperienceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static com.betamedia.qe.af.core.api.crm.form.entities.OnboardingWizardConditions.ExperienceLevel.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by mbelyaev on 5/19/17.
 */
public abstract class AbstractOnBoardingOperations<T extends EnvironmentDependent> implements OnBoardingOperations<T> {
    @Autowired
    private AbstractUserExperienceInfoRepository<T> userExperienceInfoRepository;

    @Override
    public void assertCustomerRejected(String customerId) {
        assertCustomerExperience(customerId, REJECTED);
    }

    @Override
    public void assertCustomerUnknown(String customerId) {
        assertCustomerExperience(customerId, UNKNOWN);
    }

    @Override
    public void assertCustomerNoExperience(String customerId) {
        assertCustomerExperience(customerId, NO_EXPERIENCE);
    }

    @Override
    public void assertCustomerLowExperience(String customerId) {
        assertCustomerExperience(customerId, LOW_EXPERIENCE);
    }

    @Override
    public void assertCustomerHighExperience(String customerId) {
        assertCustomerExperience(customerId, HIGH_EXPERIENCE);
    }

    @Override
    public void assertCustomerExpert(String customerId) {
        assertCustomerExperience(customerId, EXPERT);
    }

    @Override
    public void assertUsernameRejected(String username) {
        assertUsernameExperience(username, REJECTED);
    }

    @Override
    public void assertUsernameUnknown(String username) {
        assertUsernameExperience(username, UNKNOWN);
    }

    @Override
    public void assertUsernameNoExperience(String username) {
        assertUsernameExperience(username, NO_EXPERIENCE);
    }

    @Override
    public void assertUsernameLowExperience(String username) {
        assertUsernameExperience(username, LOW_EXPERIENCE);
    }

    @Override
    public void assertUsernameHighExperience(String username) {
        assertUsernameExperience(username, HIGH_EXPERIENCE);
    }

    @Override
    public void assertUsernameExpert(String username) {
        assertUsernameExperience(username, EXPERT);
    }

    private void assertCustomerExperience(String customerId, ExperienceLevel expectedExperience) {
        assertEquals(userExperienceInfoRepository.findOne(customerId).getExperienceLevel(), expectedExperience.getLevel());
    }

    private void assertUsernameExperience(String username, ExperienceLevel expectedExperience) {
        assertEquals(userExperienceInfoRepository.findByUsername(username).getExperienceLevel(), expectedExperience.getLevel());
    }

    @Override
    public void assertUsernameScore(String username, Double expectedScore) {
        assertEquals(userExperienceInfoRepository.findByUsername(username).getExperienceScore(), expectedScore);
    }

}
