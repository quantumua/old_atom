package com.betamedia.atom.core.testingtype.base;

import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;

import java.util.Optional;

/**
 * Interface for tests that expose page objects and browser-related operations
 *
 * @author mbelyaev
 * @since 5/29/17
 */
public interface WebDriverTest<T extends AbstractPageFactory> {
    T pages();

    Optional<T> optPages();
}
