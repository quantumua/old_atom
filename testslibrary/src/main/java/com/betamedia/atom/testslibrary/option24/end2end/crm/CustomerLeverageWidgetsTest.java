package com.betamedia.atom.testslibrary.option24.end2end.crm;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.testingtype.widgets.WidgetsEndToEndTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/29/17.
 */
public class CustomerLeverageWidgetsTest extends WidgetsEndToEndTest {

    private final int FIRST_AVERAGE = 0;
    private final int SECOND_AVERAGE = 1;
    private final int THIRD_AVERAGE = 2;

    private final String AVERAGE1TO50 = "1:50";
    private final String AVERAGE1TO100 = "1:100";
    private final String AVERAGE1TO200 = "1:200";

    @Test(description = "crm-9038")
    public void checkRejectedCustomerHasDisabledSelectLeverageComboBox() {
        pages().navigation().login();
        createUser(OnboardingWizardConditions.ExperienceLevel.REJECTED, ExperienceScore.REJECTED);
        pages().navigation().setLeverage();
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().size(),0);
    }

    @Test(description = "crm-9039")
    public void checkThatNoExperienceCustomerHasDisabledLeverageComboBox() {
        pages().navigation().login();
        createUser(OnboardingWizardConditions.ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().navigation().setLeverage();
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().size(),1);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(FIRST_AVERAGE),AVERAGE1TO50);
    }

    @Test(description = "crm-9040")
    public void checkThatLowExperienceCustomerCanSelectLeverage() {
        pages().navigation().login();
        createUser(OnboardingWizardConditions.ExperienceLevel.LOW_EXPERIENCE, ExperienceScore.LOW_EXPERIENCE);
        pages().navigation().setLeverage();
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().size(),2);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(FIRST_AVERAGE), AVERAGE1TO50);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(SECOND_AVERAGE),AVERAGE1TO100);
    }

    @Test(description = "crm-9041")
    public void checkThatHighExperienceCustomerCanSelectLeverage() {
        pages().navigation().login();
        createUser(OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE);
        pages().navigation().setLeverage();
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().size(),3);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(FIRST_AVERAGE), AVERAGE1TO50);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(SECOND_AVERAGE),AVERAGE1TO100);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(THIRD_AVERAGE),AVERAGE1TO200);
    }

    @Test(description = "crm-9042")
    public void checkThatExpertCustomerCanSelectLeverage() {
        pages().navigation().login();
        createUser(OnboardingWizardConditions.ExperienceLevel.EXPERT, ExperienceScore.EXPERT);
        pages().navigation().setLeverage();
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().size(),3);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(FIRST_AVERAGE), AVERAGE1TO50);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(SECOND_AVERAGE),AVERAGE1TO100);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(THIRD_AVERAGE),AVERAGE1TO200);
    }

    @Test(description = "crm-9144")
    public void checkThatUnknownCustomerCanSelectLeverage() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().navigation().login();
        pages().loginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        pages().navigation().setLeverage();
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().size(),1);
        Assert.assertEquals(pages().setLeveragePage().getLeveragesList().get(FIRST_AVERAGE), AVERAGE1TO50);
    }

    private CRMCustomer createUser(OnboardingWizardConditions.ExperienceLevel experienceLevel, ExperienceScore experienceScore) {
        CRMCustomer crmCustomer = operations().customerOperations().registerWithWizardConditions(onboardingWizardConditions(experienceLevel));
        operations().customerOperations().updateExperienceScoreInDB(crmCustomer.getId(), experienceScore.get());
        pages().navigation().login();
        pages().loginPage().login(crmCustomer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        pages().navigation().creditCardDeposit();
        pages().creditCardDeposit().submit(
                CreditCardDeposit.builder()
                        .withDepositAmount("100")
                        .build());
        return crmCustomer;
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
        UNKNOWN(0),
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
