package com.betamedia.qe.af.core.testingtype.base;

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
    private static final String DATA_PROVIDER_ERROR = "Failed to load data";

    /**
     * Test fixture teardown procedure that is invoked by TestNG after each test method invocation. <br/>
     * You might want to keep test environment intact between multiple test iterations (i.e. for data-driven tests),
     * and to only tear it down after a set of tests has concluded. <br/>
     * To achieve that, override {@link AbstractTest#doResetState()} to return <code>false</code>
     *
     * @see AbstractTest#runTearDownForInvocation()
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
     * @see AbstractTest#runTearDownForInvocation()
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
     * @implNote Will be invoked twice on for last test invocation, implement accordingly
     * @see AbstractClientTest#tearDown()
     */
    protected void tearDown() {
    }

    /**
     * Generic data provider that attempts to read the CSV resource on classpath provided by {@link AbstractTest#getDataSourcePath()}
     * as entities provided by {@link AbstractTest#getDataSourceEntity()};
     */
    @DataProvider(name = "GenericDataProvider", parallel = true)
    public final Iterator<Object[]> genericDataProvider() {
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

    //TODO: Should be removed shortly. Generic data provider should be used instead.
    /*@DataProvider(name = "demoWizardConditions")
    public static Object[][] conditions() {
        List<OnboardingWizardConditions> wizardConditionsList = new ArrayList<>();
        HeaderColumnNameMappingStrategy<OnboardingWizardConditions> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(OnboardingWizardConditions.class);
        CsvToBean<OnboardingWizardConditions> csvToBean = new CsvToBean<>();

        try (InputStream resourceInputStream = new ClassPathResource("/data/demoWizardTestCases.csv").getInputStream();) {
            wizardConditionsList = csvToBean.parse(strategy, new CSVReader(new InputStreamReader(resourceInputStream)));
        } catch (Exception e) {
            fail("Failed to initialize wizard condition tests");
        }

        Object[][] result = new Object[wizardConditionsList.size()][1];
        for (int i = 0; i < wizardConditionsList.size(); i++) {
            result[i][0] = wizardConditionsList.get(i);
        }
        return result;

    }*/
}
