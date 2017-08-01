package com.betamedia.atom.testslibrary.option24.web.crm;

import com.betamedia.atom.core.testingtype.widgets.WidgetsClientTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/19/17.
 */
public class RegisterTest extends WidgetsClientTest {

    @Test
    public void registerNewAccountTest() {

        pages().navigation().register();
        pages().registerPage().register();
    }
}
