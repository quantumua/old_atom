package com.betamedia.qe.af.core.tests.base;

import com.betamedia.qe.af.core.api.BackEndOperationsTemplate;
import org.testng.annotations.BeforeTest;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public abstract class AbstractBackEndTest<T extends BackEndOperationsTemplate> {

    private T operationTemplate = null;

    public abstract T getOperationTemplate();

    public T operations() {
        if (operationTemplate == null) {
            operationTemplate = getOperationTemplate();
        }
        return operationTemplate;
    }
}
