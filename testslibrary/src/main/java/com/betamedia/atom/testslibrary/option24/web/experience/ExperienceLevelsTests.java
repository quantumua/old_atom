package com.betamedia.atom.testslibrary.option24.web.experience;

import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 8/30/17.
 */
public class ExperienceLevelsTests extends AbstractExperienceLevelsTests {


    private static final boolean RISK_WARNING = true;
    private static final boolean NO_RISK_WARNING = false;

    @Test(description = "CTW-5508:Experience level - Rejected")
    @TestLinkProperties(displayId = "CTW-5508")
    public void ExperienceLevelRejected() {
        operations().onBoardingOperations().assertUsernameRejected(
                createUserByUI(getPersonalInformationScore0(),getRejectedTradingExperienceInfo(), RISK_WARNING).getEmail());
    }

    @Test(description = "CTW-5514:Experience level - No Experience")
    @TestLinkProperties(displayId = "CTW-5514")
    public void ExperienceLevelNoExperience() {
        operations().onBoardingOperations().assertUsernameNoExperience(
                createUserByUI(getPersonalInformationScore0(),getNoTradingExperienceInfo(), RISK_WARNING).getEmail());
    }

    @Test(description = "CTW-5510:Experience level - Low Experience")
    @TestLinkProperties(displayId = "CTW-5510")
    public void ExperienceLevelLowExperience() {
        operations().onBoardingOperations().assertUsernameLowExperience(
                createUserByUI(getPersonalInformationScore0(),getLowTradingExperienceInfo(),NO_RISK_WARNING).getEmail());
    }

    @Test(description = "CTW-5511:Experience level - High Experience")
    @TestLinkProperties(displayId = "CTW-5511")
    public void ExperienceLevelHighExperience() {
        operations().onBoardingOperations().assertUsernameHighExperience(
                createUserByUI(getPersonalInformationScoreMaximum(),getHighTradingExperienceInfo(),NO_RISK_WARNING).getEmail());
    }

    @Test(description = "CTW-5515:Experience level - Expert")
    @TestLinkProperties(displayId = "CTW-5515")
    public void ExperienceLevelExpertExperience() {
        operations().onBoardingOperations().assertUsernameExpert(
                createUserByUI(getPersonalInformationScoreMaximum(),getExpertTradingExperienceInfo(),NO_RISK_WARNING).getEmail());
    }
}
