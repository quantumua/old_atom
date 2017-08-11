package com.betamedia.atom.core.dsl.templates.tp.impl;

import com.betamedia.atom.core.dsl.operations.*;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public abstract class AbstractTPTemplate<T extends EnvironmentDependent> implements TPTemplate<T> {

    @Autowired
    private AccountOperations<T> accountOperations;
    @Autowired
    private BonusOperations<T> bonusOperations;
    @Autowired
    private CustomerOperations<T> customerOperations;
    @Autowired
    private OnBoardingOperations<T> onBoardingOperations;
    @Autowired
    private CrmDBOperations<T> crmDBOperations;

    @Override
    public AccountOperations accountOperations() {
        return accountOperations;
    }

    @Override
    public BonusOperations bonusOperations() {
        return bonusOperations;
    }

    @Override
    public CustomerOperations customerOperations() {
        return customerOperations;
    }

    @Override
    public OnBoardingOperations onBoardingOperations() {
        return onBoardingOperations;
    }

    @Override
    public CrmDBOperations crmDbOperations() {
        return crmDBOperations;
    }
}
