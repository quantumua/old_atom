package com.betamedia.atom.core.dsl.pages.extensions.base;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface ExecutesScripts {
    void executeScript(String script, Object... arguments);
}
