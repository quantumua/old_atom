package com.betamedia.atom.testslibrary.option24.web.depositSlide;

import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.entities.CreditCarddepositExtensionBase;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractWebCustomerRegistrationTest;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Leonid Artemiev
 * @since 8/28/17
 */
public class DepositSlideSanityTest extends AbstractWebCustomerRegistrationTest {

    final private static String DEPOSIT = "500";

    @BeforeMethod
    @Parameters({"countrycode", "phonecountryprefix"})
    public void before(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
       createUser(countrycode, phonecountryprefix, DEPOSIT);
    }

    /*
     *[TestLink]CTW-19424:Deposit E2E EU
     */
    @Test(description = "CTW-19424:Deposit E2E EU")
    @TestLinkProperties(displayId = "CTW-19424")
    public void depositE2EEU() {
        closeWizardAndGoTrade();
        pages().assets().switchToPanda();
        String accountDepost =pages().cfdPositions().getAccountBalance();
        softAssert().assertEquals(DEPOSIT, accountDepost, "Checking set deposit durring onboarding registration and deposit from UI");
        softAssert().assertEquals(DEPOSIT, getCRMDepositByContactId(), "Checking if deposit created in CRM");
    }

    private String getCRMDepositByContactId(){
        CreditCarddepositExtensionBase ccdExtBase = operations().customerOperations().findDeposit(getContactIdByEmail());
        return ccdExtBase.getRequestedAmount().toString();
    }

    private  String getContactIdByEmail() {
        pages().topNavigationPage().goToMyAccount();
        pages().accountDetails().invoke();
        String emailAddress = pages().accountDetails().getEmail();
        final ContactExtension contactExtension = operations().customerOperations().findExtByEmailAddress(emailAddress);
        Reporter.log("contactExtension.getPoiOcrStatus()=" + contactExtension.getPoiOcrStatus());
        Reporter.log("contactExtension.getPorOcrStatus()=" + contactExtension.getPorOcrStatus());
        return contactExtension.getContactId();
    }

    public void closeWizardAndGoTrade() {
        pages().uploadDocumentDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().setLeverageDialog().selectLeverage("100");
    }
}
