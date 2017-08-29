package com.betamedia.atom.core.dsl.pages.extensions;

import com.betamedia.atom.core.dsl.pages.extensions.base.ExecutesScripts;
import com.betamedia.atom.core.dsl.pages.extensions.base.Logging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations.Scripts.SCROLL_INTO_VIEW;
import static com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations.Scripts.SET_DISPLAY_BLOCK;


/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface ScriptOperations extends ExecutesScripts, Logging, WaitOperations {

    default void setDisplayBlock(By first, By... rest) {
        executeScript(SET_DISPLAY_BLOCK, waitUntilExists(first, rest));
    }

    /**
     * Scroll web page to the element to get visibility of it
     *
     * @param element WebElement
     */
    default WebElement scrollIntoView(WebElement element) {
        getLogger().info(String.format("scrolling to element: %s", element));
        executeScript(SCROLL_INTO_VIEW, element);
        return element;
    }

    abstract class Scripts {
        static final String SET_DISPLAY_BLOCK = "arguments[0].style.display='block'";
        static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView()";
        static final String GET_DOCUMENT_READY_STATE = "return document.readyState";
    }
}
