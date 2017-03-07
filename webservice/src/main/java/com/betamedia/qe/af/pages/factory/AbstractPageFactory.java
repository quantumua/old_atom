package com.betamedia.qe.af.pages.factory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/23/17.
 */

public abstract class AbstractPageFactory implements PageFactory {
    @Autowired
    protected PageObjectCreator creator;

    @Override
    public void closeBrowser() {
        creator.closeBrowser();
    }
}
