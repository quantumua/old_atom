package com.betamedia.qe.af.core.pages.tp.bidder;

/**
 * Created by mbelyaev on 3/21/17.
 */
public interface Bidder {
    Bidder highLow();

    Bidder shortTerm();

    Bidder doubleProfit();

    Bidder asset(String searchString);

    Bidder setAmount(String value);

    Bidder bidLow();

    Bidder bidHigh();

    Bidder confirm();
}
