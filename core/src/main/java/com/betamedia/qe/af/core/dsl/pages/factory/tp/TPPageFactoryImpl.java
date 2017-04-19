package com.betamedia.qe.af.core.dsl.pages.factory.tp;

import com.betamedia.qe.af.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.Assets;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.impl.AssetsImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.Bidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.impl.BidderImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.BinarySelector;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.impl.BinarySelectorImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.DisclaimerNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginErrorNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl.DisclaimerNotificationImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl.LoginErrorNotificationImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl.LoginPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.Positions;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.impl.PositionsImpl;
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
    public Assets assets() {
        return creator.getPage(AssetsImpl.class);
    }

    @Override
    public BinarySelector binarySelector() {
        return creator.getPage(BinarySelectorImpl.class);
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
