package com.betamedia.atom.core.testlink;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testlink.annotations.TestLinkDisplayId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlTest;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Listener for TestLink integration.
 * It is required to report test run results to TestLink.
 * Created by Oleksandr Losiev on 5/16/17.
 */
public class TestLinkListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestLinkListener.class);
    private TestLinkService testLinkService = AppContextHolder.getBean(TestLinkService.class);

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
            getTestCaseResult(testRes, status).ifPresent(testLinkService::updateTestCase);
        } catch(Exception e) {
            log.error("Cant update test with result=" + testRes + " due to exception " + e.getMessage());
        }
    }

    private Optional<TestCaseResult> getTestCaseResult(ITestResult testResult, ExecutionStatus status) {
        XmlTest xmlTest = testResult.getTestContext().getCurrentXmlTest();
        Optional<Integer> build = getIntParameterFromTestXml(xmlTest, "testlink.buildId");
        Optional<Integer> plan = getIntParameterFromTestXml(xmlTest, "testlink.planId");
        Optional<String> displayId = getTestDisplayId(testResult);
        if(!build.isPresent() || !plan.isPresent() || !displayId.isPresent()) {
            log.warn("Cant update testCase in TestLink because some of mandatory parameters " +
                    "are missing testlink.buildId=" + build + " testlink.planId=" + plan+
                    " displayId=" + displayId + " testResult=" + testResult);
            return Optional.empty();
        }
        return Optional.of(
                new TestCaseResult(build.get(), plan.get(),
                        formatTestParameters(testResult.getParameters()), status,
                        displayId.get()));

    }

    private Optional<Integer> getIntParameterFromTestXml(XmlTest xml, String paramName) {
        return Optional.ofNullable(xml.getParameter(paramName)).map(Integer::parseInt);
    }

    private Optional<String> getTestDisplayId(ITestResult iTestResult) {
        return Optional.ofNullable(iTestResult.getMethod().getConstructorOrMethod()
                .getMethod().getAnnotation(TestLinkDisplayId.class))
                .map(TestLinkDisplayId::value);
    }

    private String formatTestParameters(Object[] params) {
        return Stream.of(params)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }
}
