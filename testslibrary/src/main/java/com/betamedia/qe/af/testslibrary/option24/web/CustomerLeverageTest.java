package com.betamedia.qe.af.testslibrary.option24.web;

import com.betamedia.qe.af.core.api.crm.form.builders.CreditCardDepositBuilder;
import com.betamedia.qe.af.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.testslibrary.option24.end2end.crm.AbstractOnboardingConditionsTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/29/17.
 */
public class CustomerLeverageTest extends AbstractOnboardingConditionsTest {

    private OnboardingWizardConditions onboardingWizardConditions;
    private final int FIRST_AVERAGE = 0;
    private final int SECOND_AVERAGE = 1;
    private final int THIRD_AVERAGE = 2;

    private final String AVERAGE1TO50 = "1:50";
    private final String AVERAGE1TO100 = "1:100";
    private final String AVERAGE1TO200 = "1:200";

    @Test(description = "crm-9043")
    public void checkCustomerLeverageForRejectedCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.REJECTED, ExperienceScore.REJECTED).getUserName();
        pages().rejectMessage().isDisplayed();
        pages().topNavigationPage().isLoggedOut();
    }

    @Test(description = "crm-9044")
    public void checkCustomerLeverageForNoExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE).getUserName();
        pages().welcomePage().start();
    }

    @Test(description = "crm-9045")
    public void checkCustomerLeverageForLowExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.LOW_EXPERIENCE, ExperienceScore.LOW_EXPERIENCE).getUserName();
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        pages().setLeverageDialog().expandList();
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().size(),2);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(FIRST_AVERAGE),AVERAGE1TO50);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(SECOND_AVERAGE),AVERAGE1TO100);

    }

    @Test(description = "crm-9046")
    public void checkCustomerLeverageForHighExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE).getUserName();
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        pages().setLeverageDialog().expandList();
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().size(),3);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(FIRST_AVERAGE),AVERAGE1TO50);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(SECOND_AVERAGE),AVERAGE1TO100);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(THIRD_AVERAGE),AVERAGE1TO200);

    }

    @Test(description = "crm-9047")
    public void checkCustomerLeverageForExpertCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.EXPERT, ExperienceScore.EXPERT).getUserName();
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        pages().setLeverageDialog().expandList();
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().size(),3);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(FIRST_AVERAGE),AVERAGE1TO50);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(SECOND_AVERAGE),AVERAGE1TO100);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(THIRD_AVERAGE),AVERAGE1TO200);
    }

    private CRMCustomer createUser(OnboardingWizardConditions.ExperienceLevel experienceLevel, ExperienceScore experienceScore) {
        onboardingWizardConditions = onboardingWizardConditions(experienceLevel);
        CRMCustomer crmCustomer = operations().customerOperations().registerWithWizardConditions(onboardingWizardConditions);
        operations().customerOperations().updateExperienceScoreInDB(crmCustomer.getId(), experienceScore.get());
        pages().topNavigationPage().logIn();
        pages().loginPage().login(crmCustomer.getUserName(), CustomerBuilder.PASSWORD);
        return crmCustomer;
    }

    private void updateCreditCard() {
        CreditCardDepositBuilder builder = new CreditCardDepositBuilder();
        builder.withDepositAmount("100");
        pages().creditCardDeposit().submit(builder.build());
    }

    private OnboardingWizardConditions onboardingWizardConditions(OnboardingWizardConditions.ExperienceLevel experienceLevel) {
        return new OnboardingWizardConditions(true, true, true, true,
                experienceLevel, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, true,
                false, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);

    }

    private enum ExperienceScore {
        REJECTED(5),
        NO_EXPERIENCE(25),
        LOW_EXPERIENCE(45),
        HIGH_EXPERIENCE(65),
        EXPERT(85);

        private int score;

        ExperienceScore(int score) {
            this.score = score;
        }

        public int get() {
            return score;
        }
    }
}
