package com.betamedia.atom.core.dsl.pages.extensions.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface Finding {

    WebElement find(By first, By... rest);

    List<WebElement> findElements(By by);

}
