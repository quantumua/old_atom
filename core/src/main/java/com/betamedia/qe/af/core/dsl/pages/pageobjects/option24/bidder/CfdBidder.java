package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.bidder;

/**
 * Created by mbelyaev on 4/28/17.
 */
public interface CfdBidder {

    CfdBidder setAmount(String value);

    CfdBidder sell();

    CfdBidder buy();

    CfdBidder confirm();
}
