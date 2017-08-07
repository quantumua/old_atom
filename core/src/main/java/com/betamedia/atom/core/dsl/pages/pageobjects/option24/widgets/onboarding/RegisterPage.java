package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;

/**
 * Created by vsnigur on 5/19/17.
 */
public interface RegisterPage {

    void register(CustomerRO customer);
    void register();
}