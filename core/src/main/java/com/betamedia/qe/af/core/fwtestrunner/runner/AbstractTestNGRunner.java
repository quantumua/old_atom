package com.betamedia.qe.af.core.fwtestrunner.runner;

import com.betamedia.qe.af.core.fwtestrunner.RunnerResult;
import com.betamedia.qe.af.core.fwtestrunner.listeners.ConfigurableListenerFactory;
import com.betamedia.qe.af.core.fwtestrunner.types.TestRunnerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.TestNG;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

import static com.betamedia.qe.af.core.fwtestrunner.types.TestRunnerType.TESTNG;


/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class AbstractTestNGRunner implements TestRunner {
    @Autowired
    private Collection<ConfigurableListenerFactory> listenerFactories;

    @Override
    public TestRunnerType getType() {
        return TESTNG;
    }

    @Override
    public final RunnerResult run(Properties properties, List<String> suites, String outputDirectory) {
        initializeEnvironment(properties);
        TestNG testng = new TestNG();
        testng.setOutputDirectory(outputDirectory);
        listenerFactories.stream()
                .map(f -> f.get(outputDirectory))
                .forEach(testng::addListener);
        testng.setTestSuites(suites);
        testng.run();
        return new RunnerResult(testng.getOutputDirectory() + "/emailable-report.html", testng.hasFailure() || testng.hasSkip());
    }

    /**
     * Override to implement environment bootstrapping
     */
    public abstract void initializeEnvironment(Properties properties);

}
