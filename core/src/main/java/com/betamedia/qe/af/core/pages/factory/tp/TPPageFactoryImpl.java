package com.betamedia.qe.af.core.pages.factory.tp;

import com.betamedia.qe.af.core.pages.factory.AbstractPageFactory;
import com.betamedia.qe.af.core.pages.tp.bidder.Bidder;
import com.betamedia.qe.af.core.pages.tp.bidder.impl.BidderImpl;
import com.betamedia.qe.af.core.pages.tp.login.DisclaimerNotification;
import com.betamedia.qe.af.core.pages.tp.login.LoginErrorNotification;
import com.betamedia.qe.af.core.pages.tp.login.LoginPage;
import com.betamedia.qe.af.core.pages.tp.login.impl.DisclaimerNotificationImpl;
import com.betamedia.qe.af.core.pages.tp.login.impl.LoginErrorNotificationImpl;
import com.betamedia.qe.af.core.pages.tp.login.impl.LoginPageImpl;
import com.betamedia.qe.af.core.pages.tp.navigation.TopNavigationPage;
import com.betamedia.qe.af.core.pages.tp.navigation.TopNavigationPageImpl;
import com.betamedia.qe.af.core.pages.tp.orders.Positions;
import com.betamedia.qe.af.core.pages.tp.orders.impl.PositionsImpl;
import com.betamedia.qe.af.core.pages.type.ProductType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Lazy
@Component
@Scope("prototype")
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
