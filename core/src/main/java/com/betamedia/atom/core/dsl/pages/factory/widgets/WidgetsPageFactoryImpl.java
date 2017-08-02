package com.betamedia.atom.core.dsl.pages.factory.widgets;

import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.impl.*;
import com.betamedia.atom.core.dsl.pages.type.ProductType;

/**
 * Created by vsnigur on 7/31/17.
 */
public class WidgetsPageFactoryImpl extends AbstractPageFactory implements WidgetsPageFactory {
    @Override
    public ProductType getType() {
        return ProductType.WIDGETS;
    }

    @Override
    public LoginPage loginPage() {
        return creator.getPage(LoginPageImpl.class);
    }

    @Override
    public Navigation navigation() {
        return creator.getPage(NavigationImpl.class);
    }

    @Override
    public FnsTradingExperience fnsTradingExperience() {
        return creator.getPage(WidgetFnsTradingExperienceImpl.class);
    }

    @Override
    public FnsPersonalInformation fnsPersonalInformation() {
        return creator.getPage(WidgetFnsPersonalInformationImpl.class);
    }

    @Override
    public FnsEmployerInfo fnsEmployerInfo() {
        return creator.getPage(WidgetFnsEmployerInformationImpl.class);
    }

    @Override
    public RegisterPage registerPage() {
        return creator.getPage(RegisterPageImpl.class);
    }

    @Override
    public AccountDetailsPage accountDetails() {
        return creator.getPage(AccountDetailsPageImpl.class);
    }

    @Override
    public RegistrationPage registrationPage() { return creator.getPage(RegistrationPageImpl.class); }

    @Override
    public SetLeveragePageImpl setLeveragePage() {
        return creator.getPage(SetLeveragePageImpl.class);
    }

    @Override
    public CreditCardDepositPage creditCardDeposit() {
        return creator.getPage(CreditCardDepositPageImpl.class);
    }

    @Override
    public AccountAdditionalDetailsPage accountAdditionalDetailsPage() { return creator.getPage(AccountAdditionalDetailsPageImpl.class); }

    @Override
    public UploadDocumentsPage uploadDocumentsPage() { return creator.getPage(UploadDocumentsPageImpl.class); }
}
