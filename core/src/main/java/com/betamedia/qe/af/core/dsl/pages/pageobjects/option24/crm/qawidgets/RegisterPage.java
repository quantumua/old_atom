package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets;

import com.betamedia.qe.af.core.api.crm.form.builders.RegisterBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder.CustomerRO;

/**
 * Created by vsnigur on 5/19/17.
 */
public interface RegisterPage {
    void update(RegisterBuilder.Register info);

    void register(CustomerRO customer);
}
