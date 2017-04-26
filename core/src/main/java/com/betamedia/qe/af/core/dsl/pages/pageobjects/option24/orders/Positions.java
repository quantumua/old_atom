package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface Positions {
    List<String> get();

    /**
     * return the displayed id of the last opened position
     *
     * @return displayedId of the last opened position
     */
    String getLast();

    //TODO you are breaking the incapsulating of webElements inside page objects
    WebElement getTradeRow(String positionDisplayId);

    void checkWin(String displayedId);

    void checkLose(String displayedId);

}
