package com.betamedia.atom.testslibrary.option24.web.crm;

import com.betamedia.atom.core.testingtype.tp.TPClientTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/19/17.
 */
public class RegisterTest extends TPClientTest {

    @Test
    public void registerNewAccountTest() {

        pages().crmNavigation().register();
        pages().registerPage().register();
    }
}
