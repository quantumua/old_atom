package com.betamedia.atom.core.dsl.templates.tp;

import com.betamedia.atom.core.dsl.operations.*;
import com.betamedia.atom.core.dsl.templates.BackEndOperationsTemplate;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface TPTemplate<T extends EnvironmentDependent> extends BackEndOperationsTemplate, EnvironmentDependent {

    AccountOperations accountOperations();

    BonusOperations bonusOperations();

    CustomerOperations customerOperations();

    OnBoardingOperations onBoardingOperations();

    CrmDBOperations crmDbOperations();
}
