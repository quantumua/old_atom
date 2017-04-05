package com.betamedia.qe.af.core.dsl.pages.factory;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface PageObjectCreator {
     <T extends AbstractPageObject> T getPage(Class<T> clazz);
     void closeBrowser();
}
