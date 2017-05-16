package com.betamedia.qe.af.testslibrary.option24.web.bmw.smoke;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.testng.Assert;
import org.testng.Reporter;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.dsl.operations.TagOperations;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.tp.api.model.AccountGroup;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.Position;
import com.betamedia.tp.api.model.enums.OptionType;


/**
 * @author leonid.a
 */

public class ShortTermCheckCasesThatPositionsCannotBeOpened extends TPEndToEndTest  {
	private static final Logger logger = LogManager.getLogger(AbstractPageObject.class);
	
	/*
	 * [TestLink] TP-4508:Invested amount above = TA balance+1
     * Select an asset --> Try to open a position (60 sec) with an invested amount that 
     * is equals to account's (balance+1) --> There's a message the position cannot be
     * opened because there's not enough funds.
    */
	@Test() 
	public void InvestedAmountAboveTABalancePlusOne() {
		
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 1.5d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
		
		int minInvestmentAllowed = 10;// TODO getMinInvestment();
		int maxInvestmentAllowed = 48000;//TODO getMaxInvestment();
		double newBalance = (minInvestmentAllowed + maxInvestmentAllowed) / 2;
		operations().accountOperations().updateBalanceTP(accountId, newBalance);
		double investment = operations().accountOperations().getTP(accountId).getBalance() + 1;
		
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        pages().binaryBidder()
                .setAmount(String.valueOf(investment)  )
                .bidHigh()
                .confirm();

        //TODO Make sure there is errormessage appears: "Your account doesn't have enough funds". Message ID: bmMessage-tradingException-insufficientFunds
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65351
	}
	
	/*
	 * [TestLink] TP-4502:Invested amount maximum+1
	 * Select an asset --> Try to open a position with an invested amount that is equals to (maximum +1) -->
	 * There's a message the position cannot be opened because the invested amount is above the maximum.
	 * Result: There will be a pop up as designed.
	 */
	@Test() 
	public void InvestedAmountMaximumPlusOne() {
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 1.5d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
		
		int maxInvestmentAllowed = 48000;		// TODO getMaxInvestment();
		double aboveMaxInvestment = maxInvestmentAllowed + 1;
		
        // Making sure that I have enough money
		Double accountBalance = operations().accountOperations().getTP(accountId).getBalance();
		if(accountBalance < aboveMaxInvestment){
			operations().accountOperations().updateBalanceTP(accountId, (double) aboveMaxInvestment + 200);
		}
		
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        pages().binaryBidder()
                .setAmount(String.valueOf(aboveMaxInvestment)  )
                .bidLow()
                .confirm();
        
        // TODO: Make sure there is pop up message appears:"the position cannot be opened because the invested amount is above the maximum". bmMessageMaxAmount
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65352
	}
	
	/*
	 * [TestLink] TP-4341:Insert valid characters in an invalid form (200.2.5)
     * Select an asset  for 60 sec category --> Try to open a position with valid characters in an invalid form (200.2.5)
     * Result: There will an error message "Please enter a valid amount." in the input with a red border
     */
	@Test()
	public void InsertValidCharactersInAnInvalidForm()  {  
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 1.5d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
		
		int minInvestmentAllowed = 10;		// TODO getMinInvestment();
		float investment = minInvestmentAllowed + 200;
		operations().accountOperations().updateBalanceTP(accountId, (double) minInvestmentAllowed);
	
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        pages().binaryBidder()
                .setAmount(String.valueOf(investment)+".1.1")
                .bidHigh()
                .confirm();
        
        // TODO Make sure there is pop up message appears:"Please enter a valid amount.". bmMessageEnterValidAmount
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65352
	}
	
