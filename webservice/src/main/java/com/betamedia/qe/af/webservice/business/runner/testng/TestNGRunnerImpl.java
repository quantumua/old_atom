package com.betamedia.qe.af.webservice.business.runner.testng;

import com.betamedia.qe.af.webservice.business.runner.TestRunner;
import com.betamedia.qe.af.webservice.web.entities.RunTestParams;
import org.springframework.stereotype.Component;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Component
public class TestNGRunnerImpl implements TestRunner {

    @Override
    public boolean isAssignable(RunTestParams params) {
        return params != null;
    }

    @Override
    public void run(List<String> xmlNames) {
        ITestNGListener tla = new TestListenerAdapter();
        TestNG testng = new TestNG();

        testng.setOutputDirectory("public/test-output");
        testng.setTestSuites(xmlNames);

        testng.addListener(tla);
        testng.run();
    }
}
