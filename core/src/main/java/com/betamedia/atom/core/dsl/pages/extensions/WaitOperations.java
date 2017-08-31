package com.betamedia.atom.core.dsl.pages.extensions;

import com.betamedia.atom.core.dsl.pages.extensions.base.Finding;
import com.betamedia.atom.core.dsl.pages.extensions.base.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Supplier;

import static com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations.Scripts.GET_DOCUMENT_READY_STATE;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface WaitOperations extends Waiting, Finding {

    /**
     * Wait until element is displayed ({@link WebElement#isDisplayed()})
     *
     * @param first element locator
     * @param rest  element locator chain
     * @return located {@link WebElement} (never <code>null</code>)
     */
    default WebElement waitUntilDisplayed(By first, By... rest) {
        return getWait().until(driver -> ignoringStale(() -> elementIfVisible(find(first, rest))));
    }

    /**
     * Wait until element exists on page (not necessarily visible)
     *
     * @param first element locator
     * @param rest  element locator chain
     * @return <code>true</code> when element is found
     */
    default WebElement waitUntilExists(By first, By... rest) {
        return getWait().until(driver -> ignoringStale(() -> find(first, rest)));
    }

    default WebElement waitUntilClickable(By first, By... rest) {
        return getWait().until(ExpectedConditions.elementToBeClickable(waitUntilDisplayed(first, rest)));
    }

    /**
     * Waits until the page is fully loaded.
     *
     * @see JavascriptExecutor#executeScript(String, Object...)
     */
    default void waitUntilPageLoad() {
        getWait().until(wd ->
                ((JavascriptExecutor) wd).executeScript(GET_DOCUMENT_READY_STATE).equals("complete"));
    }

    /**
     * Wait until arbitrary condition is not null or false
     *
     * @param isValid condition to evaluate
     * @return return value of condition
     */
    default <T> T waitUntil(Supplier<T> isValid) {
        return getWait().until(d -> ignoringStale(isValid));
    }

    default <T> T ignoringStale(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (StaleElementReferenceException e) {
            return null;
        }
    }

    default WebElement elementIfVisible(WebElement element) {
        return element.isDisplayed() ? element : null;
    }
}
