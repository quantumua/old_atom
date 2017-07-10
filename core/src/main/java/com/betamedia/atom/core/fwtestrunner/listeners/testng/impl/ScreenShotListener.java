package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.configuration.properties.StorageProperties;
import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.atom.core.dsl.pages.pageobjects.browser.BrowserOperations;
import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import com.betamedia.atom.core.fwtestrunner.storage.StorageException;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import com.betamedia.atom.core.testingtype.base.WebDriverTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.util.function.Function;

/**
 * {@link ITestListener} implementation that invokes {@link BrowserOperations#takeScreenShot()} on test failure,
 * saves the file to report directory and inserts hyperlink to it in {@link Reporter#log(String)}
 *
 * @author mbelyaev
 * @since 5/29/17
 */
public class ScreenShotListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(ScreenShotListener.class);
    public static final String SCREEN_SHOT_DIRECTORY = "screenshots";
    private static final String SCREEN_SHOT_MESSAGE = "Screenshot saved at: ";
    private static final String SCREEN_SHOT_FAILURE_MESSAGE = "Could not save screenshot to target directory";

    private final String outputDirectory;
    private final StorageService storageService;
    private final Function<String, String> screenshotURLStrategy;

    public ScreenShotListener() {
        this.outputDirectory = "";
        this.storageService = new FileSystemStorageService(new StorageProperties());
        this.screenshotURLStrategy = ScreenShotListener::getSurefireURL;
    }

    /**
     * @param outputDirectory screen shot save directory (expected to be same as TestNG report directory)
     * @see ScreenShotListenerFactoryImpl
     */
    public ScreenShotListener(String outputDirectory, StorageService storageService, Function<String, String> strategy) {
        this.outputDirectory = outputDirectory;
        this.storageService = storageService;
        this.screenshotURLStrategy = strategy;
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    /**
     * Checks if relevant test class instance is an instance of {@link WebDriverTest},
     * retrieves {@link AbstractPageFactory} and invokes {@link #takeScreenShot}
     * to get screenshot of the page
     */
    @Override
    public void onTestFailure(ITestResult result) {
        Object test = result.getInstance();
        if (!(test instanceof WebDriverTest)) {
            return;
        }
        ((WebDriverTest<? extends AbstractPageFactory>) test)
                .optPages()
                .ifPresent(this::takeScreenShot);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    private void takeScreenShot(AbstractPageFactory pageFactory) {
        try {
            File screenshot = pageFactory.browser().takeScreenShot();
            storageService.store(screenshot, outputDirectory, SCREEN_SHOT_DIRECTORY);
            Reporter.log(SCREEN_SHOT_MESSAGE + screenshotURLStrategy.apply(String.join("/", SCREEN_SHOT_DIRECTORY, screenshot.getName()) + '\n'));
        } catch (StorageException e) {
            logger.error(SCREEN_SHOT_FAILURE_MESSAGE, e);
            Reporter.log(SCREEN_SHOT_FAILURE_MESSAGE + '\n');
        }
    }

    public static String getWebServiceURL(String path) {
        return "<a href=\"" + path + "\">" + path + "</a>";
    }

    public static String getSurefireURL(String path) {
        return "<a href=\"..\\..\\" + path + "\">" + path + "</a>";
    }
}
