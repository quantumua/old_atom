package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.orders;

/**
 * Created by mbelyaev on 4/28/17.
 */
public interface CfdPositions {
    void validateLatestPosition(String expectedName);

    boolean isAnyPositionOpened();

    Integer countOfOpenedPosition();

    String getAccountBalance();
}
