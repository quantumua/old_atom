package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets;

import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;

/**
 * Created by vsnigur on 5/19/17.
 */
public interface RegisterPage {

    void register(CustomerRO customer);
    void register();
}
