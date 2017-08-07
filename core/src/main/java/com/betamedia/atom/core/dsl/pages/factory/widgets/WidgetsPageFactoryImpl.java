package com.betamedia.atom.core.dsl.pages.factory.widgets;

import com.betamedia.atom.core.dsl.pages.factory.AbstractPageFactory;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.login.LoginPage;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.login.impl.LoginPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.*;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl.AccountDetailsPageImpl;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl.*;
import com.betamedia.atom.core.dsl.type.ProductType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * Created by vsnigur on 7/31/17.
 */
@Lazy
@Component
@Scope(SCOPE_PROTOTYPE)
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
    public SetLeveragePage setLeveragePage() {
        return creator.getPage(SetLeveragePageImpl.class);
    }

    @Override
    public CreditCardDepositPage creditCardDeposit() {
        return creator.getPage(CreditCardDepositPageImpl.class);
    }

    @Override
    public AccountAdditionalDetailsDialog accountAdditionalDetailsPage() { return creator.getPage(AccountAdditionalDetailsPageImpl.class); }

}
