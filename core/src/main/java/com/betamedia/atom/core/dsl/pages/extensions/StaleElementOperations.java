package com.betamedia.atom.core.dsl.pages.extensions;

import com.betamedia.atom.core.dsl.pages.extensions.base.Logging;
import com.betamedia.atom.core.dsl.pages.extensions.base.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface StaleElementOperations extends Logging, Waiting {
    String DOM_UPDATE_MESSAGE = "DOM updated when retrieving value";

    /**
     * Execute {@link Consumer} of {@link WebElement} for given {@link WebElement} located with a {@link By} chain.<br/>
     * This is for scenarios when target {@link WebElement} is expected to update often, raising {@link StaleElementReferenceException}.<br/>
     * The method will try to execute {@link Consumer} for {@link Waiting#getTimeout()} seconds
     *
     * @param supplier element supplier function
     * @param consumer consumer to execute on located {@link WebElement}
     */
    default void retryOnStale(Supplier<? extends WebElement> supplier, Consumer<? super WebElement> consumer) {
        long start = System.currentTimeMillis();
        do {
            try {
                consumer.accept(supplier.get());
            } catch (StaleElementReferenceException e) {
                getLogger().debug(DOM_UPDATE_MESSAGE, e);
                Reporter.log(DOM_UPDATE_MESSAGE + '\n');
            }
        } while (System.currentTimeMillis() - start < getTimeout() * 1000);
    }

    /**
     * Execute {@link Function} of {@link WebElement} for given {@link WebElement} located with a {@link By} chain.<br/>
     * This is for scenarios when target {@link WebElement} is expected to update often, raising {@link StaleElementReferenceException}.<br/>
     * The method will try to execute {@link Function} for {@link Waiting#getTimeout()} seconds
     *
     * @param supplier element supplier function
     * @param function function to evaluate on located {@link WebElement}
     * @return the function result
     */
    default <T> T retryOnStale(Supplier<? extends WebElement> supplier, Function<? super WebElement, T> function) {
        long start = System.currentTimeMillis();
        do {
            try {
                return function.apply(supplier.get());
            } catch (StaleElementReferenceException e) {
                getLogger().debug(DOM_UPDATE_MESSAGE, e);
                Reporter.log(DOM_UPDATE_MESSAGE + '\n');
            }
        } while (System.currentTimeMillis() - start < getTimeout() * 1000);
        return null;
    }
}
