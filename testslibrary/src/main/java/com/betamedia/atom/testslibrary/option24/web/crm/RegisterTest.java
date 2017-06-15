package com.betamedia.atom.testslibrary.option24.web.crm;

import org.testng.annotations.Test;

import com.betamedia.atom.core.testingtype.tp.TPClientTest;

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