	/*
	 * [TestLink] TP-4334:Invest negative amount (-X)
     * Select an asset --> Try to open a position with a negative invested amount ( example: -500 )
     * Result: There will an error message "Please enter a valid amount." in the input with a red border
     */
	@Test() 
	public void InvestNegativeAmount()	{
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 1.5d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
		
		int minInvestmentAllowed = 10;		// TODO getMinInvestment();
		operations().accountOperations().updateBalanceTP(accountId, (double) minInvestmentAllowed);

        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        pages().binaryBidder()
                .setAmount(String.valueOf(-1))
                .bidHigh()
                .confirm();
        
        // TODO Make sure there is pop up message appears:"Please enter a valid amount." and input field with red border: bmMessageEnterValidAmount
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65352
	}
	/*
	 * [TestLink] TP-4319:Invest zero amount
     * Select an asset --> Try to open a position with an invested amount of 0 (zero)
     * Result: There will an error message in the input with a red border
     */
	@Test() 
	public void InvestZeroAmount()	{
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 5.0d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
		
		int minInvestmentAllowed = 10;		// TODO getMinInvestment();
		operations().accountOperations().updateBalanceTP(accountId, (double) minInvestmentAllowed);

        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        pages().binaryBidder()
                .setAmount(String.valueOf(0))
                .bidLow()
                .confirm();
        
        // TODO Make sure there is pop up message appears:"Please enter a valid amount." and input field with red border: bmMessageEnterValidAmount
        // Current message "Min. amount $10 "
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65352
	}
	/*
	* [TestLink] TP-3687: Amount > Max exposure
    * Select an asset --> Open few positions till you reach a total amount which is above the total threshold (Max exposure in
    * the account group) --> There's an error message that the position cannot be opened because of maximum exposure.
    * Result: There will be a pop up as designed.
    */
	@Test() 
	public void aboveMaxExposureLimit(){
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
		
        int maxExposure = operations().accountGroupOperations().get().getExposureLimit();
		int minInvestment = 15; //TODO getMinInvestment();	

		Asset assetUnderTest = operations().assetOperations().get();
		operations().optionTemplateOperations().create(assetUnderTest.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(assetUnderTest.getId(), 5.0d);
		
		operations().accountOperations().updateBalanceTP(accountId, (double) minInvestment);
        pages().assets().asset(assetUnderTest.getId(), assetUnderTest.getAssetName());
        pages().binaryBidder()
                .setAmount(String.valueOf(minInvestment))
                .bidHigh()
                .confirm();
		
        List<String> positionIds = pages().binaryPositions().get();
        Position position = operations().positionOperations().getByDisplayId(positionIds.get(positionIds.size()));

      //TODO: cancelPosition as in legacy framework: com.scipio.tptesting.testingmanagers.PositionTestingManager.PositionTestingManager.cancelPosition()
      //PositionTestingManager.getInstance().cancelPosition(position, "Test '" + testCaseDisplayId + "'");
        
        int baseCurrencyInvestemtInt = position.getBaseCurrencyAmount().intValue();
        
        operations().accountGroupOperations().get().setExposureLimit(baseCurrencyInvestemtInt);
        Set<String> propertiesSet = new HashSet<String>();
        propertiesSet.add(AccountGroup.EP_EXPOSURE_LIMIT.getName());
        //TODO: updateEntity ???
        //accountGroupTestingManager.updateEntity(accountGroup, propertiesSet);
        try{
        	operations().feedOperations().injectFeed(assetUnderTest.getId(), 5.0d);
            pages().binaryBidder()
                    .setAmount(String.valueOf(minInvestment+200))
                    .bidHigh()
                    .confirm();
            
            operations().accountGroupOperations().get().setExposureLimit(maxExposure);
            // TODO Make sure there is error message appears:There's an error message that the position cannot be opened because of maximum exposure: bmMessage-tradingException-overExposureLimit
            //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
            //Redmine task: https://devredmine/issues/65352
            
        }catch (Throwable e)  {
            logger.debug("Got error: " + e);
            Reporter.log("Got error: " + e);
            operations().accountGroupOperations().get().setExposureLimit(maxExposure);
        }
	}
	
	/*
	 * [TestLink] TP-3688:Buying without invested amount
     * Select an asset --> Try to open a position without entering an invested amount--> There's a message: "Please enter a valid amount."
     * Result: There will an error message in the input with a red border
     */
	@Test() 
	public void BuyingWithoutInvestedAmount()	{
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 5.0d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
		
		String Investment = "";
		int minInvestmentAllowed = 10;		// TODO getMinInvestment();
		operations().accountOperations().updateBalanceTP(accountId, (double) minInvestmentAllowed);
		
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        pages().binaryBidder()
                .setAmount(Investment)
                .bidHigh()
                .confirm();
        
        // TODO Make sure there is pop up message appears:"Please enter a valid amount." and input field with red border: bmMessageEnterValidAmount
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65352
        
	}
	
	/*
	 * [TestLink] TP-3689:Invalid invested amount
     * Select an asset --> Try to open a position with an invalid value in invested amount-
     * Result: There will an error message in the input with a red border
     */
	@Test() 
	public void InvalidIinvestedAmount()	{
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 5.0d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

		String Investment = "!@#$%^&*)(";

        pages().binaryBidder()
                .setAmount(Investment)
                .bidHigh()
                .confirm();
        
        // TODO Make sure there is pop up message appears:"Please enter a valid amount." and input field with red border: bmMessageEnterValidAmount
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65352
	}

	/*
	 * [TestLink] TP-3693: Invested amount above maximum
     * Select an asset --> Try to open a position with an invested amount above the maximum -->
     * There's a message the position cannot be opened because the invested amount is above the maximum (maximumAllowed+999).
     */	
	@Test() 
	public void InvestedAmountAboveMmaximum() {
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 5.0d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

		int maxInvestment = 48000; //TODO getMaxInvestment();
		int aboveMaxInvestment = maxInvestment + 50;
		
		//Making sure that I have enough money
		Double accountBalance = operations().accountOperations().getTP(accountId).getBalance();
		if (accountBalance <= aboveMaxInvestment) {
            operations().accountOperations().updateBalanceTP(accountId, (double) aboveMaxInvestment + 50);
        }
		
        pages().binaryBidder()
        	.setAmount(String.valueOf(aboveMaxInvestment))
        	.bidLow()
        	.confirm();

        // TODO Make sure there error message appears and close it:" the position cannot be opened because the invested amount is above the maximum": bmMessageMaxAmount
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65352
        
		}

	/*
	 * [TestLink] TP-3694:Invested amount below minimum
     * Select an asset --> Try to open a position with an invested amount below the minimum --> 
     * There's a message the position cannot be opened because the invested amount is below the minimum.
     */	
	@Test() 
	public void InvestedAmountBelowMinimum() {
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 5.0d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());
        
        int minInvestment = 15;   //TODO getMinInvestment();
        float underMinInvestment = minInvestment / 2;
        operations().accountOperations().updateBalanceTP(accountId, (double)minInvestment);
        
        pages().binaryBidder()
    	.setAmount(String.valueOf(underMinInvestment))
    	.bidLow()
    	.confirm();
        
        // TODO Make sure there error message appears and close it:" the position cannot be opened because the invested amount is above the maximum": bmMessageMaxAmount
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65352
	}
	
