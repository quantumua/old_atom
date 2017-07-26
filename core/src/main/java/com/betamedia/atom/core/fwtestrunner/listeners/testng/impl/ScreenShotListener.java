package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.atom.core.dsl.pages.pageobjects.browser.BrowserOperations;
import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import com.betamedia.atom.core.fwtestrunner.storage.StorageException;
import com.betamedia.atom.core.testingtype.base.WebDriverTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * {@link ITestListener} implementation that invokes {@link BrowserOperations#takeScreenShot()} on test failure,
 * saves the file to report directory and inserts hyperlink to it in {@link Reporter#log(String)}
 *
 * @author mbelyaev
 * @since 5/29/17
 */
public class ScreenShotListener implements ITestListener {
    public static final String SCREEN_SHOT_DIRECTORY = "screenshots";
    private static final Logger logger = LogManager.getLogger(ScreenShotListener.class);
    private static final String SCREEN_SHOT_MESSAGE = "Screenshot saved at: ";
    private static final String SCREEN_SHOT_FAILURE_MESSAGE = "Could not save screenshot to target directory";

    private BiFunction<File, String[], String> storageStrategy;
    private Function<String, String> screenShotURLStrategy;

    public ScreenShotListener() {
        this.screenShotURLStrategy = path -> getHyperlink(formatForUrl(path));
        this.storageStrategy = FileSystemStorageService::store;
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
                .ifPresent(pages ->
                        takeScreenShot(
                                pages,
                                relativize(result.getTestContext().getOutputDirectory()).getParent().toString()));
    }

    private void takeScreenShot(AbstractPageFactory pageFactory, String outputDirectory) {
        try {
            File screenShot = pageFactory.browser().takeScreenShot();
            String screenShotPath = storageStrategy.apply(screenShot, new String[]{outputDirectory, SCREEN_SHOT_DIRECTORY});
            Reporter.log(SCREEN_SHOT_MESSAGE + screenShotURLStrategy.apply(screenShotPath) + '\n');
        } catch (StorageException e) {
            logger.error(SCREEN_SHOT_FAILURE_MESSAGE, e);
            Reporter.log(SCREEN_SHOT_FAILURE_MESSAGE + '\n');
        }
    }

    private static String getHyperlink(String path) {
        return "<a href=\"" + path + "\">" + path + "</a>";
    }

    private static Path relativize(String path) {
        return Paths.get("")
                .toAbsolutePath()
                .relativize(Paths.get(path));
    }

    private static String formatForUrl(String pathString) {
        Path path = Paths.get(pathString);
        return path
                .getParent()
                .getParent()
                .relativize(path)
                .toString();
    }

    @Override
    public void onTestStart(ITestResult result) {/* Do nothing */}

    @Override
    public void onTestSuccess(ITestResult result) {/* Do nothing */}

    @Override
    public void onTestSkipped(ITestResult result) {/* Do nothing */}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {/* Do nothing */}

    @Override
    public void onStart(ITestContext context) {/* Do nothing */}

    @Override
    public void onFinish(ITestContext context) {/* Do nothing */}

}
