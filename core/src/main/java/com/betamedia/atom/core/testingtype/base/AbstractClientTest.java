package com.betamedia.atom.core.testingtype.base;


import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;

import java.util.Optional;


/**
 * @author mbelyaev
 * @since 2/21/17
 */
public abstract class AbstractClientTest<T extends AbstractPageFactory> extends AbstractTest implements WebDriverTest<T> {

    private ThreadLocal<T> pages = new ThreadLocal<>();

    public abstract T getPageFactory();

    @Override
    protected void tearDown() {
        if (pages.get() != null) {
            pages.get().browser().closeBrowser();
            pages.remove();
        }
    }

    @Override
    public final T pages() {
        if (pages.get() == null) {
            T pageFactory = getPageFactory();
            pageFactory.browser().maximizeWindow();
            pages.set(pageFactory);
        }
        return pages.get();
    }

    @Override
    public final Optional<T> optPages() {
        return Optional.ofNullable(pages.get());
    }
}
