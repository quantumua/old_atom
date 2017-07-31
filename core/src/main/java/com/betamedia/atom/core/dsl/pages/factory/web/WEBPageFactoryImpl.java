package com.betamedia.atom.core.dsl.pages.factory.web;

import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.login.LoginPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.login.impl.LoginPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl.*;
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
    public LoginPage loginPage() {
        return creator.getPage(LoginPageImpl.class);
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
    public WelcomeBackMessageImpl welcomeBackMessage() {
        return creator.getPage(WelcomeBackMessageImpl.class);
    }
	
	@Override
	public AccountAdditionalDetailsPage accountAdditionalDetailsPage() {
		return creator.getPage(AccountAdditionalDetailsPageImpl.class);
	}
	
	 @Override
	    public FnsPersonalInformation fnsPersonalInformation() {
	        return creator.getPage(FnsPersonalInformationImpl.class);
	  }
	 
	 @Override
	    public FnsTradingExperience fnsTradingExperience() {
	        return creator.getPage(FnsTradingExperienceImpl.class);
	  }
	 	 
	 @Override
	    public RiskWarning riskwarning() {
	        return creator.getPage(RiskWarningImpl.class);
	  }
	 @Override
	    public CreditCardDepositPage creditCardDepositPage() {
	        return creator.getPage(CreditCardDepositPageImpl.class);
	  }
	 
	 @Override
     public DocumentUploadForm documentUploadForm() {
         return creator.getPage(DocumentUploadFormImpl.class);
     }

	 @Override
	    public WithdrawalPage withdrawalPage() {
	        return creator.getPage(WithdrawalPageImpl.class);
	    }
	 @Override
	    public SignatureRiskWarning signatureRiskWarning() {
	        return creator.getPage(SignatureRiskWarningImpl.class);
	    }
}

