package com.betamedia.qe.af.pages.factory;

import com.betamedia.qe.af.entities.page.AbstractPageObject;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface PageObjectCreator {
     <T extends AbstractPageObject> T getPage(Class<T> clazz);
     void closeBrowser();
}
