package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;

/**
 * Created by vsnigur on 7/3/17.
 */
public interface RegistrationDialog {

    String dialogCaption();

    boolean exists();

    boolean logoExists();

    boolean languageExists();

    boolean liveChatExists();

    boolean riskMessageExists();

    boolean loginButtonExists();

    String firstNameGetMessage();

    String getFirstName();

    boolean register(CustomerRO customerRO);

}
