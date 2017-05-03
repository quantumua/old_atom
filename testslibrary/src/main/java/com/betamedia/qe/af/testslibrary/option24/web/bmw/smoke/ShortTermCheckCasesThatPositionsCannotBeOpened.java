package com.betamedia.qe.af.testslibrary.option24.web.bmw.smoke;

import org.junit.Test;
import org.testng.Assert;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.dsl.operations.TagOperations;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.enums.OptionType;

/**
 * @author leonid.a
 */

public class ShortTermCheckCasesThatPositionsCannotBeOpened extends TPEndToEndTest  {
	/*
   * Open a position in allowed invested range but with invested amount
   * that is bigger than client balance by 1 (balance+1)
   */
	@Test() // TP-4508:Invested amount above = TA balance+1
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
		int maxInvestmentAllowed = 48000;//TODOgetMaxInvestment();
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
	
	@Test() //  TP-4502:Invested amount maximum+1
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
	
	@Test() //  TP-4341:Insert valid characters in an invalid form (200.2.5)
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
	
	@Test() // TP-4334:Invest negative amount (-X)
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
	
	@Test() //  TP-4319:Invest zero amount
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

}
