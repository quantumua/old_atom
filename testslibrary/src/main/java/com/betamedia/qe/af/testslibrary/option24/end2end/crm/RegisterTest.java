package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.testingtype.tp.TPCachedResourceEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/19/17.
 */
public class RegisterTest extends TPCachedResourceEndToEndTest {

    @Test
    public void registerNewAccountTest() {

        pages().crmNavigation().register();
               pages().register().register();
    }
}
