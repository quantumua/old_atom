package com.betamedia.atom.testslibrary.option24.end2end;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.testingtype.tp.TPEndToEndTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/16/17.
 */
public class HandleBinaryBiddingMessages extends TPEndToEndTest {
    @Test(description = "Handle error if user with zero balance try to trade.")
    public void bidDuringZeroBalanceTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        pages().topNavigationPage().binary();
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        String moreThanBalance = Double.toString(operations().accountOperations().getTP(customer.getId()).getBalance() + 1);
        pages().binaryBidder()
                .setAmount(moreThanBalance);
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
        CRMCustomer customer = operations().customerOperations().register();
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        pages().topNavigationPage().binary();
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

    @Test(description = "Handle error amount messages for high, low, incorrect bid input.")
    public void checkAmountErrorMessagesTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        pages().topNavigationPage().binary();
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().binaryBidder().setAmount("1");
        pages().binaryBidder().bidLow();
        pages().binaryBidder().assertMinAmountMessage("Min. amount\n€\n24  ");
        pages().binaryBidder().setAmount("500000");
        pages().binaryBidder().bidHigh();
        Assert.assertTrue(pages().binaryBidder().getMaxAmountMessage().contains("Max. amount"));
        pages().binaryBidder().setAmount("-1");
        pages().binaryBidder().bidHigh();
        pages().binaryBidder().assertValidAmountMessage("Please enter a valid amount.");
    }
}
