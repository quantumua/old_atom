package com.betamedia.atom.core.fwtestrunner.listeners.testng;

import com.betamedia.atom.core.fwtestrunner.runner.TestNGRunner;
import org.testng.ITestNGListener;

/**
 * Generic interface for {@link ITestNGListener} implementation factories to be injected to TestNG runtime
 *
 * @see TestNGRunner
 * @author mbelyaev
 * @since 5/29/17
 */
public interface ConfigurableListenerFactory<T extends ITestNGListener> {
    T get(String outputDirectory);
}
