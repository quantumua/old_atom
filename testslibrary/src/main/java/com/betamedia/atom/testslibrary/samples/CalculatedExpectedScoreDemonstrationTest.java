package com.betamedia.atom.testslibrary.samples;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels.AbstractUserExperienceTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * @author mbelyaev
 * @since 7/25/17
 */
public class CalculatedExpectedScoreDemonstrationTest extends AbstractUserExperienceTest {

    /**
     * Creates user, fills out experience forms and validates his experience against calculated value
     *
     * Fails due to expected score mismatch
     */
    @Test
    public void scoreTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        TradingExperienceInfo tradingInfo = noBinaryFieldsTradingExperience();
        Assert.assertEquals(tradingInfo.expectedScore, 27);
        PersonalInformation personalInformation = personalInfoScore10();
        Assert.assertEquals(personalInformation.expectedScore, 10);

        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingInfo);
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInformation);

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),
                (double) (tradingInfo.expectedScore + personalInformation.expectedScore));
        operations().onBoardingOperations().assertUsernameHighExperience(customer.getUserName());
    }

    private TradingExperienceInfo noBinaryFieldsTradingExperience() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.NEVER)
                .withBinaryExperience(BinaryExperience.OCCASIONALLY)
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_500_5K)
                .withForExExperience(ForExExperience.NEVER)
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                .build();
    }
}
