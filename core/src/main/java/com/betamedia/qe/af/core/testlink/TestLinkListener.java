package com.betamedia.qe.af.core.testlink;

import com.betamedia.qe.af.core.testlink.annotations.TestLinkDisplayId;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by Oleksandr Losiev on 5/16/17.
 */
public class TestLinkListener implements ITestListener{

    private TestLinkService testLinkService = TestLinkServiceLocator.getTestLinkService();

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String extenalId = getTestDisplayId(iTestResult);
        testLinkService.updateTestCase(extenalId, TestCaseResultStatus.PASSED);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String extenalId = getTestDisplayId(iTestResult);
        testLinkService.updateTestCase(extenalId, TestCaseResultStatus.FAILED);

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String extenalId = getTestDisplayId(iTestResult);
        testLinkService.updateTestCase(extenalId, TestCaseResultStatus.BLOCKED);

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
        TestLinkDisplayId idAnnotation = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestLinkDisplayId.class);
        return idAnnotation == null ? "" : idAnnotation.value();
    }
}
