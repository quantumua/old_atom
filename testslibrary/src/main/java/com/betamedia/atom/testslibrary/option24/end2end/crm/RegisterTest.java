package com.betamedia.atom.testslibrary.option24.end2end.crm;

import com.betamedia.atom.core.testingtype.tp.TPCachedResourceEndToEndTest;
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
