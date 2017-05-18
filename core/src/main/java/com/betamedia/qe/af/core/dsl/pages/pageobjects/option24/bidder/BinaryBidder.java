package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder;

/**
 * Created by mbelyaev on 3/21/17.
 */
public interface BinaryBidder {

    BinaryBidder setAmount(String value);

    BinaryBidder bidLow();

    BinaryBidder bidHigh();

    BinaryBidder confirm();

    String getMaxAmountMessage();

    String getMinAmountMessage();

    String getNotValidAmountMessage();

    void assertMaxAmountMessage(String message);

    void assertMinAmountMessage(String message);

    void assertValidAmountMessage(String message);
}
