package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.usercontrolpanel.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.usercontrolpanel.ControlPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 4/26/17.
 */
public class ControlPanelImpl extends AbstractPageObject implements ControlPanel {

    @StoredId
    private By userDetailsMenu;
    @StoredId
    private By logoutLink;
    @StoredId
    private By accountListMenu;
    @StoredId
    private By accountCurrency;

    public ControlPanelImpl(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    public void logOut() {
        makeActions()
                .moveToElement(find(userDetailsMenu))
                .moveToElement(find(userDetailsMenu, logoutLink))
                .click().perform();
    }

    @Override
    public String getCurrency() {
        waitUntilDisplayed(accountListMenu, accountCurrency);
        return find(accountListMenu, accountCurrency).getText();
    }


}
