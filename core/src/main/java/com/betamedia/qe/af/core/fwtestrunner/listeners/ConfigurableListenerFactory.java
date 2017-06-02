package com.betamedia.qe.af.core.fwtestrunner.listeners;

import com.betamedia.qe.af.core.fwtestrunner.runner.AbstractTestNGRunner;
import org.testng.ITestNGListener;

/**
 * Generic interface for {@link ITestNGListener} implementation factories to be injected to TestNG runtime
 *
 * @see AbstractTestNGRunner
 * @author mbelyaev
 * @since 5/29/17
 */
public interface ConfigurableListenerFactory<T extends ITestNGListener> {
    T get(String outputDirectory);
}
