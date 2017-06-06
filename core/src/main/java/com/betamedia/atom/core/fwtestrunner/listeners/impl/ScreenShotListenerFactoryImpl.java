package com.betamedia.atom.core.fwtestrunner.listeners.impl;

import com.betamedia.atom.core.fwtestrunner.listeners.ConfigurableListenerFactory;
import com.betamedia.atom.core.fwtestrunner.runner.AbstractTestNGRunner;
import org.springframework.stereotype.Service;

/**
 * {@link ConfigurableListenerFactory} implementation for {@link ScreenShotListener} that is managed by Spring and
 * injected into {@link AbstractTestNGRunner}
 *
 * @author mbelyaev
 * @since 5/29/17
 * @see AbstractTestNGRunner
 */
@Service
public class ScreenShotListenerFactoryImpl implements ConfigurableListenerFactory<ScreenShotListener> {
    @Override
    public ScreenShotListener get(String outputDirectory) {
        return new ScreenShotListener(outputDirectory);
    }
}
