package com.betamedia.atom.core.dsl.pages.extensions.base;

import org.openqa.selenium.interactions.Actions;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface WithActions {
    Actions makeActions();
}
