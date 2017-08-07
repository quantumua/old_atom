package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.SignatureRiskWarning;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Nir Shukrun on 6/13/17.
 */
public class SignatureRiskWarningImpl extends AbstractPageObject implements SignatureRiskWarning {

	@StoredId("RiskSignatureTextbox")
    private By RiskSignatureTextbox;
	
    @StoredId("RiskSignatureSubmitbtn")
    private By RiskSignatureSubmitbtn;
	
    public SignatureRiskWarningImpl(WebDriver webDriver) {
        super(webDriver);
    }

    //"This is a risky investment but you are an experienced person and you aware to the consequences"
    
    @Override
    public void RiskSignatureText(String riskSignatureText) {
        waitUntilDisplayed(RiskSignatureSubmitbtn);
        find(RiskSignatureTextbox).sendKeys(riskSignatureText);
        find(RiskSignatureSubmitbtn).click();
    }
	 
    @Override
    public void waitforRiskSingnature() {
    	waitUntilDisplayed(RiskSignatureSubmitbtn);    	
    }
}
