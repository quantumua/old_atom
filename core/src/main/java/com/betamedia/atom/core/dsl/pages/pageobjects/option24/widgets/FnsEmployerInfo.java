package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;

/**
 * @author mbelyaev
 * @since 7/31/17
 */
public interface FnsEmployerInfo {

    void submit(PersonalInformation info);

    void submitOnWizard(PersonalInformation info);
}
