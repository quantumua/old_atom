package com.betamedia.qe.af.core.dsl.pages.factory.tp;

import com.betamedia.qe.af.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.bidder.Bidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.bidder.impl.BidderImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.login.DisclaimerNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.login.LoginErrorNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.login.LoginPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.login.impl.DisclaimerNotificationImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.login.impl.LoginErrorNotificationImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.login.impl.LoginPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.navigation.TopNavigationPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.navigation.TopNavigationPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.orders.Positions;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.tfoption.orders.impl.PositionsImpl;
import com.betamedia.qe.af.core.dsl.pages.type.ProductType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Lazy
@Component
@Scope(SCOPE_PROTOTYPE)
public class TPPageFactoryImpl extends AbstractPageFactory implements TPPageFactory {
    @Override
    public LoginPage loginPage() {
        return creator.getPage(LoginPageImpl.class);
    }

    @Override
    public TopNavigationPage topNavigationPage() {
        return creator.getPage(TopNavigationPageImpl.class);
    }

    @Override
    public DisclaimerNotification disclaimerNotification() {
        return creator.getPage(DisclaimerNotificationImpl.class);
    }

    @Override
    public LoginErrorNotification loginErrorNotification() {
        return creator.getPage(LoginErrorNotificationImpl.class);
    }

    @Override
    public Bidder bidder() {
        return creator.getPage(BidderImpl.class);
    }

    @Override
    public Positions positions() {
        return creator.getPage(PositionsImpl.class);
    }

    @Override
    public ProductType getType() {
        return ProductType.TP;
    }
}
