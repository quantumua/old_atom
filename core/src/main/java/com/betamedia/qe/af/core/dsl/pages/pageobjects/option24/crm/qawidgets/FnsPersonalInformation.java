package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets;

import com.betamedia.qe.af.core.api.crm.form.entities.PersonalInformation;

/**
 * Created by mbelyaev on 5/18/17.
 */
public interface FnsPersonalInformation {
    void submit(PersonalInformation info);

    void submitOnWizard(PersonalInformation info);

    void submitOnWizard(String dataValue);
}
