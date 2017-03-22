package com.betamedia.qe.af.core.tests.base;


import com.betamedia.qe.af.core.pages.factory.AbstractPageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


/**
 * Created by mbelyaev on 2/21/17.
 */
public abstract class AbstractWebDriverTest<T extends AbstractPageFactory> {

    private ThreadLocal<T> pages = new ThreadLocal<>();

    public abstract T getPageFactory();

    @BeforeMethod
    public void setUp() throws Exception {
        pages.set(getPageFactory());
    }

    @AfterMethod
    public void tearDown() throws Exception {
        pages.get().closeBrowser();
    }

    public T pages() {
        return pages.get();
    }
    
}
