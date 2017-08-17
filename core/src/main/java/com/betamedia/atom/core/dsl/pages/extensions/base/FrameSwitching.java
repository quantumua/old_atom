package com.betamedia.atom.core.dsl.pages.extensions.base;

import org.openqa.selenium.By;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface FrameSwitching {
    void switchToFrame(By iFrame);

    void leaveFrame();
}
