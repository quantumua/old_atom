package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class RegisterTest extends TPResourceAwareEndToEndTest {

    @Test
    public void registerNewAccountTest() {

        pages().crmNavigation().register();
               pages().register().register();
    }
}
