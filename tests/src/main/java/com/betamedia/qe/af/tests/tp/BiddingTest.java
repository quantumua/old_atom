package com.betamedia.qe.af.tests.tp;

import com.betamedia.qe.af.core.tests.tp.TPEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 3/23/17.
 */
public class BiddingTest extends TPEndToEndTest {

    //WIP
    @Test(enabled = false)
    public void biddingTest() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login("vasichka", "123123");
        pages().disclaimerNotification().accept();
        pages().bidder()
                .highLow()
                .asset()
                .setAmount("10.0")
                .bidHigh()
                .closePosition();
    }

}
