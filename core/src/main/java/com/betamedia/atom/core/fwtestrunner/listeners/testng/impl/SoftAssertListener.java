package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.testingtype.base.AbstractTest;
import org.testng.*;
import org.testng.asserts.SoftAssert;

import java.util.Optional;

/**
 * Listener that handles automatic soft assertion validation on test completion
 *
 * @author mbelyaev
 * @since 7/26/17
 */
public class SoftAssertListener implements ITestListener, IHookable {

    private SoftAssert softAssert;

    @Override
    public void onTestStart(ITestResult result) {
        softAssert = AbstractTest.softAssert();
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        try {
            Optional.ofNullable(softAssert).ifPresent(SoftAssert::assertAll);
        } catch (AssertionError softAssertionError) {
            /*
            * If test has already thrown an exception, add soft assertion error as suppressed,
            * otherwise throw
            * */
            Optional.ofNullable(testResult.getThrowable())
                    .orElseThrow(() -> softAssertionError)
                    .addSuppressed(softAssertionError);
        }
    }


    @Override
    public void onStart(ITestContext context) {/* Do nothing */}

    @Override
    public void onTestSuccess(ITestResult result) {/* Do nothing */}

    @Override
    public void onTestFailure(ITestResult result) {/* Do nothing */}

    @Override
    public void onTestSkipped(ITestResult result) {/* Do nothing */}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {/* Do nothing */}

    @Override
    public void onFinish(ITestContext context) {/* Do nothing */}
}
