package com.betamedia.qe.af.core.testingtype.base;

import com.betamedia.qe.af.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.qe.af.core.dsl.templates.BackEndOperationsTemplate;
import org.testng.annotations.AfterMethod;

import java.util.Optional;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public abstract class AbstractEndToEndTest<T extends BackEndOperationsTemplate, P extends AbstractPageFactory> extends AbstractTest implements WebDriverTest<P>, BackEndTest<T> {

    private ThreadLocal<P> pages = new ThreadLocal<>();

    private T operationTemplate;

    public abstract T getOperationTemplate();

    public abstract P getPageFactory();

    @AfterMethod
    public void tearDown() {
        if (keepBrowser()) return;
        if (pages.get() != null) {
            pages.get().browser().closeBrowser();
            pages.set(null);
        }
    }

    @Override
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

    @Override
    public Optional<P> optPages() {
        return Optional.ofNullable(pages.get());
    }
}
