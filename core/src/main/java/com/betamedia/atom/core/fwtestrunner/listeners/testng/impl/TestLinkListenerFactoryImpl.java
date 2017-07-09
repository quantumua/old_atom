package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.fwtestrunner.listeners.testng.ListenerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Maksym Tsybulskyy
 *         Date: 7/9/2017.
 */
@Service
public class TestLinkListenerFactoryImpl implements ListenerFactory<TestLinkListener>{
    @Override
    public TestLinkListener get() {
        return new TestLinkListener();
    }
}
