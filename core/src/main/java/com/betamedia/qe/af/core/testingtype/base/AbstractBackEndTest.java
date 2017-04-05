package com.betamedia.qe.af.core.testingtype.base;

import com.betamedia.qe.af.core.dsl.templates.BackEndOperationsTemplate;

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
