package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;

/**
 * Created by mbelyaev on 3/21/17.
 */
public interface RegistrationPage {


    void register(CustomerBuilder.CustomerRO customer);
    void register();
    
  }
