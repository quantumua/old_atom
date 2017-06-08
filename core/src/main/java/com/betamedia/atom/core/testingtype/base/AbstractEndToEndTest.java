package com.betamedia.atom.core.testingtype.base;

import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.atom.core.dsl.templates.BackEndOperationsTemplate;

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

    @Override
    protected void tearDown() {
        if (pages.get() != null) {
            pages.get().browser().closeBrowser();
            pages.remove();
        }
    }

    @Override
    public final P pages() {
        if (pages.get() == null) {
            P pageFactory = getPageFactory();
            pageFactory.browser().maximizeWindow();
            pages.set(pageFactory);
        }
        return pages.get();
    }

    public final T operations() {
        if (operationTemplate == null) {
            operationTemplate = getOperationTemplate();
        }
        return operationTemplate;
    }

    @Override
    public final Optional<P> optPages() {
        return Optional.ofNullable(pages.get());
    }
}
