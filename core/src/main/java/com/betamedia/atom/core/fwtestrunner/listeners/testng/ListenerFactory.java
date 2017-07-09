package com.betamedia.atom.core.fwtestrunner.listeners.testng;

import org.testng.ITestNGListener;

/**
 * @author Maksym Tsybulskyy
 *         Date: 7/9/2017.
 */
public interface ListenerFactory<T extends ITestNGListener> {
    T get();
}
