package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;

/**
 * Created by mbelyaev on 5/17/17.
 */
public interface FnsTradingExperience {

    void submit(TradingExperienceInfo info);
    void checkDependsQuestions(TradingExperienceInfo info);
}
