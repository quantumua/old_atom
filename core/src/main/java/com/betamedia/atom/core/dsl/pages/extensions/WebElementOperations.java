package com.betamedia.atom.core.dsl.pages.extensions;

import com.betamedia.atom.core.dsl.pages.extensions.base.Logging;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface WebElementOperations extends WaitOperations, Logging {
    String EXPECTED_LOOKUP_FAILURE_MESSAGE = "Could not find element";

    /**
     * Check if {@link WebElement} located with {@link By} chain exists on page
     *
     * @param first element locator chain
     * @param rest  element locator chain
     * @return <code>true</code> if element was found on page, <code>false</code> otherwise
     */
    default boolean exists(By first, By... rest) {
        try {
            find(first, rest);
            return true;
        } catch (NoSuchElementException e) {
            getLogger().debug(EXPECTED_LOOKUP_FAILURE_MESSAGE + ": " + first, rest, e);
            return false;
        }
    }

    /**
     * A wrapper that handles {@link TimeoutException} and {@link NoSuchElementException} for {@link WebElement} retrieving methods.
     *
     * @param supplier supplier of the element
     * @return {@link Optional <WebElement>} containing element or empty if nothing was located
     */
    default Optional<WebElement> maybe(Supplier<WebElement> supplier) {
        try {
            return Optional.ofNullable(supplier.get());
        } catch (TimeoutException | NoSuchElementException e) {
            getLogger().debug(EXPECTED_LOOKUP_FAILURE_MESSAGE + ": " + supplier, e);
            Reporter.log(EXPECTED_LOOKUP_FAILURE_MESSAGE + ": " + supplier + '\n');
            return Optional.empty();
        }
    }

    /**
     * Wait until element is displayed and perform {@link WebElement#click()} on it
     *
     * @param first element locator
     * @param rest  element locator chain
     */
    default void click(By first, By... rest) {
        waitUntilDisplayed(first, rest).click();
    }

    /**
     * Retrieves a <code>select</code> element at the locator and wraps it in {@link Select}
     *
     * @param first element locator
     * @param rest  element locator chain
     * @return {@link Select} for the element
     */

    default Select inSelect(By first, By... rest) {
        return inSelect(waitUntilDisplayed(first, rest));
    }

    /**
     * Creates {@link Select} for a given {@link WebElement}
     *
     * @param element source <code>WebElement</code>
     * @return {@link Select} for the element
     */
    default Select inSelect(WebElement element) {
        return new Select(element);
    }

}
