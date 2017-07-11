package com.betamedia.atom.core.fwtestrunner.listeners.testng;

import com.betamedia.atom.core.fwtestrunner.listeners.testng.impl.ScreenShotListener;

public interface ScreenShotListenerFactory {
    ScreenShotListener get(String outputDirectory);
}
