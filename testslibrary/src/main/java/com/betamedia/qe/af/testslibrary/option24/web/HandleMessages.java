package com.betamedia.qe.af.testslibrary.option24.web;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/16/17.
 */
public class HandleMessages  extends TPEndToEndTest {
    @Test(description = "Handle error if user with zero balance try to trade.")
    public void bidDuringZeroBalanceTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().binaryBidder()
                .setAmount(Double.toString(operations().accountOperations().getTP(customer.getId()).getBalance() + 1));
        pages().binaryBidder().bidHigh();
        pages().dialogBox().assertTitle("Error");
        pages().dialogBox().assertMessage("Your account has been blocked. Please contact customer support at info@24option.com");
        pages().dialogBox().close();
        pages().binaryBidder().bidLow();
        pages().dialogBox().assertTitle("Error");
        pages().dialogBox().assertMessage("Your account has been blocked. Please contact customer support at info@24option.com");
    }

    @Test(description = "Handle error if user with none zero balance try to trade.")
    public void bidMoreThanNoneZeroBalanceTest() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login("vasichka", "123123");
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().binaryBidder().setAmount("1000");
        pages().binaryBidder().bidHigh();
        pages().dialogBox().exists();
        pages().dialogBox().assertTitle("Error");
        pages().dialogBox().assertMessage("Your account doesn't have enough funds.");
        pages().dialogBox().close();
        pages().binaryBidder().bidLow();
        pages().dialogBox().assertTitle("Error");
        pages().dialogBox().assertMessage("Your account doesn't have enough funds.");
    }
}
