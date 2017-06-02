package com.betamedia.qe.af.core.testingtype.base;

import com.betamedia.qe.af.core.dsl.templates.BackEndOperationsTemplate;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public abstract class AbstractBackEndTest<T extends BackEndOperationsTemplate> extends AbstractTest implements BackEndTest<T> {

    private T operationTemplate = null;

    public abstract T getOperationTemplate();

    public final T operations() {
        if (operationTemplate == null) {
            operationTemplate = getOperationTemplate();
        }
        return operationTemplate;
    }
}
