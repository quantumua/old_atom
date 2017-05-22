package com.betamedia.qe.af.core.dsl.pages.factory.web;

import com.betamedia.qe.af.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.impl.AssetsImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.BinaryBidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.CfdBidder;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.impl.BinaryBidderImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder.impl.CfdBidderImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.BinarySelector;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.binaryselector.impl.BinarySelectorImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.controlpanel.ControlPanel;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.controlpanel.impl.ControlPanelImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.DisclaimerNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginErrorNotification;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl.DisclaimerNotificationImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl.LoginErrorNotificationImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl.LoginPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.MessageBox;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.messages.impl.MessageBoxImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.LandingPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.LandingPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.RegistrationPage;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl.RegistrationPageImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.BinaryPositions;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.CfdPositions;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.impl.BinaryPositionsImpl;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.impl.CfdPositionsImpl;
import com.betamedia.qe.af.core.dsl.pages.type.ProductType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * @author Lilian Medina
 *         Date: 5/11/17.
 */
@Lazy
@Component
@Scope(SCOPE_PROTOTYPE)
public class WEBPageFactoryImpl extends AbstractPageFactory implements WEBPageFactory {
   
	@Override
	public RegistrationPage registrationPage() {
		return creator.getPage(RegistrationPageImpl.class);
	}
    @Override
    public TopNavigationPage topNavigationPage() {
        return creator.getPage(TopNavigationPageImpl.class);
    }
  
    @Override
    public ProductType getType() {
        return ProductType.WEB;
    }

	

	

   
}
