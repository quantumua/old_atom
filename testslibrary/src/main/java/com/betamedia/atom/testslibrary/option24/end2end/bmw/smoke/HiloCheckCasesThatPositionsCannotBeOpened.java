package com.betamedia.atom.testslibrary.option24.end2end.bmw.smoke;

import org.testng.annotations.Test;

import com.betamedia.atom.core.api.tp.entities.response.CRMAccount;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.dsl.operations.TagOperations;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.enums.OptionType;

/**
 * @author leonid.a
 */

public class HiloCheckCasesThatPositionsCannotBeOpened extends AbstractOnboardingUserExperienceTest{
	/*
	 * [TestLink] TP-4335:Invest negative amount (-X)
	 * Select an asset --> Try to open a position with a negative invested amount ( example: -500 )
     * Result: There will an error message "Please enter a valid amount." in the input with a red border
	 */
	@Test(description = "TP-4335")
	public void investNegativeAmountTest()	{
        Asset asset = assetIsReadyToTrade(OptionType.HILO, TagOperations.TagName.NO_CATEGORY, 1.5d);
        CRMCustomer customer = createHighExperiencedUser();
        
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 1000d);
		pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
		
		int minInvestmentAllowed = 10;		// TODO getMinInvestment();
		operations().accountOperations().updateBalanceTP(accountId, (double) minInvestmentAllowed);

        pages().binaryBidder()
                .setAmount(String.valueOf(-1))
                .bidHigh()
                .confirm();
        
        pages().binaryBidder().assertValidAmountMessage("Please enter a valid amount.");
	}

	/*
	 * [TestLink] TP-4320:Invest zero amount
	 * Select an asset --> Try to open a position with an invested amount of 0 (zero) 
	 * Result: There will an error message in the input with a red border
	 */
	@Test(description = "TP-4320") 
	public void investZeroAmountTest()	{
		Asset asset = assetIsReadyToTrade(OptionType.HILO, TagOperations.TagName.NO_CATEGORY, 5.0d);
	    CRMCustomer customer = createHighExperiencedUser();
	    
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 1000d);
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
		
		int minInvestmentAllowed = 10;		// TODO getMinInvestment();
		operations().accountOperations().updateBalanceTP(accountId, (double) minInvestmentAllowed);

        pages().binaryBidder()
                .setAmount(String.valueOf(0))
                .bidLow()
                .confirm();
        
        pages().binaryBidder().assertMinAmountMessage("Min. amount\n€\n10  ");
	}
	
	/*
	 * [TestLink] TP-3642:Buying without invested amount
     * Select an asset --> Try to open a position without entering an invested amount--> There's a message: "Please enter a valid amount."
     * Result: There will an error message in the input with a red border
     */
	@Test(description = "TP-3642")
	public void buyingWithoutInvestedAmountTest()	{
		Asset asset = assetIsReadyToTrade(OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT, 5.0d);
	    CRMCustomer customer = createHighExperiencedUser();

		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 1000d);
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
		
		String Investment = "";
		int minInvestmentAllowed = 10;		// TODO getMinInvestment();
		operations().accountOperations().updateBalanceTP(accountId, (double) minInvestmentAllowed);
		
        pages().binaryBidder()
                .setAmount(Investment)
                .bidHigh()
                .confirm();
        
        pages().binaryBidder().assertValidAmountMessage("Please enter a valid amount.");
	}
	/*
	 * [TestLink] TP-3656:Invalid invested amount
	 * Select an asset --> Try to open a position with an invalid value in invested amount--> There's a message that the invested amount doesn't have valid values.
	 * Result: There will an error message in the input with a red border
	 */
	@Test(description = "TP-3656") 
	public void invalidIinvestedAmountTest()	{
		Asset asset = assetIsReadyToTrade(OptionType.HILO, TagOperations.TagName.NO_CATEGORY, 5.0d);
	    CRMCustomer customer = createHighExperiencedUser();

		CRMAccount binaryAccount = customer.getBinaryAccount();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 1000d);
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

		String Investment = "!@#$%^&*)(";

        pages().binaryBidder()
                .setAmount(Investment)
                .bidHigh()
                .confirm();
        
        pages().binaryBidder().assertValidAmountMessage("Please enter a valid amount.");
	}
}
