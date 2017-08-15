package com.betamedia.atom.core.dsl.pages.utils;

/**
 * @author mbelyaev
 * @since 8/15/17
 */
public class BrowserScriptUtils {
    public static final String SET_DISPLAY_BLOCK = "arguments[0].style.display='block'";
    public static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView()";
    public static final String GET_DOCUMENT_READY_STATE = "return document.readyState";


    private BrowserScriptUtils() {
    }
}
