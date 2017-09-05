package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;

/**
 * Created by mbelyaev on 5/18/17.
 */
public interface FnsPersonalInformation {
    void submit(PersonalInformation info);

    boolean exists();

    void assertControlsUsability(PersonalInformation info, String expectedItemsBackgroundColor, String expectedAnswerColor, String expectedHoverAnswersColor, String expectedSelectionColor);
}
