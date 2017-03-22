package com.betamedia.qe.af.core.tests.base;

import com.betamedia.qe.af.core.api.BackEndOperationsTemplate;
import com.betamedia.qe.af.core.pages.factory.AbstractPageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public abstract class AbstractEndToEndTest<T extends BackEndOperationsTemplate, P extends AbstractPageFactory> {

    private ThreadLocal<P> pages = new ThreadLocal<>();

    private T operationTemplate;

    public abstract T getOperationTemplate();

    public abstract P getPageFactory();

    @BeforeTest
    public void setUpTests() throws Exception {
        operationTemplate = getOperationTemplate();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        pages.set(getPageFactory());
    }

    @AfterMethod
    public void tearDown() throws Exception {
        pages.get().closeBrowser();
    }

    public P pages() {
        return pages.get();
    }

    public T operations() {
        return operationTemplate;
    }
}
