package com.betamedia.atom.core.testlink;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testlink.annotations.TestLinkDisplayId;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Optional;

/**
 * Listener for TestLink integration.
 * It is required to report test run results to TestLink.
 * Created by Oleksandr Losiev on 5/16/17.
 */
public class TestLinkListener implements ITestListener {

    private TestLinkService testLinkService = AppContextHolder.getBean(TestLinkService.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        testLinkService.updateTestCase(iTestResult.getParameters(),
                getTestDisplayId(iTestResult), ExecutionStatus.PASSED);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        testLinkService.updateTestCase(iTestResult.getParameters(),
                getTestDisplayId(iTestResult), ExecutionStatus.FAILED);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        testLinkService.updateTestCase(iTestResult.getParameters(),
                getTestDisplayId(iTestResult), ExecutionStatus.NOT_RUN);
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

    private String getTestDisplayId(ITestResult iTestResult) {
        return Optional.ofNullable(iTestResult.getMethod().getConstructorOrMethod()
                .getMethod().getAnnotation(TestLinkDisplayId.class))
                .map(TestLinkDisplayId::value)
                .orElse("");
    }
}
