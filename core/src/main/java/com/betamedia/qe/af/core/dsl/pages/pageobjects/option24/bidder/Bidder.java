package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder;

/**
 * Created by mbelyaev on 3/21/17.
 */
public interface Bidder {
    Bidder highLow();

    Bidder shortTerm();

    Bidder doubleProfit();

    Bidder asset(String assetId, String assetName);

    Bidder setAmount(String value);

    Bidder bidLow();

    Bidder bidHigh();

    Bidder confirm();
}
