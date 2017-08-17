package com.betamedia.atom.core.dsl.pages.extensions;

import com.betamedia.atom.core.dsl.pages.extensions.base.FrameSwitching;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.function.Supplier;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface FrameOperations extends FrameSwitching {
    /**
     * Evaluate {@link Supplier} value for given iFrame located with a {@link By} chain
     *
     * @param supplier function to evaluate
     * @param iFrames  {@link By} locator chain for an iFrame
     * @return return value of supplier
     * @throws NoSuchFrameException           If the given element is neither an IFRAME nor a FRAME element.
     * @throws StaleElementReferenceException If the WebElement has gone stale.
     */
    default <T> T inIFrame(Supplier<T> supplier, By... iFrames) {
        try {
            for (By iFrame : iFrames) {
                switchToFrame(iFrame);
            }
            return supplier.get();
        } finally {
            leaveFrame();
        }
    }
}
