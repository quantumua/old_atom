package com.betamedia.qe.af.core.tests.base;

import com.betamedia.qe.af.core.api.BackEndOperationsTemplate;
import com.betamedia.qe.af.core.pages.factory.AbstractPageFactory;
import org.testng.annotations.AfterMethod;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public abstract class AbstractEndToEndTest<T extends BackEndOperationsTemplate, P extends AbstractPageFactory> {

    private ThreadLocal<P> pages = new ThreadLocal<>();

    private T operationTemplate;

    public abstract T getOperationTemplate();

    public abstract P getPageFactory();

    @AfterMethod
    public void tearDown() throws Exception {
        if (pages.get() != null) {
            pages.get().closeBrowser();
        }
    }

    public P pages() {
        if (pages.get() == null) {
            pages.set(getPageFactory());
        }
        return pages.get();
    }

    public T operations() {
        if (operationTemplate == null) {
            operationTemplate = getOperationTemplate();
        }
        return operationTemplate;
    }
}
