package com.betamedia.atom.core.dsl.pages.extensions.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface Waiting {
    Wait<WebDriver> getWait();

    int getTimeout();
}