	/*
	 * [TestLink] TP-3695:Invested amount above TA balance
     * Select an asset --> Try to open a position with an invested amount above the account's balance -->
     * There's a message the position cannot be opened because there's not enough funds.
     */	
	@Test() 
	public void InvestedAmountAboveTABalance() {
		Asset asset = operations().assetOperations().get();
		operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
		operations().feedOperations().injectFeed(asset.getId(), 5.0d);
		CRMCustomer customer = operations().customerOperations().register();
		CRMAccount binaryAccount = customer.getBinaryAccount();
		String accountId = binaryAccount.getId();
		operations().accountOperations().depositCRM(binaryAccount.getId(), 100d);
		pages().topNavigationPage().logIn();
		pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
		Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().disclaimerNotification().tryAccept();
        pages().binarySelector().highLow();
        pages().assets().asset(asset.getId(), asset.getAssetName());

        int maxInvestmentAllowed = 15;	//TODO getMaxInvestment();
        int minInvestmentAllowed = 40000;	//TODO getMinInvestment();
        double newBalance = (maxInvestmentAllowed + minInvestmentAllowed) / 2;
        operations().accountOperations().getTP().setBalance(newBalance);
        
        double investBalans  = operations().accountOperations().getTP().getBalance();
        double investment = (investBalans + maxInvestmentAllowed)/2;
        logger.debug("investment " + investment + " Balance " + investBalans + " maxInvestmentAllowed " + maxInvestmentAllowed + " minInvestmentAllowed " + minInvestmentAllowed);

        pages().binaryBidder()
    		.setAmount(String.valueOf(investment))
    		.bidLow()
    		.confirm();
      
        // TODO Make sure there error message appears and close it:"the position cannot be opened because there's not enough funds": bmMessage-tradingException-insufficientFunds
        //see in legacy framework class: com.scipio.tptesting.pom.def.DefaultPOMMessageDialog
        //Redmine task: https://devredmine/issues/65352
        
	}
}
