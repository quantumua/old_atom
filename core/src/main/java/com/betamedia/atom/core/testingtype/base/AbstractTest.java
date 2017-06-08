package com.betamedia.atom.core.testingtype.base;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.core.io.ClassPathResource;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

/**
 * @author mbelyaev
 * @since 5/23/17
 */
public abstract class AbstractTest {
    protected static final String GENERIC_DATA_PROVIDER = "GenericDataProvider";
    protected static final String GENERIC_PARALLEL_DATA_PROVIDER = "GenericParallelDataProvider";
    private static final String DATA_PROVIDER_ERROR = "Failed to load data";

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
        return getData(getDataSourceEntity(), getDataSourcePath())
                .stream()
                .map(d -> new Object[]{d})
                .iterator();
    }

    /**
     * Generic data provider that attempts to read the CSV resource on classpath provided by {@link AbstractTest#getDataSourcePath()}
     * as entities provided by {@link AbstractTest#getDataSourceEntity()};
     *
     * @implNote TestNG will invoke {@link #runTearDown()} after every execution
     */
    @DataProvider(name = GENERIC_PARALLEL_DATA_PROVIDER, parallel = true)
    public final Iterator<Object[]> genericParallelDataProvider() {
        return getData(getDataSourceEntity(), getDataSourcePath())
                .stream()
                .map(d -> new Object[]{d})
                .iterator();
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

    private static <T> List<T> getData(Class<T> tClass, String path) {
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(tClass);
        CsvToBean<T> csvToBean = new CsvToBean<>();
        try (InputStream inputStream = new ClassPathResource(path).getInputStream()) {
            return csvToBean.parse(strategy, new CSVReader(new InputStreamReader(inputStream)));
        } catch (IOException e) {
            Reporter.log(DATA_PROVIDER_ERROR + '\n');
            throw new RuntimeException(DATA_PROVIDER_ERROR, e);
        }
    }
}
