package com.betamedia.qe.af.core.testingtype.base;

import com.betamedia.qe.af.core.dsl.templates.BackEndOperationsTemplate;
import com.betamedia.qe.af.core.dsl.pages.factory.AbstractPageFactory;
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

    @AfterMethod(lastTimeOnly = true)
    public void tearDown() {
        if (pages.get() != null) {
            pages.get().browser().closeBrowser();
            pages.set(null);
        }
    }

    public P pages() {
        if (pages.get() == null) {
            P pageFactory = getPageFactory();
            pageFactory.browser().maximizeWindow();
            pages.set(pageFactory);
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
