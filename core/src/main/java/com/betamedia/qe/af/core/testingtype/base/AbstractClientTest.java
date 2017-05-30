package com.betamedia.qe.af.core.testingtype.base;


import com.betamedia.qe.af.core.dsl.pages.factory.AbstractPageFactory;
import org.testng.annotations.AfterMethod;

import java.util.Optional;


/**
 * Created by mbelyaev on 2/21/17.
 */
public abstract class AbstractClientTest<T extends AbstractPageFactory> extends AbstractTest implements WebDriverTest<T>{

    private ThreadLocal<T> pages = new ThreadLocal<>();

    public abstract T getPageFactory();

    @AfterMethod
    public void tearDown() throws Exception {
        if (pages.get() != null) {
            pages.get().browser().closeBrowser();
            pages.set(null);
        }
    }

    @Override
    public T pages() {
        if (pages.get() == null) {
            T pageFactory = getPageFactory();
            pageFactory.browser().maximizeWindow();
            pages.set(pageFactory);
        }
        return pages.get();
    }

    @Override
    public Optional<T> optPages() {
        return Optional.ofNullable(pages.get());
    }
}
