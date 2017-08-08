package com.betamedia.atom.core.testingtype.base;

import com.betamedia.atom.core.fwdataaccess.repository.CsvResourceRepository;
import com.betamedia.atom.core.fwtestrunner.listeners.testng.impl.ContextInitializingListener;
import com.betamedia.atom.core.fwtestrunner.listeners.testng.impl.ScreenShotListener;
import com.betamedia.atom.core.fwtestrunner.listeners.testng.impl.SoftAssertListener;
import com.betamedia.atom.core.fwtestrunner.listeners.testng.impl.TestLinkListener;
import com.betamedia.atom.core.holders.AppContextHolder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.util.Iterator;
import java.util.List;

/**
 * @author mbelyaev
 * @since 5/23/17
 */
@Listeners({
        ContextInitializingListener.class,
        ScreenShotListener.class,
        TestLinkListener.class,
        SoftAssertListener.class
})
public abstract class AbstractTest {
    protected static final String GENERIC_DATA_PROVIDER = "GenericDataProvider";
    protected static final String GENERIC_PARALLEL_DATA_PROVIDER = "GenericParallelDataProvider";
    protected static final String CACHED_DATA_PROVIDER = "CachedDataProvider";
    protected static final String CACHED_PARALLEL_DATA_PROVIDER = "CachedParallelDataProvider";

    private static final ThreadLocal<SoftAssert> softAsserts = ThreadLocal.withInitial(SoftAssert::new);

    private CsvResourceRepository csvResourceRepository = null;

    /**
     * Factory method for managed {@link SoftAssert}s to use inside test lifecycle.
     */
    public static SoftAssert softAssert() {
        return softAsserts.get();
    }

    /**
     * Removes {@link SoftAssert} entity for current thread
     */
    @AfterMethod
    public final void removeAssert() {
        softAsserts.remove();
    }

    /**
     * Test fixture teardown procedure that is invoked by TestNG after each test method invocation. <br/>
     * You might want to keep test environment intact between multiple test iterations (i.e. for data-driven tests),
     * and to only tear it down after a set of tests has concluded. <br/>
     * To achieve that, override {@link AbstractTest#doResetState()} to return <code>false</code>
     *
     * @see AbstractTest#tearDown()
     */
    @AfterMethod
    public final void runTearDownForInvocation() {
        if (doResetState()) tearDown();
    }

    /**
     * Test fixture teardown procedure that is invoked by TestNG after the last test invocation for
     * tests with invocation count > 1. <br/>
     * Calls {@link AbstractTest#tearDown()} that you want to override in implementing classes
     *
     * @implNote Data providers with parallel = true will disregard lastTimeOnly and invoke this for every method execution
     * @see AbstractTest#tearDown()
     * @see AfterMethod#lastTimeOnly()
     */
    @AfterMethod(lastTimeOnly = true)
    public final void runTearDown() {
        tearDown();
    }

    /**
     * Override this method in implementing test class to enable (<code>true</code>) or disable (<code>false</code>)
     * tear down logic between test method iterations (i.e. data-driven); default <code>false</code>
     */
    protected boolean doResetState() {
        return true;
    }

    /**
     * Override this method in implementing classes to provide test fixture tear down behavior
     *
     * @implNote Can be invoked multiple times, implement accordingly
     * @see AbstractClientTest#tearDown()
     */
    protected void tearDown() {
    }

    /**
     * Generic data provider that attempts to read the CSV resource on classpath provided by {@link AbstractTest#getDataSourcePath()}
     * as entities provided by {@link AbstractTest#getDataSourceEntity()};
     */
    @DataProvider(name = GENERIC_DATA_PROVIDER)
    public final Iterator<Object[]> genericDataProvider() {
        return readFromClasspath();
    }

    /**
     * Generic data provider that attempts to read the CSV resource on classpath provided by {@link AbstractTest#getDataSourcePath()}
     * as entities provided by {@link AbstractTest#getDataSourceEntity()};
     *
     * @implNote TestNG will invoke {@link #runTearDown()} after every execution
     */
    @DataProvider(name = GENERIC_PARALLEL_DATA_PROVIDER, parallel = true)
    public final Iterator<Object[]> genericParallelDataProvider() {
        return readFromClasspath();
    }

    /**
     * Generic data provider that attempts to get resources from {@link CsvResourceRepository}
     * as entities provided by {@link AbstractTest#getDataSourceEntity()};
     */
    @DataProvider(name = CACHED_DATA_PROVIDER)
    public final Iterator<Object[]> cachedDataProvider() {
        return readCachedFromClasspath();
    }

    /**
     * Generic data provider that attempts to get resources from {@link CsvResourceRepository}
     * as entities provided by {@link AbstractTest#getDataSourceEntity()};
     *
     * @implNote TestNG will invoke {@link #runTearDown()} after every execution
     */
    @DataProvider(name = CACHED_PARALLEL_DATA_PROVIDER, parallel = true)
    public final Iterator<Object[]> cachedParallelDataProvider() {
        return readCachedFromClasspath();
    }

    private Iterator<Object[]> readCachedFromClasspath() {
        return getResources(getDataSourceEntity())
                .stream()
                .map(a -> new Object[]{a})
                .iterator();
    }

    private Iterator<Object[]> readFromClasspath() {
        return CsvResourceRepository.getData(getDataSourceEntity(), getDataSourcePath())
                .stream()
                .map(d -> new Object[]{d})
                .iterator();
    }

    protected final <T> List<T> getResources(Class<T> entity) {
        return getCsvResourceRepository().get(entity);
    }

    /**
     * Override this method in implementing test class to pass the required entity to GenericDataProvider
     */
    protected Class getDataSourceEntity() {
        return null;
    }

    /**
     * Override this method in implementing test class to pass the data source path to GenericDataProvider
     */
    protected String getDataSourcePath() {
        return null;
    }

    private CsvResourceRepository getCsvResourceRepository() {
        if (csvResourceRepository == null) {
            csvResourceRepository = AppContextHolder.getBean(CsvResourceRepository.class);
        }
        return csvResourceRepository;
    }
}
