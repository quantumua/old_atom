package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testlink.TestCaseResult;
import com.betamedia.atom.core.testlink.TestLinkDisplayIdHolder;
import com.betamedia.atom.core.testlink.TestLinkService;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.google.common.base.Throwables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.internal.ConstructorOrMethod;
import org.testng.xml.XmlTest;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

/**
 * Listener for TestLink integration.
 * It is required to report test run results to TestLink.
 * Created by Oleksandr Losiev on 5/16/17.
 */
public class TestLinkListener implements ITestListener {

    public static final String CONFIGURATION_KEY = "configuration";
    private static final String TESTLINK_BUILD_ID = "testlinkBuildId";
    private static final String TESTLINK_PLAN_ID = "testlinkPlanId";
    private static final String DISPLAY_ID = "displayId";
    private static final Logger logger = LogManager.getLogger(TestLinkListener.class);
    private TestLinkService testLinkService;

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        updateTestCaseWithStatus(iTestResult, ExecutionStatus.PASSED);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        updateTestCaseWithStatus(iTestResult,
                ofNullable(iTestResult)
                        .map(ITestResult::getThrowable)
                        .map(t -> t instanceof AssertionError).orElse(false) ? ExecutionStatus.FAILED : ExecutionStatus.BLOCKED
        );
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        updateTestCaseWithStatus(iTestResult, ExecutionStatus.NOT_RUN);
    }

    private void updateTestCaseWithStatus(ITestResult testRes, ExecutionStatus status) {
        try {
            getTestCaseResult(testRes, status)
                    .ifPresent(getTestLinkService()::updateTestCase);
        } catch (Exception e) {
            logger.error(MessageFormat.format("Failed to update test with result={0} {1}", testRes, e));
            Reporter.log(MessageFormat.format("Failed to update test with result={0}<br/>", testRes));
        }
    }

    private static Optional<TestCaseResult> getTestCaseResult(ITestResult testResult, ExecutionStatus status) {
        XmlTest xmlTest = testResult.getTestContext().getCurrentXmlTest();
        Optional<TestLinkProperties> annotationProperties = getAnnotationProperties(testResult);
        Optional<Integer> buildId = getXmlParameter(xmlTest, TESTLINK_BUILD_ID, () -> annotationProperties.map(TestLinkProperties::buildId).orElse(null));
        Optional<Integer> planId = getXmlParameter(xmlTest, TESTLINK_PLAN_ID, () -> annotationProperties.map(TestLinkProperties::planId).orElse(null));
        Optional<String> displayId = getDisplayId(testResult, () -> annotationProperties.map(TestLinkProperties::displayId).orElse(null));
        return buildId.flatMap(bid ->
                planId.flatMap(pid ->
                        displayId.map(did ->
                                new TestCaseResult(
                                        bid,
                                        pid,
                                        did,
                                        testResult.getTestContext().getAttribute(CONFIGURATION_KEY).toString(),
                                        formatTestParameters(testResult.getParameters()),
                                        String.join(System.lineSeparator(), Reporter.getOutput(testResult)),
                                        status,
                                        ofNullable(testResult.getThrowable()).map(Throwables::getStackTraceAsString).orElse("")
                                )
                        )
                )
        );
    }

    private static Optional<Integer> getXmlParameter(XmlTest xmlTest, String parameterName, Supplier<Integer> other) {
        return ofNullable(ofNullable(ofNullable(xmlTest.getParameter(parameterName)).map(Integer::parseInt).orElseGet(other))
                .filter(value -> value > 0)
                .map(value -> logRetrievedParameter(parameterName, value))
                .orElseGet(() -> logMissingParameter(parameterName)));
    }

    private static Optional<String> getDisplayId(ITestResult testResult, Supplier<String> other) {
        return ofNullable(ofNullable(Arrays.stream(testResult.getParameters())
                .filter(param -> param instanceof TestLinkDisplayIdHolder)
                .findFirst()
                .map(TestLinkDisplayIdHolder.class::cast)
                .map(TestLinkDisplayIdHolder::getDisplayId)
                .orElseGet(other))
                .map(display -> logRetrievedParameter(DISPLAY_ID, display))
                .orElseGet(() -> logMissingParameter(DISPLAY_ID)));
    }

    private static Optional<TestLinkProperties> getAnnotationProperties(ITestResult testResult) {
        return of(testResult)
                .map(ITestResult::getMethod)
                .map(ITestNGMethod::getConstructorOrMethod)
                .map(ConstructorOrMethod::getMethod)
                .map(method -> method.getAnnotation(TestLinkProperties.class));
    }

    private static <T> T logRetrievedParameter(String parameter, T value) {
        logger.debug("Retrieved " + parameter + "=" + value);
        return value;
    }

    private static <T> T logMissingParameter(String parameter) {
        logger.warn("Failed to update test case because " + parameter + " is missing!");
        Reporter.log("Failed to update test case because " + parameter + " is missing!</br>");
        return null;
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

    @Override
    public void onStart(ITestContext iTestContext) {/* Do nothing */}

    @Override
    public void onTestStart(ITestResult iTestResult) {/* Do nothing */}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {/* Do nothing */}

    @Override
    public void onFinish(ITestContext iTestContext) {/* Do nothing */}
}
