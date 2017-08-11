package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.api.crm.form.entities.AccountDetails;

public interface AccountDetailsPage {

    void invoke();

    void update(AccountDetails info);

    String getEmail();
}