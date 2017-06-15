package com.betamedia.atom.core.dsl.pages.factory.web;




import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.RegistrationPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.WelcomePage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.AccountAdditionalDetailsPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsPersonalInformation;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsTradingExperience;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation.TopNavigationPage;


/**
 * @author Lilian Medina
 *         Date: 5/11/17.
 */
public interface WEBPageFactory {
	
	RegistrationPage registrationPage();

    TopNavigationPage topNavigationPage();
    
    WelcomePage welcomepage();
    
    AccountAdditionalDetailsPage accountAdditionalDetailsPage();     
    
    FnsPersonalInformation fnsPersonalInformation();
    
    FnsTradingExperience fnsTradingExperience();
    


}
