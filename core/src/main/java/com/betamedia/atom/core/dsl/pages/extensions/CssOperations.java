package com.betamedia.atom.core.dsl.pages.extensions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface CssOperations extends WaitOperations {
    /**
     * Gets CSS property of {@link WebElement}
     *
     * @param propertyName CSS property name
     * @param first        element locator
     * @param rest         element locator chain
     * @return found css value
     */
    default String getCssValue(String propertyName, By first, By... rest) {
        return waitUntilDisplayed(first, rest).getCssValue(propertyName);
    }

    /**
     * Gets Attribute of {@link WebElement}
     *
     * @param propertyName CSS property name
     * @param first element locator
     * @param rest element locator chain
     * @return found css value
     */
    default String getAttribute(String propertyName, By first, By... rest) {
        return waitUntilDisplayed(first, rest).getAttribute(propertyName);
    }

    /**
     * Checks CSS property of {@link WebElement} to be equal to expected value
     *
     * @param propertyName  CSS property name
     * @param expectedValue expected property value
     * @param first         element locator
     * @param rest          element locator chain
     * @return <code>true</code> if property value matches expected value, <code>false</code> otherwise
     */
    default boolean checkCssValue(String propertyName, String expectedValue, By first, By... rest) {
        return getCssValue(propertyName, first, rest).equals(expectedValue);
    }
}
