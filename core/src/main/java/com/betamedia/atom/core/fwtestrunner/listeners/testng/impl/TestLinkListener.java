package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testlink.TestCaseResult;
import com.betamedia.atom.core.testlink.TestLinkDisplayIdHolder;
import com.betamedia.atom.core.testlink.TestLinkService;
import com.betamedia.atom.core.testlink.annotations.TestLinkDisplayId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.internal.ConstructorOrMethod;
import org.testng.xml.XmlTest;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Listener for TestLink integration.
 * It is required to report test run results to TestLink.
 * Created by Oleksandr Losiev on 5/16/17.
 */
public class TestLinkListener implements ITestListener {

    private static final String TESTLINK_BUILD_ID = "testlinkBuildId";
    private static final String TESTLINK_PLAN_ID = "testlinkPlanId";
    private static final String DISPLAY_ID = "displayId";
    private static final Logger logger = LogManager.getLogger(TestLinkListener.class);
    private TestLinkService testLinkService;

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        updateTestCaseWithStatus(iTestResult, ExecutionStatus.PASSED);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        updateTestCaseWithStatus(iTestResult, ExecutionStatus.FAILED);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        updateTestCaseWithStatus(iTestResult, ExecutionStatus.NOT_RUN);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    private void updateTestCaseWithStatus(ITestResult testRes, ExecutionStatus status) {
        try {
            getTestCaseResult(testRes, status)
                    .ifPresent(getTestLinkService()::updateTestCase);
        } catch (Exception e) {
            logger.error("Failed to update test with result=" + testRes, e);
            Reporter.log("Failed to update test with result=" + testRes);
        }
    }

    private static Optional<TestCaseResult> getTestCaseResult(ITestResult testResult, ExecutionStatus status) {
        XmlTest xmlTest = testResult.getTestContext().getCurrentXmlTest();
        return Optional.of(
                getIntParameterFromTestXml(xmlTest, TESTLINK_BUILD_ID)
                        .map(build -> logRetrievedParameter(TESTLINK_BUILD_ID, build))
                        .map(build -> resultForBuild(testResult, status, xmlTest, build))
                        .orElseGet(() -> logMissingParameter(TESTLINK_BUILD_ID)));
    }

    private static TestCaseResult resultForBuild(ITestResult testResult, ExecutionStatus status, XmlTest xmlTest, Integer build) {
        return getIntParameterFromTestXml(xmlTest, TESTLINK_PLAN_ID)
                .map(plan -> logRetrievedParameter(TESTLINK_PLAN_ID, plan))
                .map(plan -> resultForPlan(testResult, status, build, plan))
                .orElseGet(() -> logMissingParameter(TESTLINK_PLAN_ID));
    }

    private static TestCaseResult resultForPlan(ITestResult testResult, ExecutionStatus status, Integer build, Integer plan) {
        return getTestDisplayId(testResult)
                .map(displayId -> logRetrievedParameter(DISPLAY_ID, displayId))
                .map(displayId -> new TestCaseResult(build, plan, formatTestParameters(testResult.getParameters()), status, displayId))
                .orElseGet(() -> logMissingParameter(DISPLAY_ID));
    }

    private static <T> T logRetrievedParameter(String parameter, T value) {
        logger.debug("Retrieved " + parameter + "=" + value);
        return value;
    }

    private static TestCaseResult logMissingParameter(String parameter) {
        logger.warn("Failed to update test case because " + parameter + " is missing!");
        Reporter.log("Failed to update test case because " + parameter + " is missing!");
        return null;
    }

    private static Optional<Integer> getIntParameterFromTestXml(XmlTest xml, String paramName) {
        return Optional.ofNullable(xml.getParameter(paramName)).map(Integer::parseInt);
    }

    private static Optional<String> getTestDisplayId(ITestResult iTestResult) {
        return Optional.of(Arrays.stream(iTestResult.getParameters())
                .filter(param -> param instanceof TestLinkDisplayIdHolder)
                .findFirst()
                .map(TestLinkDisplayIdHolder.class::cast)
                .map(TestLinkDisplayIdHolder::getDisplayId)
                .orElseGet(() ->
                        Optional.of(iTestResult)
                                .map(ITestResult::getMethod)
                                .map(ITestNGMethod::getConstructorOrMethod)
                                .map(ConstructorOrMethod::getMethod)
                                .map(method -> method.getAnnotation(TestLinkDisplayId.class))
                                .map(TestLinkDisplayId::value)
                                .orElse(null)
                ));
    }

    private static String formatTestParameters(Object[] params) {
        return Stream.of(params)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    private TestLinkService getTestLinkService() {
        if (testLinkService == null) {
            testLinkService = AppContextHolder.getBean(TestLinkService.class);
        }
        return testLinkService;
    }
}
