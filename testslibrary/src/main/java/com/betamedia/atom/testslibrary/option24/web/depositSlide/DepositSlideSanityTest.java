package com.betamedia.atom.testslibrary.option24.web.depositSlide;

import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractWebCustomerRegistrationTest;
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

    @BeforeMethod
    @Parameters({"countrycode", "phonecountryprefix"})
    public void before(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
       createUser(countrycode, phonecountryprefix, "500");
    }

    /*
     *[TestLink]CTW-19424:Deposit E2E EU
     *
     */
    @Test(description = "CTW-19424:Deposit E2E EU")
    @TestLinkProperties(displayId = "CTW-19424")
    public void depositE2EEU() {
        getDepositByContactId();
    }

    private void getDepositByContactId(){
        //CreditCarddepositExtensionBase ccExtBase = operations().customerOperations().findDeposit(getContactIdByEmail());
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
}
