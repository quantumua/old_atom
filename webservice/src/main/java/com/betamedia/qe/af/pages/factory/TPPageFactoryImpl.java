package com.betamedia.qe.af.pages.factory;

import com.betamedia.qe.af.pages.common.type.AppType;
import com.betamedia.qe.af.pages.tp.login.DisclaimerNotification;
import com.betamedia.qe.af.pages.tp.login.LoginErrorNotification;
import com.betamedia.qe.af.pages.tp.login.LoginPage;
import com.betamedia.qe.af.pages.tp.login.impl.DisclaimerNotificationImpl;
import com.betamedia.qe.af.pages.tp.login.impl.LoginErrorNotificationImpl;
import com.betamedia.qe.af.pages.tp.login.impl.LoginPageImpl;
import com.betamedia.qe.af.pages.tp.navigation.TopNavigationPage;
import com.betamedia.qe.af.pages.tp.navigation.TopNavigationPageImpl;
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
    public AppType getType() {
        return AppType.TP;
    }
}
