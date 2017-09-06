package com.betamedia.atom.testslibrary.option24.web.depositSlide;

import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.web.AbstractWebNavigationTest;
import org.testng.annotations.*;

/**
 * @author Leonid Artemiev
 * @since 8/28/17
 */
public class DepositSlideSanityTest extends AbstractWebNavigationTest {

    final private static String DEPOSIT = "500";

    /*
     *[TestLink]CTW-19424:Deposit E2E EU
     */
    @Test(description = "CTW-19424:Deposit E2E EU")
    @TestLinkProperties(displayId = "CTW-19424")
    @Parameters({"countrycode", "phonecountryprefix"})
    public void depositE2EEU(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        CustomerRegistrationInfo customerRegistrationInfo = createUser(countrycode,phonecountryprefix, DEPOSIT);
        closeOnboardingWizardAndGoTrade();
        pages().assets().switchToPanda();
        String accountBalance =pages().cfdPositions().getAccountBalance();
        pages().assets().leavePandaFrame();
        softAssert().assertEquals(
                DEPOSIT,
                accountBalance,
                "Checking set deposit durring onboarding registration and deposit from UI");
        softAssert().assertEquals(
                DEPOSIT,
                operations().customerOperations().getCreditCardDepositExtBaseByEmailAddress(customerRegistrationInfo.getEmail())
                        .getRequestedAmount()
                        .toString(),
                "Checking if deposit created in CRM");
    }

}