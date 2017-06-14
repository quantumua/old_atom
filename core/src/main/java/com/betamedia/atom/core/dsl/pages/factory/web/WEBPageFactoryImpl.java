package com.betamedia.atom.core.dsl.pages.factory.web;

import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.RegistrationPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.WelcomePage;
import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.AccountAdditionalDetailsPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsPersonalInformation;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl.AccountAdditionalDetailsPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl.FnsPersonalInformationImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl.RegistrationPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl.WelcomePageImpl;
import com.betamedia.atom.core.dsl.pages.type.ProductType;
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
	@Override
	public WelcomePage welcomepage() {
		return creator.getPage(WelcomePageImpl.class);
	}
	@Override
	public AccountAdditionalDetailsPage accountAdditionalDetailsPage() {
		return creator.getPage(AccountAdditionalDetailsPageImpl.class);
	}
	
	 @Override
	    public FnsPersonalInformation fnsPersonalInformation() {
	        return creator.getPage(FnsPersonalInformationImpl.class);
	  }
	 
	 	    
}

