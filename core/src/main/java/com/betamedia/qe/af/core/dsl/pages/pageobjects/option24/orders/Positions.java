package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface Positions {
    List<String> get();

	WebElement getTradeRow(String positionDisplayId);
    
}
