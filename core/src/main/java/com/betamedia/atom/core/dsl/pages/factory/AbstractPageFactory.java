package com.betamedia.atom.core.dsl.pages.factory;

import com.betamedia.atom.core.dsl.pages.pageobjects.browser.BrowserOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.browser.impl.BrowserOperationsImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/23/17.
 */

public abstract class AbstractPageFactory implements PageFactory {
    @Autowired
    protected PageObjectCreator creator;

    @Override
    public BrowserOperations browser(){
        return creator.getPage(BrowserOperationsImpl.class);
    }
}
